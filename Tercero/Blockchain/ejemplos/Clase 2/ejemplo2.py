# pip install web3
from web3 import Web3


def generate_contract_address(account_address: str, nonce: int) -> str:
    """
    Genera la dirección de un contrato Ethereum a partir de la dirección del creador y un nonce.
    """
    rlp_encoded = Web3.to_bytes(hexstr=account_address) + Web3.to_bytes(nonce)
    contract_address = Web3.keccak(rlp_encoded)[-20:].hex()
    return contract_address


# Dirección de la cuenta creadora (ejemplo)
account_address = '0x742d35Cc6634C0532925a3b844Bc454e4438f44e'
nonce = 1
contract_address = generate_contract_address(account_address, nonce)
print(f"Contract Address: {contract_address}")
