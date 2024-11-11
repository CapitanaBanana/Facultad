import tkinter as tk
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
				"anonymous": false,
				"inputs": [
					{
						"indexed": false,
						"internalType": "address",
						"name": "by",
						"type": "address"
					},
					{
						"indexed": false,
						"internalType": "uint256",
						"name": "counterNow",
						"type": "uint256"
					}
				],
				"name": "counterModified",
				"type": "event"
			},
			{
				"inputs": [
					{
						"internalType": "address",
						"name": "_address",
						"type": "address"
					}
				],
				"name": "addToWhiteList",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			},
			{
				"inputs": [],
				"name": "decrement",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			},
			{
				"inputs": [],
				"name": "getCounter",
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
				"name": "increment",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			}
		]
"""

contract_address = w3.to_checksum_address(
    '0xb1947019F293d4717E9eDE52e88EA8F48912dd3A')  # address del contador

account = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY"))
account2 = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY2"))
contrato = w3.eth.contract(address=contract_address, abi=abi)


def add_to_whitelist(acc):
    transaction = {
        'nonce': w3.eth.get_transaction_count(account.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': account.address

    }
    # estimo el gas que va a necesitar la transacción
    contract_data = contrato.functions.addToWhiteList(acc).build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    # ahora si buildeo la tx
    contract_data = contrato.functions.addToWhiteList(acc).build_transaction(
        transaction)
    # la firmo y mando
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get("PRIVATE_KEY"))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def get_counter():
    return contrato.functions.getCounter().call()


def increment():
    transaction = {
        'nonce': w3.eth.get_transaction_count(account.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': account.address
    }
    contract_data = contrato.functions.increment().build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato.functions.increment().build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get("PRIVATE_KEY"))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def decrement():
    transaction = {
        'nonce': w3.eth.get_transaction_count(account.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': account.address
    }

    contract_data = contrato.functions.decrement().build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato.functions.decrement().build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get("PRIVATE_KEY"))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def get_counter():
    return contrato.functions.getCounter().call({'from': account.address})

# esto es para escuchar el evento


def handle_event(event):
    print("Update:")
    print("Direccion:", event['args']['by'])
    print("Counter ahora:", event['args']['counterNow'])


event_filter = contrato.events.counterModified.create_filter(
    from_block='latest')


def listen_for_events():
    print("Esperando eventos...")
    while True:
        events = event_filter.get_new_entries()
        for event in events:
            handle_event(event)


listen_for_events()
