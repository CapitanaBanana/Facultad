
import os
from web3 import Web3
from dotenv import load_dotenv
load_dotenv()
NODE = os.environ.get("NODE")
w3 = Web3(Web3.HTTPProvider(NODE))
# print(w3.is_connected())
# print(w3.eth.get_block('latest'))
account = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY"))
# print(account.address)

# para mandar 0.05 ether a mi cuenta account 1
""" signed_txn = w3.eth.account.sign_transaction({
    'nonce': w3.eth.get_transaction_count(account.address),
    'gas': 21000,
    'gasPrice': w3.eth.gas_price,
    'to': '0x243864768d88FB03e79D5B332Fea6714f18AEcf2',
    'value': 5 * 10**16,
    'chainId': 11155111,
}, os.environ.get("PRIVATE_KEY"))
tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction) 
print(tx_hash)"""

# para conseguir la data a partir del hash de la transacción
""" tx = '0x0542ddc3d3c8393a9d5c2f10b4d78e79372f1c45e76603bbee949f79a82b45c8'
print(w3.eth.get_transaction(tx))
print(w3.eth.get_transaction_receipt(tx))#imprimen parecido, capaz este más lindo
"""
