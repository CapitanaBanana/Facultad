from web3 import Web3
from dotenv import load_dotenv
import os
load_dotenv()
NODE = os.environ.get("NODE")
w3 = Web3(Web3.HTTPProvider(NODE))
abi = """[
			{
				"inputs": [
					{
						"internalType": "uint128",
						"name": "_cantCandidatos",
						"type": "uint128"
					}
				],
				"stateMutability": "nonpayable",
				"type": "constructor"
			},
			{
				"inputs": [],
				"name": "GetGanador",
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
				"inputs": [
					{
						"internalType": "uint128",
						"name": "voto",
						"type": "uint128"
					}
				],
				"name": "votar",
				"outputs": [],
				"stateMutability": "nonpayable",
				"type": "function"
			}
		]"""

contract_address = w3.to_checksum_address(
    '0x7899790bc066dd8C2A4CbF42220B55DC9f8BdCDC')  # address del contrato

account = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY"))
account2 = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY2"))
account3 = w3.eth.account.from_key(os.environ.get("PRIVATE_KEY3"))
contrato = w3.eth.contract(address=contract_address, abi=abi)


def votar(voto, votante, key):
    transaction = {
        'nonce': w3.eth.get_transaction_count(votante.address),
        'gasPrice': int(w3.eth.gas_price * 1.2),
        'chainId': 11155111,
        'gas': 1000000,
        'from': votante.address

    }
    contract_data = contrato.functions.votar(voto).build_transaction(
        transaction)
    estimated_gas = w3.eth.estimate_gas(contract_data)
    transaction['gas'] = estimated_gas
    contract_data = contrato.functions.votar(voto).build_transaction(
        transaction)
    signed_txn = w3.eth.account.sign_transaction(
        contract_data, os.environ.get(key))
    tx_hash = w3.eth.send_raw_transaction(signed_txn.raw_transaction)
    return tx_hash.hex()


def getGanador():
    return contrato.functions.GetGanador().call()
