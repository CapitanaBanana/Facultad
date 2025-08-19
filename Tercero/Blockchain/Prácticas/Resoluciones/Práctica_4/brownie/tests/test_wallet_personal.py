
import pytest
from brownie import accounts, WalletPersonal, config
from scripts.utils import get_account


@pytest.fixture
def wallet():
    account = get_account()
    return WalletPersonal.deploy({'from': account})


def test_deploy(wallet):
    owner = wallet.owner()
    assert owner == get_account()


def test_deposit(wallet):
    account = get_account()
    deposit_amount = 1e17  # 0.1 ETH en wei
    tx = wallet.deposit({'from': account, 'value': deposit_amount})
    contract_balance = wallet.balance()
    assert contract_balance == deposit_amount


def test_deposti_without_value(wallet):
    account = get_account()
    with pytest.raises(Exception, match="Debe ingresar un valor"):
        wallet.deposit({'from': account})


def test_withdraw(wallet):
    account = get_account()
    deposit_amount = 1e17
    wallet.deposit({'from': account, 'value': deposit_amount})
    withdraw_amount = 1e17  # 0.1 ETH en wei
    wallet.withdraw(withdraw_amount, {'from': account})
    contract_balance = wallet.balance()
    assert contract_balance == deposit_amount - withdraw_amount


def test_withdraw_insufficient_funds(wallet):

    account = get_account()
    deposit_amount = 1e17  # 0.1 ETH en wei
    wallet.deposit({'from': account, 'value': deposit_amount})
    withdraw_amount = 2e18  # 2 ETH en wei
    with pytest.raises(Exception, match="Fondos insuficientes en el contrato"):
        wallet.withdraw(withdraw_amount, {'from': account})


def test_only_owner_can_withdraw(wallet):
    non_owner = accounts.add(config['account_b'])
    deposit_amount = 1e17  # 0.1 ETH en wei
    wallet.deposit({'from': get_account(), 'value': deposit_amount})
    with pytest.raises(Exception, match="Ownable: caller is not the owner"):
        wallet.withdraw(deposit_amount, {'from': non_owner})


def test_balance_function(wallet):
    account = get_account()
    deposit_amount = 1e17  # 0.1 ETH en wei
    wallet.deposit({'from': account, 'value': deposit_amount})
    assert wallet.balance() == deposit_amount
