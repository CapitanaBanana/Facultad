from brownie import Votacion, network
from .utils import get_account, get_votacion


def deploy(cant_candidatos):
    account = get_account()
    votacion = Votacion.deploy(cant_candidatos, {'from': account})
    return votacion


def verify():
    votacion = Votacion[-1]
    Votacion.publish_source(votacion)


def votar(candidato):
    account = get_account()
    votacion = get_votacion()
    tx = votacion.votar(candidato, {'from': account})


def getGanador():
    votacion = get_votacion()
    return votacion.getGanador()
