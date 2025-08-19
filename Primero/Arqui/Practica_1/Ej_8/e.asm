org 1000h
letra DB 98
palabra DB "abbcde#!"
DB 00h
org 3000h
CONTAR_CAR: mov ch,0
 mov bx, sp
 add bx, 2;posiciono en letra
 mov ah, [bx];pongo letra en ah
 add bx, 2;posiciono en el primero de los caracteres
 mov bx, [BX];pongo en bx la direc efectiva del primer caracter
 sig: cmp byte ptr [BX], 00h
 jz fin
 cmp byte ptr [BX], ah
 jnz noes
 inc Ch
 noes: inc BX
 jmp sig
fin: ret
org 2000h
 mov AX, offset palabra
 push AX
 mov AX, letra
 push AX
 call CONTAR_CAR
hlt
end
