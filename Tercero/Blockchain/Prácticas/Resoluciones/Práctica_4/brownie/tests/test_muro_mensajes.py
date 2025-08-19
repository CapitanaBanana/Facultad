
import pytest
from brownie import MuroDeMensajes, MockRegistroDeUsuarios, accounts, config
from scripts.utils import get_account, get_muro_de_mensajes, get_registro_de_usuarios


@pytest.fixture
def mock_registro_de_usuarios():
    account = get_account()
    mock_registro = MockRegistroDeUsuarios.deploy({'from': account})
    return mock_registro


@pytest.fixture
def muro_de_mensajes(mock_registro_de_usuarios):
    account = get_account()
    muro = MuroDeMensajes.deploy(
        mock_registro_de_usuarios.address, {'from': account})
    return muro


def test_deploy_muro_de_mensajes(muro_de_mensajes):
    assert muro_de_mensajes.address != "0x0000000000000000000000000000000000000000"
    assert muro_de_mensajes.address is not None


def test_dejar_mensaje(muro_de_mensajes, mock_registro_de_usuarios):
    account = get_account()
    mensaje = "hola mundo"
    mock_registro_de_usuarios.registrarse("CapitanaBanana", {'from': account})
    assert mock_registro_de_usuarios.estaRegistrado(account.address) == True
    tx = muro_de_mensajes.dejarMensaje(mensaje, {'from': account})
    # 1 significa que la transacción fue exitosa, en el de abajo pruebo leerlo
    assert tx.status == 1


def test_leer_mensajes(muro_de_mensajes, mock_registro_de_usuarios):
    account = get_account()
    mensaje = "hola mundo"
    mock_registro_de_usuarios.registrarse("CapitanaBanana", {'from': account})
    muro_de_mensajes.dejarMensaje(mensaje, {'from': account})
    mensajes = muro_de_mensajes.leerMensajes()
    assert ('CapitanaBanana', mensaje) in mensajes


def test_fallo_dejar_mensaje_sin_registro(muro_de_mensajes, mock_registro_de_usuarios):
    account = accounts.add(config['account_b'])
    mensaje = "mensaje de prueba"
    with pytest.raises(Exception, match="Tenes que registrarte para dejar un mensaje"):
        muro_de_mensajes.dejarMensaje(mensaje, {'from': account})
