from brownie import MuroDeMensajes, accounts, config, network
from .utils import get_account, get_registro_de_usuarios, get_muro_de_mensajes


def deploy():
    account = get_account()
    registro = get_registro_de_usuarios()
    muro = MuroDeMensajes.deploy(registro.address, {'from': account})
    return muro


def verify():
    muro = MuroDeMensajes[-1]
    MuroDeMensajes.publish_source(muro)


def dejarMensaje(mensaje):
    account = get_account()
    muro = get_muro_de_mensajes()
    tx = muro.dejarMensaje(mensaje, {'from': account})


def leer_mensajes():
    muro = get_muro_de_mensajes()
    mensajes = muro.leerMensajes()
    return (mensajes)
