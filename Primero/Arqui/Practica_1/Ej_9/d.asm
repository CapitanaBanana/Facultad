org 1000h
byt db 10010100b
n db 6
org 300h
ROTARDER: mov ch, [bx]
mov dl, 8
sub dl, dh
loop: add ch, ch
adc ch, 0
dec dl
jnz loop
mov byt, ch
ret

org 2000h
mov bx, offset byt
mov dh, n
call ROTARDER
hlt
end
