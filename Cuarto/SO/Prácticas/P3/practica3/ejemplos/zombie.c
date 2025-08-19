#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int child_pid = fork();
    if (child_pid == 0) {
        // Child process
        printf("Child process: PID = %d, my parent is: %d\n", getpid(), getppid());
        // finishes immediately and becomes a zombie until waited by parent
    } else {
        // Parent process
        printf("Parent process: PID = %d, my parent is: %d\n", getpid(), getppid());
        sleep(10); // Sleep for 10 seconds to allow the child to become a zombie
        waitpid(child_pid, NULL, 0); // Wait for the child process to finish
    }
}