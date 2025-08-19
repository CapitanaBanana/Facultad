org 1000h
original DB 98
reemplazo DB 97
palabra DB "abbcde#!"
DB 00h
org 3000h
REEMPLAZAR_CAR: mov ch,0
 mov bx, sp
 add bx, 2;posiciono en letra original
 mov ah, [bx];pongo letra en ah
 add bx, 2
 mov al, [bx];idem con reemplazo
 add bx, 2;posiciono en el primero de los caracteres
 mov bx, [BX];pongo en bx la direc efectiva del primer caracter
 sig: cmp byte ptr [BX], 00h
 jz fin
 cmp byte ptr [BX], ah
 jnz noes
 mov [bx], al
 noes: inc BX
 jmp sig
fin: ret
org 2000h
 mov AX, offset palabra
 push AX
 mov AX, reemplazo 
 push AX
 mov AX, original
 push AX
 call REEMPLAZAR_CAR
hlt
end
