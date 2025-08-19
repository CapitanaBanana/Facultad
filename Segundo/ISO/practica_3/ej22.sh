#!/bin/bash
num=(10 3 5 7 9 3 5 4)
productoria(){
	res=${num[0]}
	i=1
	while [ $i -lt ${#num[@]} ]; do
		res=$((num[i] * res))
		i=$((i+1))
	done
	echo $res
}
echo "${num[*]}"
resultado=$(productoria)
echo "resultado= $resultado"
