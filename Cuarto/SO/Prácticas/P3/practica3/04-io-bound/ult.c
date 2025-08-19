#include <pth.h>
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include "common.h" // clockwatch_*

#define SLEEP_TIME 10
#define NUM_THREADS 5

void* do_work(void* arg) {
    unsigned long thread_id = (unsigned long) pth_self();
    clockwatch_t clock;
    double elapsed;

    printf("[Thread %lu] Doing some work...\n", thread_id);
    clockwatch_start(&clock);

    pth_sleep(SLEEP_TIME); // Simulate some io-bound work

    elapsed = clockwatch_stop(&clock);
    printf("[Thread %lu] Done with work in %f seconds.\n", thread_id, elapsed);
    return NULL;
}

int main() {
    pth_t threads[NUM_THREADS];
    clockwatch_t clock;
    double elapsed;

    pth_init();

    printf("Starting the program.\n");
    clockwatch_start(&clock);

    for (int i = 0; i < NUM_THREADS; i++) {
        threads[i] = pth_spawn(PTH_ATTR_DEFAULT, do_work, NULL);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        pth_join(threads[i], NULL);
    }

    elapsed = clockwatch_stop(&clock);
    printf("All threads are done in %f seconds\n", elapsed);

    pth_kill();

    return 0;
}