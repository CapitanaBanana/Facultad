#!/bin/bash
while true; do
	select opt in ej12 ej13 ej16 ej17 ej18 exit; do
		case $opt in
			ej12) echo `bash ej12`;;
			ej13) echo `bash ej13a`;;
			ej16) echo `bash ej16.sh`;;
			ej17) echo `bash ej17.sh`;;
			ej18) echo `bash ej18.sh`;;
			exit) break 2
		esac
	done
done
