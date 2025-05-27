#define _POSIX_C_SOURCE 200809L
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include "common.h" // clockwatch_t, difftime_ns, nth_prime_number

#define SLEEP_TIME 10
#define NUM_THREADS 5

// Count the number of primes up to this limit
#define LIMIT 2500000


void* do_work(void* arg) {
    pthread_t thread_id = pthread_self();
    clockwatch_t clock;
    double elapsed;

    
    printf("[Thread %lu] Doing some work...\n", thread_id);
    clockwatch_start(&clock);

    // Calculate the nth prime number
    nth_prime_number(LIMIT); // CPU bound work
    
    elapsed = clockwatch_stop(&clock);
    printf("[Thread %lu] Done with work in %f seconds.\n", thread_id, elapsed);
    return NULL;
}

int main() {
    pthread_t threads[NUM_THREADS];
    clockwatch_t clock;
    double elapsed;

    clockwatch_start(&clock);
    printf("Starting the program.\n");

    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_create(&threads[i], NULL, do_work, NULL);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    elapsed = clockwatch_stop(&clock);
    printf("All threads are done in %f seconds\n", elapsed);

    return 0;
}
