from web3 import Web3
from dotenv import load_dotenv
import os
load_dotenv()
NODE = os.environ.get("NODE")
w3 = Web3(Web3.HTTPProvider(NODE))
abi = """[
			{
				"inputs": [],
				"stateMutability": "nonpayable",
				"type": "constructor"
			},
			{
				"stateMutability": "payable",
				"type": "fallback"
			},
			{
				"inputs": [],
				"name": "balance",
				"outputs": [
					{
						"internalType": "uint256",
						"name": "",
						"type": "uint256"
					}
				],
				"stateMutability": "view",
				"type": "function"
			},
			{
				"inputs": [],
				"name": "deposit",
				"outputs": [],
				"stateMutability": "payable",
				"type": "function"
			},
			{
				"inputs": [
					{
						"internalType": "uint256",
						"name": "_amount",
						"type": "uint256"
					}
				],
				"name": "withdraw",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			},
			{
				"stateMutability": "payable",
				"type": "receive"
			}
		]"""

contract_address = w3.to_checksum_address(
    '0x63956E69AA0e69BF67828e00E6B632a60E25f7F2')  # address del contrato

account = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY"))
account2 = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY2"))
contrato = w3.eth.contract(address=contract_address, abi=abi)


def withdraw(cant):
    transaction = {
        'nonce': w3.eth.get_transaction_count(account.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': account.address

    }
    contract_data = contrato.functions.withdraw(cant).build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato.functions.withdraw(cant).build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get("PRIVATE_KEY"))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def balance():
    return contrato.functions.balance().call()


def deposit(cant):
    transaction = {
        'nonce': w3.eth.get_transaction_count(account2.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': account2.address,
        'value': cant
    }
    contract_data = contrato.functions.deposit().build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato.functions.deposit().build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get("PRIVATE_KEY2"))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()
