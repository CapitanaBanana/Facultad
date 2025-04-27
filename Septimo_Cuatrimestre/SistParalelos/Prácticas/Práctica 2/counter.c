#include <pthread.h>
#include <windows.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>

void *thread_work(void *arg);
double dwalltime();
void initarray(int *arr, int n);

int *arr;
int x;
int n;

typedef struct {
    int i_start;
    int i_end;
} thread_info;

pthread_mutex_t lock;
int ocurrencias = 0;

int main(int argc, char *argv[])
{
    int  i;
    double t1, t2;

    if (argc != 3) {
        printf("Usage: %s <size> <value>\n", argv[0]);
        exit(1);
    }

    n = atoi(argv[1]);
    x = atoi(argv[2]);

    arr = (int *)malloc(n * sizeof(int));
    if (arr == NULL) {
        printf("Memory allocation failed\n");
        return 1;
    }

    initarray(arr, n); 

    SYSTEM_INFO sysinfo;
    GetSystemInfo(&sysinfo);
    int nprocs = sysinfo.dwNumberOfProcessors;
    int chunk_size = (n + nprocs - 1) / nprocs;

    pthread_t threads[nprocs];
    thread_info thread_data[nprocs];

    pthread_mutex_init(&lock, NULL);

    t1 = dwalltime();

    for (i = 0; i < nprocs; i++) {
        thread_data[i].i_start = i * chunk_size;
        thread_data[i].i_end = (i + 1) * chunk_size < n ? (i + 1) * chunk_size : n;
        pthread_create(&threads[i], NULL, thread_work, &thread_data[i]);
    }

    for (i = 0; i < nprocs; i++) {
        pthread_join(threads[i], NULL);
    }

    t2 = dwalltime();

    printf("Ocurrencias de %d: %d\n", x, ocurrencias);
    printf("Tiempo: %f segundos\n", t2 - t1);

    pthread_mutex_destroy(&lock);
    free(arr);
    return 0;
}

void *thread_work(void *arg)
{
    thread_info *info = (thread_info *)arg;
    int local_count = 0;
    for (int i = info->i_start; i < info->i_end; i++) {
        if (arr[i] == x) {
            local_count++;
        }
    }

    pthread_mutex_lock(&lock);
    ocurrencias += local_count;
    pthread_mutex_unlock(&lock);

    return NULL;
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
