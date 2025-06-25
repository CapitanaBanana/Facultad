#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <mpi.h>
#include <sys/time.h>
#include <omp.h>

#define COORDINATOR 0
/* Funciones */

void initvalmat(double *mat, int n, int transpose);

void matmulblks(double *a, double *b, double *c, int n, int bs, int rows_per_process);

void blkmul(double *ablk, double *bblk, double *rsltblk, int n, int bs);

double generateRandomNumber(double min, double max);

double dwalltime();
/***********************************************************************************/

int main(int argc, char *argv[])
{
  double *A, *B, *C, *BT, *MR, *BT_local;
  int n, bs = 128, rank, size, rows_per_process, worker_size, num_threads, provided;
  double timetick, R;
  // 0 es a, 1 es b
  double local_min[2] = {1e9, 1e9};
  double local_max[2] = {-1e9, -1e9};
  double local_sum[2] = {0, 0};
  double global_sum[2], global_min[2], global_max[2];
  double t_total_start, t_total_end;
  double t_comm_start, t_comm_end;
  double t_comm = 0.0;
  MPI_Init_thread(&argc, &argv, MPI_THREAD_FUNNELED, &provided);
  MPI_Comm_size(MPI_COMM_WORLD, &size);
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);

  // ValidaciÃ³n de parÃ¡metros
  if ((argc != 3) || ((n = atoi(argv[1])) <= 0) || ((n % size) != 0) || ((num_threads = atoi(argv[2])) <= 0))
  {
    printf("\nError en los parÃ¡metros. Uso: ./%s N (N debe ser mÃºltiplo de BS y de la cantidad de procesos)\n", argv[0]);
    exit(1);
  }

  rows_per_process = n / size;
  worker_size = n * rows_per_process;                   // TamaÃ±o de la matriz por proceso
  bs = (bs < rows_per_process) ? bs : rows_per_process; // Asegurar que el bloque no sea mayor que las filas por proceso

  B = (double *)malloc(n * n * sizeof(double));
  BT = (double *)malloc(n * n * sizeof(double));
  BT_local = (double *)malloc(worker_size * sizeof(double)); // Matriz transpuesta local

  if (rank == COORDINATOR)
  {
    A = (double *)malloc(n * n * sizeof(double));
    C = (double *)malloc(n * n * sizeof(double));
    MR = (double *)malloc(n * n * sizeof(double));
    initvalmat(A, n, 0);
    initvalmat(B, n, 1);
    initvalmat(C, n, 0);
    timetick = dwalltime();
  }
  else
  {
    A = (double *)malloc(worker_size * sizeof(double));
    C = (double *)malloc(worker_size * sizeof(double));
    MR = (double *)malloc(worker_size * sizeof(double));
  }

  MPI_Barrier(MPI_COMM_WORLD);
  t_total_start = MPI_Wtime();

  // distribuir A y C a los trabajadores
  t_comm_start = MPI_Wtime();
  MPI_Scatter(A, worker_size, MPI_DOUBLE, A, worker_size, MPI_DOUBLE, COORDINATOR, MPI_COMM_WORLD);
  MPI_Scatter(C, worker_size, MPI_DOUBLE, C, worker_size, MPI_DOUBLE, COORDINATOR, MPI_COMM_WORLD);

  // distribuir B a todos los procesos
  MPI_Bcast(B, n * n, MPI_DOUBLE, COORDINATOR, MPI_COMM_WORLD);
  t_comm_end = MPI_Wtime();
  t_comm += t_comm_end - t_comm_start;

  int inicio = rank * rows_per_process;
  int fin = inicio + rows_per_process;

