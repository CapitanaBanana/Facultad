#include <pthread.h>
#include <windows.h>
#include <sys/time.h>
#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
void *thread_work(void *arg);
double dwalltime();
void initarray(int *arr, int n);

int *arr;
int n;

typedef struct {
    int i_start;
    int i_end;
} thread_info;

sem_t sem;
int global_min, global_max;
long long global_sum = 0;




int main(int argc, char *argv[])
{
    int  i;
    double t1, t2;

    if (argc != 2) {
        printf("Usage: %s <size>\n", argv[0]);
        exit(1);
    }

    n = atoi(argv[1]);

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
     global_min = 1e9;
      global_max = -1e9;
      global_sum = 0;
      sem_init(&sem, 0, 1);

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

    printf("Min: %d, Max: %d, Promedio: %lld\n", global_min, global_max, global_sum / n);
    printf("Tiempo: %f segundos\n", t2 - t1);

    free(arr);
    return 0;
}

void *thread_work(void *arg)
{
    thread_info *info = (thread_info *)arg;
    int local_min = 1e9, local_max = -1e9;
    long long local_sum = 0;

    for (int i = info->i_start; i < info->i_end; i++) {
        int val = arr[i];
        local_sum += val;
        if (val < local_min) local_min = val;
        if (val > local_max) local_max = val;
    }
    sem_wait(&sem);
    if (local_min < global_min) global_min = local_min;
    if (local_max > global_max) global_max = local_max;
    global_sum += local_sum;
    sem_post(&sem);
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
