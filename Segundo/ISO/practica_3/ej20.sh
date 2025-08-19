#!bin/bash
push() {
	pila+=($1)
	echo "pila:"
	for elem in ${pila[@]}
	do
		echo $elem

	done	
	echo "--fin de pila--"
}
pop(){
	unset pila[$(expr ${#pila[*]} - 1)]
	echo "pila:"
	for elem in "${pila[@]}"
	do
		echo $elem
	done
	echo "--fin de pila--"
}

pila=()
while true; do
 select opt in push pop exit; do
	 case $opt in
		push) echo "ingrese el dato a pushear"
			read x
			push $x;;
		pop) pop;;
		exit) break 2;;
	esac
	done
done
