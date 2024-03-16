org 1000h
palabra DB "contar1#!"
DB 00h
org 3000h
ES_VOCAL: cmp AH, 97;a
 jz vocal
 cmp AH, 101;e
 jz vocal
 cmp AH, 105;i
 jz vocal
 cmp AH, 111;o
 jz vocal
 cmp AH, 117;u
 jz vocal
 cmp AH, 65;A
 jz vocal
 cmp AH, 69;E
 jz vocal
 cmp AH, 73;I
 jz vocal
 cmp AH, 79;O
 jz vocal
 cmp AH, 85;U
 jz vocal
 mov DX, 00h
 jmp fin1
 vocal: mov DX, 0ffh
 fin1: ret
CONTAR_VOC: mov CX,0
 sig: cmp byte ptr [BX], 00h
 jz fin
 mov AH, [BX]
 call ES_VOCAL
 cmp DX, 0ffh
 jnz novocal
 inc CX
 novocal: inc BX
  jmp sig
fin: ret
org 2000h
 mov BX, offset palabra
 call CONTAR_VOC
hlt
end
