#!/bin/sh

if [ $(id -u) -ne 0 ]; then
    echo Ejecutar con root
    echo "\t- sudo $0"
    echo "\t- su -c $0"
    exit 1
fi

apt-get update
apt-get install -yy git build-essential libpth-dev strace python3 python3-venv htop podman
