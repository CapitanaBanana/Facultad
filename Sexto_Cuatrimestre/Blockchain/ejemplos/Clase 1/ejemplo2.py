# ejemplo2.py
# pip install ecsda
from ecdsa import SigningKey, SECP256k1, VerifyingKey
# Generando una clave privada para la curva secp256k1
sk = SigningKey.generate(curve=SECP256k1)
# Obteniendo la clave pública a partir de la clave privada
vk = sk.get_verifying_key()
# Imprimiendo la clave privada y pública
print("Private Key:", sk.to_string().hex())
print("Public Key:", vk.to_string().hex())