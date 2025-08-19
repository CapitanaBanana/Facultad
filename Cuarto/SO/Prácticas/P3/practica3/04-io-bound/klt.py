import threading
import time

# Delay in seconds to simulate IO-bound work
# adjust for faster results or to have extra time to monitor resources
SLEEP_TIME = 10

def do_work():
    thread_id = threading.get_ident()
    print(f"[{thread_id=}] Doing some work...")
    time.sleep(SLEEP_TIME)
    print(f"[{thread_id=}] Done with work.")


if __name__ == "__main__":
    start = time.time()
    print("Starting the program.")
    all_threads = []
    for i in range(5):
        # Start 5 threads
        thread = threading.Thread(target=do_work)
        all_threads.append(thread)
        thread.start()

    for thread in all_threads:
        # Wait for all threads to finish
        thread.join()

    elapsed = time.time() - start
    print(f"All threads are done in {elapsed} seconds")