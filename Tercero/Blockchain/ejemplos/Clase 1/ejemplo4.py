import hashlib
#ejemplo4.py
def proof_of_work(block_data, difficulty="0000"):

    max_nonce = 10000000 # Ajustar para evitar bucles infinitos
    target = int(difficulty + "F" * (64 - len(difficulty)), 16)
    for nonce in range(max_nonce):
        data = f"{block_data}{nonce}".encode()
        hash_result = hashlib.sha256(data).hexdigest()
        if int(hash_result, 16) < target:
            return nonce, hash_result
    return None, None

# Datos del bloque
block_data = "Información del bloque 12345"
nonce, hash_result = proof_of_work(block_data)
print(f"Nonce: {nonce}")
print(f"Hash: {hash_result}")