""" 2- Una vez recibido BTC Test sobre la primera dirección, realice otro script que restaure una billetera desde las palabras generadas en el punto anterior, luego envíe 20 satoshis desde la primera dirección(que tiene BTC Test) a la segunda dirección. """
from cryptos import Bitcoin
seed_phrase = "vital power drama odor mix involve list rate tenant pottery steel scout"

coin = Bitcoin(testnet=True)
wallet = coin.wallet(seed=seed_phrase)

addr1 = wallet.new_receiving_address()
addr2 = wallet.new_change_address()


def balance_calculation(address):
    inputs = coin.unspent(address)
    total = 0
    for i in inputs:
        total += i['value']
    return total


# Blances antes de la transacción
balance_addr1_before = balance_calculation(addr1)
print(f"Balance de la dirección 1 antes de la transacción: {
      balance_addr1_before} satoshi")


balance_addr2_before = balance_calculation(addr2)
print(f"Balance de la dirección 2 antes de la transacción: {
      balance_addr2_before} satoshis")


# Esto hace que explote todo, me tira timeout y no se como solucionarlo :(
if balance_addr1_before >= 20:
    transaction = wallet.send(addr2, 20)
    print("Número de transacción:", transaction)
else:
    print("Fondos insuficientes en addr1 para realizar la transacción.")


# Blances después de la transacción
balance_addr1_after = balance_calculation(addr1)
print(f"Balance de la dirección 1 después de la transacción: {
      balance_addr1_after} satoshi")

balance_addr2_after = balance_calculation(addr2)
print(f"Balance de la dirección 2 después de la transacción: {
      balance_addr2_after} satoshis")
