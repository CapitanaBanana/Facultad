#!/bin/bash
if [ $# -ne 1 ]; then
	echo error
	exit 1
fi
arch=/home/capi/ISO/1erParcial/importante/pepe.txt
while [ true ]; do
	if [ -f $arch ]; then
		break 1
	fi
	sleep 180
done

cant=$(cat $arch | grep "FATAL ERROR" | wc -l)
if [ $cant -ge 1 ]; then
	echo hola
	$(tar -czvf /home/capi/ISO/1erParcial/comprimido.tgz /home/capi/ISO/1erParcial/importante/) 
	echo "la cant de fatal error fue: $cant"
	exit 0
fi