#pragma omp parallel num_threads(num_threads)
  {
    // Variables privadas para reducciÃ³n manual
#pragma omp for schedule(static) nowait
    for (int i = 0; i < rows_per_process; i++)
    {
      for (int j = 0; j < n; j++)
      {
        BT_local[j * rows_per_process + i] = B[(inicio + i) * n + j];
      }
    }

    // Busco max y min de A junto con promedio
#pragma omp for reduction(min : local_min[0]) reduction(max : local_max[0]) reduction(+ : local_sum[0]) nowait schedule(static)
    for (int i = 0; i < rows_per_process; i++)
    {
      for (int j = 0; j < n; j++)
      {
        double valA = A[i * n + j];
        if (valA < local_min[0])
          local_min[0] = valA;
        if (valA > local_max[0])
          local_max[0] = valA;
        local_sum[0] += valA;
      }
    }
#pragma omp for reduction(min : local_min[1]) reduction(max : local_max[1]) reduction(+ : local_sum[1]) schedule(static)
    for (int i = inicio; i < fin; i++)
    {
      for (int j = 0; j < n; j++)
      {
        double valB = B[i * n + j];
        if (valB < local_min[1])
          local_min[1] = valB;
        if (valB > local_max[1])
          local_max[1] = valB;
        local_sum[1] += valB;
      }
    }

#pragma omp master
    {
      // Recolectar resultados de min, max y sum de A y B
      t_comm_start = MPI_Wtime();
      MPI_Allreduce(local_min, global_min, 2, MPI_DOUBLE, MPI_MIN, MPI_COMM_WORLD); // min
      MPI_Allreduce(local_max, global_max, 2, MPI_DOUBLE, MPI_MAX, MPI_COMM_WORLD); // max
      MPI_Allreduce(local_sum, global_sum, 2, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD); // sum
      t_comm_end = MPI_Wtime();
      t_comm += t_comm_end - t_comm_start;

      // Recopilar la matriz transpuesta de B
      t_comm_start = MPI_Wtime();
      MPI_Allgather(BT_local, worker_size, MPI_DOUBLE,
                    BT, worker_size, MPI_DOUBLE,
                    MPI_COMM_WORLD);
      t_comm_end = MPI_Wtime();
      t_comm += t_comm_end - t_comm_start;
    }

    double total_elements = n * n;
    double promedioA = global_sum[0] / total_elements;
    double promedioB = global_sum[1] / total_elements;
    R = ((global_max[0] * global_max[1]) - (global_min[0] * global_min[1])) / (promedioA * promedioB);

    matmulblks(A, B, MR, n, bs, rows_per_process);
    matmulblks(C, BT, C, n, bs, rows_per_process);

#pragma omp for schedule(static)
    for (int i = 0; i < rows_per_process; i++)
    {
      for (int j = 0; j < n; j++)
        C[i * n + j] += MR[i * n + j] * R;
    }

#pragma omp master
    {
      // Recolectar resultados finales en el coordinador
      t_comm_start = MPI_Wtime();
      MPI_Gather(MR, worker_size, MPI_DOUBLE, MR, worker_size, MPI_DOUBLE, COORDINATOR, MPI_COMM_WORLD);
      t_comm_end = MPI_Wtime();
      t_comm += t_comm_end - t_comm_start;
    }
  } // Fin de la regiÃ³n paralela

  // Recolectar resultados finales en el coordinador
  t_total_end = MPI_Wtime();

  double min_start, max_end;

  MPI_Reduce(&t_total_start, &min_start, 1, MPI_DOUBLE, MPI_MIN, COORDINATOR, MPI_COMM_WORLD);
  MPI_Reduce(&t_total_end, &max_end, 1, MPI_DOUBLE, MPI_MAX, COORDINATOR, MPI_COMM_WORLD);

  double tiempo_total_comm;
  MPI_Reduce(&t_comm, &tiempo_total_comm, 1, MPI_DOUBLE, MPI_SUM, COORDINATOR, MPI_COMM_WORLD);
  t_comm = tiempo_total_comm / size;

  if (rank == COORDINATOR)
  {
    double elapsed = max_end - min_start;
    printf("MMBLK-MPI-R-COMB;%d;%d;n_nodos:%d;n_hilos:%d;%.6lf;%.6lf\n", n, bs, size, num_threads, elapsed, t_comm);
    free(BT_local);
    free(A);
    free(C);
    free(MR);
  }
  free(B);
  free(BT);

  //

  MPI_Finalize();
  return 0;
}

/* InicializaciÃ³n de matriz con valores aleatorios */
void initvalmat(double *mat, int n, int transpose)
{
  for (int i = 0; i < n; i++)
  {
    for (int j = 0; j < n; j++)
    {
      double val = generateRandomNumber(1, 100);
      if (transpose)
        mat[j * n + i] = val;
      else
        mat[i * n + j] = val;
    }
  }
}

/* MultiplicaciÃ³n de matrices por bloques */
void matmulblks(double *a, double *b, double *c, int n, int bs, int rows_per_process)
{
#pragma omp for nowait schedule(static)
  for (int i = 0; i < rows_per_process; i += bs)
  {
    for (int j = 0; j < n; j += bs)
    {
      for (int k = 0; k < n; k += bs)
        blkmul(&a[i * n + k], &b[j * n + k], &c[i * n + j], n, bs);
    }
  }
}

/*****************************************************************/

/* MultiplicaciÃ³n de bloques */
void blkmul(double *ablk, double *bblk, double *rsltblk, int n, int bs)
{
  for (int i = 0; i < bs; i++)
  {
    for (int j = 0; j < bs; j++)
    {
      double totalRes = 0.0;
      for (int k = 0; k < bs; k++)
        totalRes += ablk[i * n + k] * bblk[j * n + k];
      rsltblk[i * n + j] += totalRes;
    }
  }
}

/* Generar nÃºmero aleatorio */
double generateRandomNumber(double min, double max)
{
  // return (double)rand() / RAND_MAX * (max - min) + min;
  return 1;
}
double dwalltime()
{
  struct timeval tv;
  gettimeofday(&tv, NULL);
  return tv.tv_sec + tv.tv_usec / 1e6;
}
