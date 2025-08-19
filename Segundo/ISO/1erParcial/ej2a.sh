#!/bin/bash
if [ $# -lt 1 ]; then
	echo error
	exit 3
fi
cant=0
for p in $*; do
	if [ -f $p ]; then
		$(zip file.zip $p)
	elif [ -d $p ]; then
		if [ -r $p ]; then
			$(tar -zvcf archivo.tar $p)
		elif [ -w $p ]; then
			$(rmdir $p)
		fi
	else
		let cant++
	fi
done
echo "cantidad= $cant"
