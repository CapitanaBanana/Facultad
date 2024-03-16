#!/bin/bash
if [ $# -lt 1 ]; then
	echo "error. ingrese al menos un usuario."
	exit 1
fi
esta_logueado(){
	users=$(who)
	for user in $users; do
		if [ "$1" = $user ]; then
			return 0
		fi
	done
	return 1
}
cant_procesos(){
	cant=$(ps -u $1 | wc -l)
	return $cant
}
uso_de_procesos(){
	if [ $1 -gt 50 ]; then
		return 0
	fi
	return 1
}
touch 
for p in $*; do
	echo "el user $p"       
	esta_logueado $p
	if [ $? -eq 0 ]; then
		echo "esta logueado"
		cant_procesos $p
		cant=$?
		echo "tiene $cant procesos"
		uso_de_procesos $cant
		if [ $? -eq 0 ]; then
			echo "tiene mas de 50 procesos"
			echo $p $(date) >> log.txt
		fi
	else
	echo "no esta logueado"
	fi
done
