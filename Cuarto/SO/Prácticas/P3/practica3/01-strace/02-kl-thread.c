#define _GNU_SOURCE
#include <stdio.h>   // printf
#include <stdlib.h>  // NULL
#include <unistd.h>  // getpid, gettid
#include <pthread.h> // pthread_create, pthread_self, pthread_join

void *task(void *arg)
{
    printf("Child thread: PID = %u, THREAD_ID = %d\n", getpid(), gettid());
    return NULL;
}

int main()
{
    pthread_t thread;

    printf("Parent process: PID = %u, THREAD_ID = %d\n", getpid(), gettid());

    // Create new kernel level thread
    if (pthread_create(&thread, NULL, task, NULL) != 0)
    {
        perror("pthread_create");
        return 1;
    }

    // Wait for the child thread to finish
    if (pthread_join(thread, NULL) != 0)
    {
        perror("pthread_join");
        return 1;
    }

    return 0;
}