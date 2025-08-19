org 1000h
A dW 13C7H
B dw 7a5cH
org 3000h
swap: mov bx,sp
 add bx, 2
 mov bx, [bx]
 mov dx, bx;en dx tengo la direc de b
 mov ax, [bx]; en ax tengo el valor de b
 mov bx, sp
 add bx, 4
 mov bx, [bx]
 mov cx, [bx]; en cx tengo valor de a
 mov [bx], ax; pongo b en direccion de a
 mov bx, dx; pongo direccion de b en bx
 mov [bx], cx; pongo a en b
 ret
org 2000h
mov ax, offset A
push ax
mov ax, offset B
push ax
call swap
hlt
end
