#include <pthread.h>
#include <windows.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>

double dwalltime();
void initarray(int *arr, int n);
int main(int argc, char *argv[]) {
    int n, x, i, ocurrencias = 0;
    double t1, t2;

    if (argc != 3) {
        printf("Usage: %s <size> <value>\n", argv[0]);
        exit(1);
    }

    n = atoi(argv[1]);
    x = atoi(argv[2]);

    int *arr = (int *)malloc(n * sizeof(int));
    if (arr == NULL) {
        printf("Memory allocation failed\n");
        return 1;
    }

    initarray(arr, n);  

    t1 = dwalltime();

 
    for (i = 0; i < n; i++) {
        if (arr[i] == x) {
            ocurrencias++;
        }
    }

    t2 = dwalltime();

    printf("Ocurrencias de %d: %d\n", x, ocurrencias);
    printf("Tiempo: %f segundos\n", t2 - t1);

    free(arr);
    return 0;
}

void initarray(int *arr, int n)
{
    for (int i = 0; i < n; i++) {
        arr[i] = rand();
    }
}

double dwalltime()
{
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return (double)tv.tv_sec + (double)tv.tv_usec / 1000000.0;
}
