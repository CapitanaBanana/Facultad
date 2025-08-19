#!/bin/bash
arreglo=(1 2 3 4 5 6 7 8 9 10 11 12)
cant=0
for num in ${arreglo[@]}; do
	if [ $((num % 2)) -eq 0 ]; then
	       echo $num
       else
	cant=$((cant + 1))
	fi
done
echo "cant= $cant"
