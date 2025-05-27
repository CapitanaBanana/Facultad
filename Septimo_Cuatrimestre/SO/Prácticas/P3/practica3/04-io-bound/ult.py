import gevent
import time

# Delay in seconds to simulate IO-bound work
# adjust for faster results or to have extra time to monitor resources
SLEEP_TIME = 10

def do_work():
    greenlet_id = id(gevent.getcurrent())
    print(f"[{greenlet_id=}] Doing some work...")
    gevent.sleep(SLEEP_TIME)
    print(f"[{greenlet_id=}] Done with work.")


if __name__ == "__main__":
    start = time.time()
    print("Starting the program.")
    all_greenlets = []
    for i in range(5):
        # Start 5 greenlets
        greenlet = gevent.spawn(do_work)
        all_greenlets.append(greenlet)

    gevent.joinall(all_greenlets)
    elapsed = time.time() - start
    print(f"All greenlets are done in {elapsed} seconds")