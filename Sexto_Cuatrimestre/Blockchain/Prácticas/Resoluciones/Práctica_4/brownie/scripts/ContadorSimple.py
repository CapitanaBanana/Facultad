from brownie import accounts, ContadorSimple, config


def deploy():
    account = accounts.add(config["account_a"])
    contador = ContadorSimple.deploy({'from': account})
    tx = contador.addToWhiteList(account, {'from': account})
    tx.wait(1)
    tx = contador.increment({'from': account})
    tx.wait(1)
    print(contador.getCounter())


def verify():
  contadorSimple= ContadorSimple[-1]
  ContadorSimple.publish_source(contadorSimple)