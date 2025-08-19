#!/bin/bash
usuarios=$(cat /etc/passwd | cut -d: -f 1,7)
cumplen=()
for l in $usuarios; do
	bash=$(echo $l | cut -d: -f 2)
	if [ $bash = "/bin/bash" ]; then
		user=$(echo $l | cut -d: -f 1)
		if [ $(find /home/$user/mailDir -maxdepth 1 -type d) ]; then
			cumplen+=($user)
		fi
	fi
done
echo ${cumplen[*]} > /home/capi/listado.txt

