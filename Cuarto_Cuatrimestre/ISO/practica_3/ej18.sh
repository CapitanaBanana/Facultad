#!/bin/bash
if [ $# -ne 1 ]; then
	echo "jaja error pt"
	exit 1
fi
while true; do
	if `echo who` | grep -q "$1"; then
		echo "usuario logueado"
		exit 0
	fi
	sleep 10
	echo "todavia no se logueo"
done	
