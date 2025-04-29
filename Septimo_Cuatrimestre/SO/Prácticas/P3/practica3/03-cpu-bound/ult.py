import gevent
import time
from common import nth_prime_number

# Number of threads to spawn
NUM_THREADS = 5

# Delay in seconds to simulate IO-bound work
# adjust for faster results or to have extra time to monitor resources
SLEEP_TIME = 10

# Count the number of primes up to this limit
LIMIT = 500_000


def do_work():
    greenlet_id = id(gevent.getcurrent())
    start = time.time()
    print(f"[{greenlet_id=}] Doing some work...")
    nth_prime_number(LIMIT)
    elapsed = time.time() - start
    print(f"[{greenlet_id=}] Done with work in {elapsed} seconds.")


if __name__ == "__main__":
    start = time.time()
    print("Starting the program.")
    all_greenlets = []
    for i in range(NUM_THREADS):
        # Start NUM_THREADS greenlets
        greenlet = gevent.spawn(do_work)
        all_greenlets.append(greenlet)

    gevent.joinall(all_greenlets)
    elapsed = time.time() - start
    print(f"All greenlets are done in {elapsed} seconds")
