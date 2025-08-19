from web3 import Web3
from dotenv import load_dotenv
import os
load_dotenv()
NODE = os.environ.get("NODE")
w3 = Web3(Web3.HTTPProvider(NODE))
abi_registro = """[
			{
				"inputs": [
					{
						"internalType": "address",
						"name": "sender",
						"type": "address"
					}
				],
				"name": "estaRegistrado",
				"outputs": [
					{
						"internalType": "bool",
						"name": "",
						"type": "bool"
					}
				],
				"stateMutability": "view",
				"type": "function"
			},
			{
				"inputs": [
					{
						"internalType": "address",
						"name": "direccion",
						"type": "address"
					}
				],
				"name": "getUsuario",
				"outputs": [
					{
						"internalType": "string",
						"name": "nombre",
						"type": "string"
					}
				],
				"stateMutability": "view",
				"type": "function"
			},
			{
				"inputs": [
					{
						"internalType": "string",
						"name": "nombre",
						"type": "string"
					}
				],
				"name": "registrarse",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			}
		]"""

abi_muro = """[
			{
				"inputs": [
					{
						"internalType": "address",
						"name": "_registroDeUsuarios",
						"type": "address"
					}
				],
				"stateMutability": "nonpayable",
				"type": "constructor"
			},
			{
				"inputs": [
					{
						"internalType": "string",
						"name": "mensaje",
						"type": "string"
					}
				],
				"name": "dejarMensaje",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			},
			{
				"inputs": [],
				"name": "leerMensajes",
				"outputs": [
					{
						"components": [
							{
								"internalType": "string",
								"name": "usuario",
								"type": "string"
							},
							{
								"internalType": "string",
								"name": "contenido",
								"type": "string"
							}
						],
						"internalType": "struct MuroDeMensajes.Mensaje[]",
						"name": "",
						"type": "tuple[]"
					}
				],
				"stateMutability": "view",
				"type": "function"
			}
		]"""
# address del contrato de registro de usuarios
registro_usuarios = w3.to_checksum_address(
    '0xFAb0D94d583C9AF3C877a14eeec483fB64BAdb05')

# address del contrato de muro de mensajes
muro_mensajes = w3.to_checksum_address(
    '0x6448c52Bbb5C034632DbDE5aDb71b7AcC8aE9455')

account = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY"))
account2 = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY2"))
account3 = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY3"))

contrato_registro = w3.eth.contract(
    address=registro_usuarios, abi=abi_registro)
contrato_muro = w3.eth.contract(address=muro_mensajes, abi=abi_muro)


def registrarse(user, nombre, key):
    transaction = {
        'nonce': w3.eth.get_transaction_count(user.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': user.address
    }
    contract_data = contrato_registro.functions.registrarse(nombre).build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato_registro.functions.registrarse(nombre).build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get(key))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def dejarMensaje(user, mensaje, key):
    transaction = {
        'nonce': w3.eth.get_transaction_count(user.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'from': user.address
    }
    contract_data = contrato_muro.functions.dejarMensaje(mensaje).build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato_muro.functions.dejarMensaje(mensaje).build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get(key))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def leerMensajes():
    return contrato_muro.functions.leerMensajes().call()
