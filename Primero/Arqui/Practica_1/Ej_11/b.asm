org 1000h
dividendo dw 000Eh
divisor dw 0003H
org 3000h
div: mov cx, 0
 mov bx, sp
 add bx, 2 
 mov ax, [bx]; pongo dividendo en ax
 add bx, 2
 mov dx, [bx]; divisor en dx
 
 resta: cmp ax, dx
 js fin
 inc cx
 sub ax, dx
 jmp resta
 fin: mov bx, offset divisor
 add bx, 2
 mov [bx], ax;pongo resto en posicion siguiente a divisor
 mov ax, bx
 ret
org 2000h
mov ax, divisor
push ax
mov ax, dividendo
push ax
call div
mov bx, ax
mov cx, [bx]
hlt
end
