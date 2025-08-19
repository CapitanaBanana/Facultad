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