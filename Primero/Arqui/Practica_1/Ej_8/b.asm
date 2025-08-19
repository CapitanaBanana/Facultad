org 1000h
palabra DB "azBcDE1#!"
DB 00h
org 3000h
CONTAR_MIN: mov CX,0
 sig: cmp byte ptr [BX], 00h
 jz fin
 cmp byte ptr [BX], 123
 jns novocal
 cmp byte ptr [BX], 97
 js novocal
 inc CX
 novocal: inc BX
 jmp sig
fin: ret
org 2000h
 mov BX, offset palabra
 call CONTAR_MIN
hlt
end
