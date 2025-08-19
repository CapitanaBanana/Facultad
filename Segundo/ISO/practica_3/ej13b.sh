#!/bin/var
echo "Ingrese 1 para listar, 2 para saber su ubicacion y 3 para saber quien sos jajajax" 
read opcion
case $opcion in
	1) ls 
	;;
	2) pwd
	;;
	3) whoami 
esac
