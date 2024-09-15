import hashlib
#ejemplo3.py
def double_sha256(data):
    """Aplica SHA-256 dos veces."""
    return hashlib.sha256(hashlib.sha256(data).digest()).digest()

# Entrada ficticia: hash de transacción anterior y el índice de la salida
input_txid = "abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234"
input_vout = "00000000"
# Salida ficticia: cantidad en satoshis (0.1 BTC) y un scriptPubKey ficticio
output_value = "00ca9a3b00000000"

# Juntar todos los componentes para formar la estructura de la transacción serializada
raw_transaction = input_txid + input_vout + output_value

# Convertir la transacción en bytes
transaction_bytes = bytes.fromhex(raw_transaction)

# Generar el hash de la transacción
tx_hash = double_sha256(transaction_bytes)

print(f"Hash de la transacción: {tx_hash.hex()}")