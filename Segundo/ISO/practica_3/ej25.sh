#!/bin/bash
if [ $# -eq 0 ]; then
	echo "error"
	exit 1
fi
usuarios=()
users=$(cat /etc/passwd | cut -d: -f 1,4)
for u in $users; do
	nombre=$(echo $u | cut -d: -f1)
	grupo=$(echo $u | cut -d: -f2)
	if [ "$grupo" == 1001 ]; then
		usuarios+=($nombre)
	
	fi
done

case $1 in
	"-b") if [ ${#usuarios[*]} -ge $2 ]; then
		echo ${usuarios[$2]}
	else
		echo error
	fi
	;;
"-l") echo "longitud ${#usuarios[*]}"
	;;
"-i") echo ${usuarios[*]}
	;;
esac
