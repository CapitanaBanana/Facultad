#define _GNU_SOURCE
#include <stdio.h>    // printf
#include <stdlib.h>   // NULL
#include <unistd.h>   // fork, getpid, gettid
#include <sys/wait.h> // wait


void task()
{
    printf("Child process: PID = %u, THREAD_ID = %d\n", getpid(), gettid());
}

int main()
{
    printf("Parent process: PID = %u, THREAD_ID = %d\n", getpid(), gettid());
    // Fork a new process
    if (fork() == 0)
    {
        // Child process
        task();
    }
    else
    {
        // Parent process
        wait(NULL); // Wait for the child process to finish
    }
}