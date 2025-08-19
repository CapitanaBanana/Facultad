#ifndef COMMON_H
#define COMMON_H

#undef _POSIX_C_SOURCE
#define _POSIX_C_SOURCE 200809L
#include <stdio.h>
#include <unistd.h>
#include <time.h>
#undef _POSIX_C_SOURCE

/**
 * Este archivo provee funciones útiles para los ejemplos, pero no
 * es necesario que les alumnes lo analicen.
 */

typedef struct clockwatch {
    struct timespec start;
    struct timespec end;
} clockwatch_t;

/**
 * @brief Calculate the difference in nanoseconds
 */
double difftime_ns(struct timespec end, struct timespec start);

/**
 * @brief Check if n is prime
 */
int is_prime(int n);
/**
 * @brief Find the nth prime number
 */
int nth_prime_number(int nth);

// Clockwatch functions
// These functions are used to measure elapsed time

/**
 * @brief Start the clockwatch
 */
void clockwatch_start(clockwatch_t *cw);
/**
 * @brief Stop the clockwatch and return elapsed time in seconds
 */
double clockwatch_stop(clockwatch_t *cw);
/**
 * @brief Get the elapsed time in seconds
 */
double clockwatch_get_time(clockwatch_t *cw);

#endif // COMMON_H