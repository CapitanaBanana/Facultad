org 1000h
dividendo dw 000Fh
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
 fin: push cx
 sub bx, 4
 mov bx, [bx]
 push bx
 ret
org 2000h
mov ax, divisor
push ax
mov ax, dividendo
push ax
call div
pop ax
hlt
end
