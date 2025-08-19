from brownie import WalletPersonal, network
from .utils import get_account, get_wallet_personal


def deploy():
    account = get_account()
    wallet = WalletPersonal.deploy({'from': account})
    return wallet


def verify():
    wallet = WalletPersonal[-1]
    WalletPersonal.publish_source(wallet)


def withdraw(amount):
    account = get_account()
    wallet = get_wallet_personal()
    tx = wallet.withdraw(amount, {'from': account})


def deposit(amount):
    account = get_account()
    wallet = get_wallet_personal()
    tx = wallet.deposit({'from': account, 'value': amount})


def getBalance():
    wallet = get_wallet_personal()
    return wallet.balance()
