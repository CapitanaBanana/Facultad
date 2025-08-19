from brownie import accounts, ContadorSimple, config
from .utils import get_account, get_contador


def deploy():
    account = get_account()
    contador = ContadorSimple.deploy({'from': account})
    return contador


def verify():
    contadorSimple = ContadorSimple[-1]
    ContadorSimple.publish_source(contadorSimple)


def increment():
    account = get_account()
    contadorSimple = get_contador()
    contadorSimple.increment({'from': account})


def decrement():
    account = get_account()
    contadorSimple = get_contador()
    contadorSimple.decrement({'from': account})


def addToWhiteList(address):
    account = get_account()
    contadorSimple = get_contador()
    contadorSimple.addToWhiteList(address, {'from': account})


def getCounter():
    contadorSimple = get_contador()
    return contadorSimple.getCounter()
