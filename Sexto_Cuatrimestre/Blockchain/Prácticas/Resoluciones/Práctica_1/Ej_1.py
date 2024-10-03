""" 1- Realice un script que genere una billetera y muestre 2 direcciones de recepción sobre la Testnet de Bitcoin, anote la frase semilla generada. Luego solicite a través de una faucet BTC Test para la primera dirección. """

from cryptos import entropy_to_words, Bitcoin 
import os
seed_phrase = entropy_to_words(os.urandom(16))
print("Seed Phrase:", seed_phrase)
coin = Bitcoin(testnet=True)
wallet = coin.wallet(seed=seed_phrase)

#genera claves y dos direcciones de recepción
print("privada maestra:", wallet.keystore.xprv)
print("pública maestra:", wallet.keystore.xpub)

addr1 = wallet.new_receiving_address()
print("First receiving address:", addr1)
print("First address Private key:", wallet.privkey(addr1))
addr2 = wallet.new_change_address()
print("Second receiving address:", addr2)
print("Second address Private key:", wallet.privkey(addr2))

""" Seed Phrase: vital power drama odor mix involve list rate tenant pottery steel scout
privada maestra: tprv8gdTUVFwSmvj976hNhXR5zQ4TcaoSrcLvQe3z7LTjQbzBSP4maC4MQY2JcqSrEKLGCq7tGAN5eAQko7NpbJHkE6hqy6PBXVaFRMANLSNHYe
pública maestra: tpubDDKVcuJBb9cQ2a8VGMC1VQ4B2e6jcBoFViEqGdNm9gQP1vdqPy1eXu9tUknJMD2EwMB3iU3BhJSMD7qJiViiA2m73YZduJSKZK567RacCkW
First receiving address: mzWXzo4Y859u44PuRSGQ34QdQ1EgQsEDWe
First address Private key: cMqgzpazs3dUBe7kpQe3YRi3ovCVRKKCCcQzFqKC1YGu434zjiJ1
Second receiving address: msgkPaNwLb9CTiPoNudggR8HsArwBh95q7
Second address Private key: cS1E4Ews4PuZduBGjLMNDYJtt8CnMMDzSGCnEMyMu3oBZRW6FuFu """