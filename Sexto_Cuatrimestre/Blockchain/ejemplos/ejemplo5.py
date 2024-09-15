#ejemplo5.py
#pip install mnemonic
#pip install git+https://github.com/primal100/pybitcointools
from cryptos import entropy_to_words, Bitcoin
import os
words = entropy_to_words(os.urandom(16))
print("mi frase semilla:", words)
coin = Bitcoin()
wallet = coin.wallet(words)
print("path de derivación:", wallet.keystore.root_derivation)
print("privada maestra:", wallet.keystore.xprv)
print("pública maestra:", wallet.keystore.xpub)
addr1 = wallet.new_receiving_address()# creando pública de recepción
print("addr1:", addr1)
print("privada de addr1:", wallet.privkey(addr1))
addr2 = wallet.new_change_address()# creando 2da pública de recepción
print("addr2:", addr2)
print("privada addr2:",wallet.privkey(addr2))

