# pip install eth-utils eth-keys mnemonic
from eth_keys import keys
from mnemonic import Mnemonic
from eth_utils import keccak
# 1. Generación de la frase semilla (seed phrase)
mnemo = Mnemonic("english")
seed_phrase = mnemo.generate(strength=256)
print("Seed Phrase:", seed_phrase)
# 2. Transformación de la seed phrase en una seed
seed = Mnemonic.to_seed(seed_phrase)
# Función para derivar la clave privada a partir de la seed y un índice


def derive_private_key(seed, index):
    # Usamos keccak256 como función hash
    return keccak(seed + index.to_bytes(4, byteorder='big'))


# Derivamos dos claves privadas con índices diferentes
private_key1 = derive_private_key(seed, 0)
private_key2 = derive_private_key(seed, 1)

# Convertimos las claves privadas en claves públicas y direcciones
private_key_obj1 = keys.PrivateKey(private_key1)
public_key_obj1 = private_key_obj1.public_key
address1 = public_key_obj1.to_address()

private_key_obj2 = keys.PrivateKey(private_key2)
public_key_obj2 = private_key_obj2.public_key
address2 = public_key_obj2.to_address()

print("\nPrivate Key 1:", private_key_obj1.to_hex())
print("Public Key 1:", public_key_obj1.to_hex())
print("Address 1:", address1)

print("\nPrivate Key 2:", private_key_obj2.to_hex())
print("Public Key 2:", public_key_obj2.to_hex())
print("Address 2:", address2)
