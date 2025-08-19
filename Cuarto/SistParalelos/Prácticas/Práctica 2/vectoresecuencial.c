#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>

double dwalltime();
void initarray(int arr, int n);
int main(int argc, charargv[])
{
  int n, i;
 double promedio = 0;
  int max = -1;
  int min = 99999999;
  double t1, t2;

  if (argc != 2)
  {
    printf("Usage: %s <size> \n", argv[0]);
    exit(1);
  }

  n = atoi(argv[1]);

  int arr = (int)malloc(n * sizeof(int));
  if (arr == NULL)
  {
    printf("Memory allocation failed\n");
    return 1;
  }

  initarray(arr, n);

  t1 = dwalltime();

  for (i = 0; i < n; i++)
  {

    if (max < arr[i])
      max = arr[i];

          if (min > arr[i])
              min = arr[i];

          promedio += arr[i];
  }

  promedio /= n;

      t2 = dwalltime();

  printf("min: %d , max: %d , promedio: %d\n", min, max, promedio);
  printf("Tiempo: %f segundos\n", t2 - t1);

  free(arr);
  return 0;
}

void initarray(int *arr, int n)
{
  for (int i = 0; i < n; i++)
  {
    arr[i] = rand();
  }
}

double dwalltime()
{
  struct timeval tv;
  gettimeofday(&tv, NULL);
  return (double)tv.tv_sec + (double)tv.tv_usec / 1000000.0;
}