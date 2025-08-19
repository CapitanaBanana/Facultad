from brownie import accounts, config, network, ContadorSimple, MuroDeMensajes, RegistroDeUsuarios, WalletPersonal, Votacion


def get_account():
    if network.show_active() == "development":
        return accounts[0]
    elif network.show_active() == "sepolia":
        return accounts.add(config['deployer_sepolia'])


def get_contador():
    deployed_contadores = ContadorSimple
    if len(deployed_contadores) > 0:
        return deployed_contadores[-1]
    else:
        account = get_account()
        contador = ContadorSimple.deploy({'from': account})
        return contador


def get_muro_de_mensajes():
    deployed_muros = MuroDeMensajes
    if len(deployed_muros) > 0:
        return deployed_muros[-1]
    else:
        account = get_account()
        muro = MuroDeMensajes.deploy({'from': account})
        return muro


def get_registro_de_usuarios():
    deployed_registros = RegistroDeUsuarios
    if len(deployed_registros) > 0:
        return deployed_registros[-1]
    else:
        account = get_account()
        registro = RegistroDeUsuarios.deploy({'from': account})
        return registro


def get_wallet_personal():
    deployed_wallets = WalletPersonal
    if len(deployed_wallets) > 0:
        return deployed_wallets[-1]
    else:
        account = get_account()
        wallet = WalletPersonal.deploy({'from': account})
        return wallet


def get_votacion():
    deployed_votaciones = Votacion
    if len(deployed_votaciones) > 0:
        return deployed_votaciones[-1]
    else:
        account = get_account()
        votacion = Votacion.deploy({'from': account})
        return votacion
