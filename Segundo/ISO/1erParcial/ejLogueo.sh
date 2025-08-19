#!/bin/bash
if [ $# -ne 1 ]; then
	exit 1
fi
usuarios=($(cat /etc/passwd | cut -d: -f1))
esta=" "
for u in ${usuarios[*]}; do
	if [ $1 = $u ]; then
		esta=(si)
	fi
done
if [ "$esta" != "si" ]; then
	exit 1
fi
logueados=($(who| cut -d" " -f1))
cant=0
while true ; do
	for user in ${logueados[*]}; do
		if [ $user = $1 ]; then
			echo "$user $(date)" >> $user.log
			let cant++
			echo wiiji
		fi
	done
	if [ $cant -eq 30 ]; then
		exit 0
	fi
	sleep 1
done
