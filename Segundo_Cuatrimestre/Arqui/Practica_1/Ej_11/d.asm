org 1000h
num1 dw 0036h,7219h
num2 dw 0032h,1232h
org 300h
SUM32: mov bx,sp;
add bx, 2
mov ax,[bx];en ax esta num2l
add bx, 2
add ax, [bx]; le sumo a ax num1l
adc dx, 0
add bx, 2
add dx, [bx]; le sumo a dx num2h
add bx,2
add dx,[bx]; le sumo a dx num1h
mov cx, bx
mov bx, num2
add bx, 5
mov [bx], dx;parte alta
push bx
add bx, 2
mov [bx], ax;parte baja
push bx
mov bx, cx
sub bx, 8
mov bx, [bx]
push bx
ret
org 2000h
mov bx, offset num1
mov ax, [bx];paso a ax parte alta de num1
push ax;push num1h
mov bx, offset num2
mov ax, [bx]
push ax; push num2h
mov bx, offset num1 +2
mov cx, [bx]; pongo en cx parte baja de num1
push cx; push num1l
mov bx, offset num2+2
mov cx, [bx]; paso parte baja de num2
push cx;push num2L
call SUM32;
pop bx
mov ax, [bx]
pop bx
mov bx, [bx]
hlt
end
