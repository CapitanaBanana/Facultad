org 1000h
palabra DB "abcd"
DB 00h
org 3000h
longitud: cmp byte ptr [BX], 00h
 jz fin
 mov CX,0
 sig: inc BX
  inc CX
  cmp byte ptr [BX], 00h
  jnz sig
fin: ret
org 2000h
 mov BX, offset palabra
 call longitud
hlt
end
~~~~~~~~~~~~~~~~~~~~~~~~~~OPCION 2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
org 1000h
palabra DB "abcd"
DB 00h
org 3000h
longitud: mov CX,0
 sig: cmp byte ptr [BX], 00h
 jz fin
 inc BX
  inc CX
  jmp sig
fin: ret
org 2000h
 mov BX, offset palabra
 call longitud
hlt
end
