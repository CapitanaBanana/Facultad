
import pytest
from brownie import accounts, ContadorSimple, config
from scripts.utils import get_account


@pytest.fixture
def contador():
    account = get_account()
    contract = ContadorSimple.deploy({'from': account})
    contract.addToWhiteList(account.address, {'from': account})
    return contract


def test_contador_deploy(contador):
    account = get_account()
    assert contador.getCounter({'from': account}) == 0


def test_increment(contador):
    account = get_account()
    contador.increment({'from': account})
    assert contador.getCounter({'from': account}) == 1


def test_decrement(contador):
    account = get_account()
    with pytest.raises(Exception, match="El contador ya es 0"):
        contador.decrement({'from': account})
    contador.increment({'from': account})
    contador.decrement({'from': account})
    assert contador.getCounter({'from': account}) == 0


def test_increment_without_whitelist(contador):
    non_whitelisted_account = accounts.add(config['account_b'])
    with pytest.raises(Exception, match="La direccion no esta en la white list"):
        contador.increment({'from': non_whitelisted_account})


def test_decrement_without_whitelist(contador):
    non_whitelisted_account = accounts.add(config['account_b'])
    with pytest.raises(Exception, match="La direccion no esta en la white list"):
        contador.decrement({'from': non_whitelisted_account})


def test_add_to_whitelist_no_owner(contador):
    non_owner = accounts.add(config['account_b'])

    with pytest.raises(Exception, match="Ownable: caller is not the owner"):
        contador.addToWhiteList(non_owner.address, {'from': non_owner})


def test_counter_modified_event_increment(contador):
    account = get_account()
    tx = contador.increment({'from': account})

    assert "counterModified" in tx.events
    assert tx.events["counterModified"]["by"] == account.address
    assert tx.events["counterModified"]["counterNow"] == 1


def test_add_invalid_address_to_whitelist(contador):
    owner = get_account()
    with pytest.raises(Exception, match="Debe enviar una direccion valida"):
        contador.addToWhiteList(
            "0x0000000000000000000000000000000000000000", {'from': owner})
