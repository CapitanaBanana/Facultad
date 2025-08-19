#define _GNU_SOURCE
#include <stdio.h>  // printf
#include <stdlib.h> // NULL
#include <unistd.h> // getpid, gettid
#include <pth.h>    // pth_* functions

void *task(void *arg)
{
    printf("Child thread: PID = %u, THREAD_ID = %d, PTH_ID = %lu\n", getpid(), gettid(), (unsigned long)pth_self());
    return NULL;
}

int main()
{
    pth_t thread;

    // Initialize the pth library
    if (pth_init() == 0)
    {
        perror("pth_init");
        return 1;
    }

    printf("Parent process: PID = %u, THREAD_ID = %d, PTH_ID = %lu\n", getpid(), gettid(), (unsigned long)pth_self());

    // Create new user-level thread
    thread = pth_spawn(PTH_ATTR_DEFAULT, task, NULL);
    if (thread == NULL)
    {
        perror("pth_spawn");
        return 1;
    }

    // Wait for the child thread to finish
    if (pth_join(thread, NULL) == 0)
    {
        perror("pth_join");
        return 1;
    }

    // Finalize the pth library
    pth_kill();
    return 0;
}