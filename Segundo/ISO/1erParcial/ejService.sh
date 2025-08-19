#!/bin/bash
if [ $# -ne 1 ]; then
	echo error
	exit 1
fi
if [ -z $SERVICE ]; then
	SERVICE="cups"
fi
archivos=($(find /var/log/$SERVICE -type f))
cant=0
for a in ${archivos[*]}; do
	if [ $(cat $a | grep $1 | wc -l) -ge 1 ]; then
		let cant++
	fi
done
echo "cant de archovs con $1: $cant"
exit 0
