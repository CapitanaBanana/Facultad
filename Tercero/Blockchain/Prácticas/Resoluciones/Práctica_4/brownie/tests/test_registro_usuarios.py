import pytest
from brownie import RegistroDeUsuarios, accounts, config
from scripts.utils import get_account


@pytest.fixture
def registro_de_usuarios():
    account = get_account()
    registro = RegistroDeUsuarios.deploy({'from': account})
    return registro


def test_deploy_registro_de_usuarios(registro_de_usuarios):
    assert registro_de_usuarios.address != "0x0000000000000000000000000000000000000000"
    assert registro_de_usuarios.address is not None


def test_registrar_usuario(registro_de_usuarios):
    account = get_account()
    nombre = "CapitanaBanana"
    tx = registro_de_usuarios.registrarse(nombre, {'from': account})
    assert registro_de_usuarios.estaRegistrado(account.address) == True


def test_registro_multiple_no_permitido(registro_de_usuarios):
    account = get_account()
    nombre = "CapitanaBanana"
    registro_de_usuarios.registrarse(nombre, {'from': account})
    with pytest.raises(Exception, match=".*Ya estas registrado.*"):
        registro_de_usuarios.registrarse(nombre, {'from': account})


def test_esta_registrado(registro_de_usuarios):
    account = get_account()
    nombre = "CapitanaBanana"
    registro_de_usuarios.registrarse(nombre, {'from': account})
    assert registro_de_usuarios.estaRegistrado(account.address) == True
    una_direccion = accounts.add(config['account_b']).address
    assert registro_de_usuarios.estaRegistrado(una_direccion) == False


def test_get_usuario(registro_de_usuarios):
    account = get_account()
    nombre = "CapitanaBanana"
    registro_de_usuarios.registrarse(nombre, {'from': account})
    assert registro_de_usuarios.getUsuario(account.address) == nombre
    una_direccion = accounts.add(config['account_b']).address
    assert registro_de_usuarios.getUsuario(
        una_direccion) == "Usuario no encontrado"
