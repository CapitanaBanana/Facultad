import pytest
from brownie import Votacion, accounts, config
from scripts.utils import get_account


@pytest.fixture
def votacion():
    account = get_account()
    return Votacion.deploy(3, {'from': account})


def test_deploy_votacion(votacion):
    # me fijo que la dirección no sea nula, no se me ocurre otra forma de testearlo
    assert votacion.address != "0x0000000000000000000000000000000000000000"
    assert votacion.address is not None


def test_vote(votacion):
    account = get_account()
    votacion.votar(1, {'from': account})
    assert votacion.GetGanador() == 1


def test_cant_vote_twice(votacion):
    account = get_account()
    votacion.votar(1, {'from': account})
    with pytest.raises(Exception, match="Ya votaste! sali de aca"):
        votacion.votar(1, {'from': account})


def test_invalid_votes(votacion):
    account = get_account()
    with pytest.raises(Exception, match="Voto invalido"):
        votacion.votar(4, {'from': account})
    with pytest.raises(Exception, match="Voto invalido"):
        votacion.votar(0, {'from': account})


def test_ganador_correcto(votacion):
    account = get_account()
    account2 = accounts.add(config['account_b'])
    account3 = accounts.add(config['account_c'])
    votacion.votar(1, {'from': account})
    votacion.votar(2, {'from': account2})
    votacion.votar(1, {'from':  account3})
    assert votacion.GetGanador() == 1
