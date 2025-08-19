#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <windows.h>
#include <sys/time.h>

void *thread_work(void *arg);

/* Init square matrix with a specific value */
void initvalmat(double *mat, int n, double val, int transpose);

/* Multiply square matrices, blocked version */
void matmulblks(double *a, double *b, double *c, int n, int bs);

/* Multiply (block)submatrices */
void blkmul(double *ablk, double *bblk, double *cblk, int n, int bs);

/* Time in seconds from some point in the past */
double dwalltime();

struct thread_multiplication_info
{
  double *a;
  double *b;
  double *c;
  int n;
  int bs;
  int i_start;
  int i_end;
};

/************** MAIN *************/
int main(int argc, char *argv[])
{
  int n, bs;
  
  /* Check command line parameters */
  if (argc != 3) {
    printf("\nError: Se necesitan dos argumentos. Usage: ./%s N BS (N debe ser múltiplo de BS)\n", argv[0]);
    exit(1);
  }

  n = atoi(argv[1]);
  bs = atoi(argv[2]);

  /* Check if n and bs are positive */
  if (n <= 0) {
    printf("\nError: El valor de N debe ser un número positivo mayor que cero.\n");
    exit(1);
  }

  if (bs <= 0) {
    printf("\nError: El valor de BS debe ser un número positivo mayor que cero.\n");
    exit(1);
  }

  /* Check if n is a multiple of bs */
  if (n % bs != 0) {
    printf("\nError: N debe ser múltiplo de BS. (N mod BS = %d)\n", n % bs);
    exit(1);
  }

  printf("Valor de n: %d, Valor de bs: %d\n", n, bs);

  double *a, *b, *c;
  int i, j;
  
  pthread_t threads[nprocs];
  struct thread_multiplication_info infos[nprocs];
  int chunk = (n + nprocs - 1) / nprocs;
  double timetick;

  /* Getting memory */
  a = (double *)malloc(n * n * sizeof(double));
  b = (double *)malloc(n * n * sizeof(double));
  c = (double *)malloc(n * n * sizeof(double));

  if (a == NULL || b == NULL || c == NULL) {
    printf("\nError: No se pudo asignar memoria para las matrices.\n");
    exit(1);
  }

  printf("Memoria para matrices asignada correctamente.\n");

  /* Init matrix operands */
  initvalmat(a, n, 1.0, 0);
  initvalmat(b, n, 1.0, 1);
  initvalmat(c, n, 0.0, 0);

  timetick = dwalltime();
  printf("Tiempo inicial (timetick): %lf segundos\n", timetick);

  // Crear hilos
  for (i = 0; i < nprocs; i++)
  {
    infos[i].a = a;
    infos[i].b = b;
    infos[i].c = c;
    infos[i].n = n;
    infos[i].bs = bs;
    infos[i].i_start = i * chunk;
    infos[i].i_end = (i == nprocs - 1) ? n : (i + 1) * chunk;

    if (pthread_create(&threads[i], NULL, thread_work, &infos[i]) != 0) {
      printf("Error al crear el hilo %d\n", i);
      exit(1);
    }
    printf("Hilo %d creado correctamente.\n", i);
  }

  // Esperar a que terminen
  for (i = 0; i < nprocs; i++) {
    if (pthread_join(threads[i], NULL) != 0) {
      printf("Error al esperar el hilo %d\n", i);
      exit(1);
    }
    printf("Hilo %d terminado correctamente.\n", i);
  }

  double workTime = dwalltime() - timetick;

  printf("Tiempo de trabajo (en segundos): %lf\n", workTime);

  // Si el tiempo de trabajo es mayor que 0, calcular el rendimiento
  if (workTime > 0) {
    printf("MMBLK-SEC;%d;%d;%lf;%lf\n", n, bs, workTime, ((double)2 * n * n * n) / (workTime * 1000000000));
  } else {
    // Si el tiempo de trabajo es muy pequeño
    printf("Error: El tiempo de trabajo es demasiado pequeño para calcular el rendimiento correctamente.\n");
    printf("Tiempo de trabajo: %lf segundos (muy pequeño)\n", workTime);
  }

  // Añadir un pequeño retraso para evitar que el terminal cierre demasiado rápido
  sleep(1);

  return 0;
}

/*****************************************************************/
/* Init square matrix with a specific value */
void initvalmat(double *mat, int n, double val, int transpose)
{
  int i, j;

  if (transpose == 0)
  {
    for (i = 0; i < n; i++)
    {
      for (j = 0; j < n; j++)
      {
        mat[i * n + j] = val;
      }
    }
  }
  else
  {
    for (i = 0; i < n; i++)
    {
      for (j = 0; j < n; j++)
      {
        mat[j * n + i] = val;
      }
    }
  }
}

/*****************************************************************/

void *thread_work(void *arg)
{
  struct thread_multiplication_info *info = (struct thread_multiplication_info *)arg;
  int i, j, k;
  int start = info->i_start;
  int end = info->i_end;
  double *a = info->a;
  double *b = info->b;
  double *c = info->c;
  int n = info->n;
  int bs = info->bs;

  for (i = start; i < end; i += bs)
  {
    for (j = 0; j < n; j += bs)
    {
      for (k = 0; k < n; k += bs)
      {
        blkmul(&a[i * n + k], &b[j * n + k], &c[i * n + j], n, bs);
      }
    }
  }
  return NULL;
}

/*****************************************************************/

/* Multiply (block)submatrices */
void blkmul(double *ablk, double *bblk, double *cblk, int n, int bs)
{
  int i, j, k;

  for (i = 0; i < bs; i++)
  {
    for (j = 0; j < bs; j++)
    {
      double sum = 0.0;
      for (k = 0; k < bs; k++)
      {
        sum += ablk[i * n + k] * bblk[j * n + k];
      }
      cblk[i * n + j] += sum;
    }
  }
}

/*****************************************************************/

double dwalltime()
{
  double sec;
  struct timeval tv;

  gettimeofday(&tv, NULL);
  sec = tv.tv_sec + tv.tv_usec / 1000000.0;
  return sec;
}
