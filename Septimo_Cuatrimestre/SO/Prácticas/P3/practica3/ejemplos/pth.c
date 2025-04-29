#define _GNU_SOURCE
#include <stdio.h>   // printf
#include <stdlib.h>  // NULL
#include <unistd.h>  // getpid
#include <pth.h>     // GNU Pth API

void *task(void *args) {
    printf("Child thread: PID = %d, OS_THREAD_ID = %d, PTH_THREAD_ID = %p\n, ", getpid(), gettid(), pth_self());
    return NULL;
}

int main() {
    pth_t thread;

    // Initialize the GNU Pth library
    if (pth_init() == 0) {
        perror("pth_init");
        return 1;
    }

    printf("Parent thread: PID = %d, OS_THREAD_ID = %d, PTH_THREAD_ID = %p\n, ", getpid(), gettid(), pth_self());

    // Create a new thread
    thread = pth_spawn(PTH_ATTR_DEFAULT, task, NULL);
    if (thread == NULL) {
        perror("pth_spawn");
        return 1;
    }

    // Wait for the child thread to finish
    if (pth_join(thread, NULL) == 0) {
        perror("pth_join");
        return 1;
    }

    // Finalize the GNU Pth library
    pth_kill();
    return 0;
}