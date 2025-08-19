#!/bin/bash
archivos=$(ls /home/capi| grep ".sh")
if [ $(find /home/capi -type d -name "bin" | wc -l) -lt 1 ]; then
	$(mkdir /home/capi/bin)
fi
movidos=0
for a in $archivos; do
	let movidos++
	echo "movi: $a"
	$(mv /home/capi/$a /home/capi/bin)
done
echo "cantidad de archivos movidos= $movidos"

