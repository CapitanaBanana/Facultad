#!/bin/bash
if [ $# -ne 1 ]; then
	echo "error"
	exit 1
fi
echo "" > "reporte.txt" 
persona=`cat /etc/passwd | cut -d: -f1,6`
for p in $persona ;
do
	home=$(echo $p | cut -d: -f2)
	user=$(echo $p | cut -d: -f1)
	 if [ -d $home ]; then
		 cant=$(find $home -name "*.$1" | wc -l) 
	 fi 
	 echo  "$user $cant" >> "reporte.txt"
done


