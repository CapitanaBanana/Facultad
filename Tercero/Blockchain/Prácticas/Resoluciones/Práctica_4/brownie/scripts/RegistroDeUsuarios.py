from brownie import RegistroDeUsuarios, network
from .utils import get_account, get_registro_de_usuarios


def deploy():
    account = get_account()
    registro = RegistroDeUsuarios.deploy({'from': account})
    return registro


def verify():
    registro = RegistroDeUsuarios[-1]
    RegistroDeUsuarios.publish_source(registro)


def registrar_usuario(nombre):
    account = get_account()
    registro = get_registro_de_usuarios()
    tx = registro.registrarse(nombre, {'from': account})


def esta_registrado(account):
    registro = get_registro_de_usuarios()
    tx = registro.estaRegistrado(account.address)
