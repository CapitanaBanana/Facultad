ORG 1000H
num DB 01h

ORG 2000H
 MOV BX, OFFSET num
 MOV AL, 1
 mov ah, 0ffh
 loop: INT 7
  mov cx, [bx]
  inc cx; digito siguiente
  mov num, cx
  mov bx, offset num
  dec ah
  jnz loop
 INT 0
 END 
