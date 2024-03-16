#!/bin/bash
if [ $# -lt 1 ]; then
	echo "error, debe enviar al menos 1 parametro"
	exit 1
fi
i=1
no=0
for p in $*;
do
	
	if [ $((i % 2)) -eq 0 ]; then
		if [ -d $p ]; then
			echo "$p es directorio"	
		elif [ -f $p ]; then
			echo "$p es archivo"
		else
			echo "$p no existe"
			let no++
		fi
	fi
	let i++
done
echo "la cantidad de archivos que no estaban fue $no"
