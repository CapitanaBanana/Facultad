#!/bin/bash
i=0
for linea in $(ls); do
	echo "$linea" | tr 'a-zA-Z' 'A-Za-z' | tr -d 'A' | tr -d 'a'
done       
