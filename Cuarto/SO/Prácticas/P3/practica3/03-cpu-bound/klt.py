import threading
import time
from common import nth_prime_number

# Number of threads to spawn
NUM_THREADS = 7

# Delay in seconds to simulate IO-bound work
# adjust for faster results or to have extra time to monitor resources
SLEEP_TIME = 10

# Count the number of primes up to this limit
LIMIT = 500_000


def do_work():
    thread_id = threading.get_ident()
    start = time.time()
    print(f"[{thread_id=}] Doing some work...")
    nth_prime_number(LIMIT)  # This is a CPU-bound task
    elapsed = time.time() - start
    print(f"[{thread_id=}] Done with work in {elapsed} seconds.")


if __name__ == "__main__":
    start = time.time()
    print("Starting the program.")
    all_threads = []
    for i in range(NUM_THREADS):
        # Start NUM_THREADS threads
        thread = threading.Thread(target=do_work)
        all_threads.append(thread)
        thread.start()

    for thread in all_threads:
        # Wait for all threads to finish
        thread.join()

    elapsed = time.time() - start
    print(f"All threads are done in {elapsed} seconds")
