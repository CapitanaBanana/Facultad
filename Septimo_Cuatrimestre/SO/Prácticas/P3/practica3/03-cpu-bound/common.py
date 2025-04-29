# Este archivo provee funciones útiles para los ejemplos, pero no
# es necesario que les alumnes lo analicen.

def is_prime(nth):
    # Check if n is prime
    if nth < 2:
        return False
    if nth == 2:
        return True
    for i in range(2, int(nth**0.5) + 1):
        if nth % i == 0:
            return False
    return True


def nth_prime_number(nth):
    # Find the nth prime number
    last_prime = 2
    count = 0
    i = 2
    while count < nth:
        if is_prime(i):
            last_prime = i
            count += 1
        i += 1
    print(f"{nth}th prime is {last_prime}")
    return last_prime