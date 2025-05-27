#include "common.h"

/**
 * Este archivo provee funciones útiles para los ejemplos, pero no
 * es necesario que les alumnes lo analicen.
 */

double difftime_ns(struct timespec end, struct timespec start)
{
    // Calculate the difference in nanoseconds
    // between two timespec structures
    return (end.tv_sec - start.tv_sec) + (end.tv_nsec - start.tv_nsec) * 1e-9;
}

void clockwatch_start(clockwatch_t *cw)
{
    // Start the clockwatch
    clock_gettime(CLOCK_MONOTONIC, &cw->start);
}

double clockwatch_stop(clockwatch_t *cw)
{
    // Stop the clockwatch
    clock_gettime(CLOCK_MONOTONIC, &cw->end);
    // Calculate the elapsed time
    return clockwatch_get_time(cw);
}

double clockwatch_get_time(clockwatch_t *cw)
{
    // Get the elapsed time in seconds
    return difftime_ns(cw->end, cw->start);
}

int is_prime(int n)
{
    // Check if n is prime
    if (n < 2)
    {
        return 0;
    }
    if (n == 2)
        return 1;
    for (int i = 2; i * i <= n; i++)
    {
        if (n % i == 0)
        {
            return 0;
        }
    }
    return 1;
}

int nth_prime_number(int nth)
{
    // Find the nth prime number
    int last_prime = 2;
    int count = 0;
    int i;
    for (i = 2; count < nth; i++)
    {
        if (is_prime(i))
        {
            last_prime = i;
            count++;
        }
    }
    printf("%dth prime is %d\n", nth, last_prime);
    return last_prime;
}