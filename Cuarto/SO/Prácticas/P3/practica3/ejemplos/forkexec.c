#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int child_pid = fork();
    if (child_pid == 0) {
        // Child process
        printf("Child process: PID = %d, my parent is: %d\n", getpid(), getppid());
        execl("/bin/ls", "ls", "--color=always", NULL); // Execute the 'ls' command
    } else {
        // Parent process
        printf("Parent process: PID = %d, my parent is: %d\n", getpid(), getppid());
        waitpid(child_pid, NULL, 0); // Wait for the child process to finish
        printf("Parent process: Child finished\n");
    }
}