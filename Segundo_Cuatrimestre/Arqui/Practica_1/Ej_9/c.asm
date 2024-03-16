org 1000h
N db 6h
byt db 10010100b
org 3000h
ROTARIZQ:  add ah, ah
  adc ah,0
  mov byt, ah
  ret
ROTARIZQ_N: cmp ch, 0
  jz fin
  call ROTARIZQ
  dec ch
  jmp ROTARIZQ_N
  fin: ret
ROTARDER_N: mov cl,ch
 mov ch,8
 sub ch, cl
 call ROTARIZQ_N
 ret
org 2000h
mov ch, N
mov ah, byt
call ROTARDER_N
hlt
end
