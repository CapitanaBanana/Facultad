#!/bin/bash
if [ $# -ne 1 ]; then
	echo "jaja pt"
	exit 4
fi
r=0
w=0
if [ $(find -type d -name "$1" | wc -l) -eq 1 ]; then
	files=$(ls -l "$1" | cut -d- -f2)
	for f in $files; do
		if [[ $f == *"w"* ]] ; then
			let w++
		fi
		if [[ $f == *"r"* ]]; then
			let r++
		fi
	done
	echo "read= $r"
	echo "write= $w"
else
	echo "$1"
	echo "no encuentro"
	exit 4
fi

