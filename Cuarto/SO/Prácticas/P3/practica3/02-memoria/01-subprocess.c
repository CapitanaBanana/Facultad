#define _GNU_SOURCE
#include <stdio.h>    // printf
#include <stdlib.h>   // NULL
#include <unistd.h>   // fork, getpid, gettid
#include <sys/wait.h> // wait

int number = 42;

void task()
{
    printf("Child process: PID = %u, THREAD_ID = %d\n", getpid(), gettid());
    number = number * 2; // Modify the global variable
    printf("Child process: number = %d\n", number);
}

int main()
{
    printf("Parent process: PID = %u, THREAD_ID = %d\n", getpid(), gettid());
    printf("Parent process: number = %d\n", number);
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
        printf("Parent process: number = %d\n", number);
    }
}