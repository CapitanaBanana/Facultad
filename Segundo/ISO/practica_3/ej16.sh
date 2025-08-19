#!/bin/bash
if [ $# -ne 1 ]; then
    echo "error"
    exit 1
fi
for linea in $(cat /etc/passwd | cut -d: -f 1,6); do
	user=$(echo $linea | cut -d: -f1)
	home=$(echo $linea | cut -d: -f2)
done
