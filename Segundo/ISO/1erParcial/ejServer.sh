#!/bin/bash
if [ $# -lt 2 ]; then
	echo "tiene que mandar al menos la fecha y la ip"
	exit 1
fi
if [ ! -e /home/capi/ISO/1erParcial/access.$1.log ]; then
	echo "no existe un archivo para el dia ingresado"
	exit 1
fi
for ip in $*; do
	if [ "$ip" != "$1" ]; then
		cant=$(cat /home/capi/ISO/1erParcial/access.$1.log | grep $ip | wc -l)
		echo $ip $cant
	fi
done
