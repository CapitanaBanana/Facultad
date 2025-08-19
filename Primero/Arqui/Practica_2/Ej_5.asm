ORG 1000H
MSJ DB "INGRESE UN NUMERO:"
FIN DB ?
msjerror db "CARACTER NO VALIDO"
finerror db ?

ORG 1500H
NUM DB ?

ORG 3000H
ES_NUM: cmp NUM, 30h
js noes
cmp NUM, 40h
js es
noes: mov dx, 0ffh 
jmp fin
es: mov dx, 00h
fin: ret
 ORG 2000H
 MOV BX, OFFSET MSJ
MOV AL, OFFSET FIN-OFFSET MSJ
 INT 7
 MOV BX, OFFSET NUM
INT 6
call ES_NUM
cmp dx, 00h
jz bien
MOV BX, OFFSET msjerror
MOV AL, OFFSET finerror-OFFSET msjerror
int 7
jmp fino
 bien: MOV AL, 1
 mov bx, offset NUM
 INT 7
 MOV CL, NUM
fino: INT 0
 END 
