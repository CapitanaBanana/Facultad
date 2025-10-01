import base64
import re
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad

llave = "CLAVE RE SECRETA"  # 16 bytes -> AES-128

B64 = """
dV5t6M4m2AcjYWsxC9iO+YXlc0r0ClfwyTGtpuWdPh9fvH+8cejJWOHYq1qH7qA+Kj7Lci133Awj3rnoq42p532+fvbN64oZ8R/TlMkhw47nmIM5gPN+rt45985jeiIDbdpCu1ig09Rzepl4/kawM1AzFtoMzTvadmx11qSFp+UD81yiRz6HjaFLIIIIQnbzFrmcOIOGEQ6LBEYz2cTW6JPBs7MHpqDrcrzZoLcb7Ah2jQSIId+YZ90JmRt83yTe66a60kqL5SoW7/463Suyyp9xDhrgFu6YS3ScNDgOamADIcKmLUTxrvYooZIjL7s+thek3aBPrv/yB84YNUhX7MOxjiTiP02nBJ1E1dOA0ew75BeARB4cHKVfLMnPMkjSYyiQ2eTWqYd4cZ+14Z9joNVA1Uei8Pg4KITPfJYy3Mc=
"""

def main():
    # limpiar espacios y saltos de línea
    b64_clean = re.sub(r"\s+", "", B64)
    ct = base64.b64decode(b64_clean)

    cipher = AES.new(llave.encode("utf-8"), AES.MODE_ECB)
    pt = cipher.decrypt(ct)
    try:
        pt = unpad(pt, AES.block_size)  # PKCS#7
    except ValueError:
        # si no tuviera padding estándar, comentar la línea de unpad
        pass

    print(pt.decode("utf-8", errors="replace"))

if __name__ == "__main__":
    main()
