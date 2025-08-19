ORG 1000H
a DB 41h

ORG 2000H
 MOV BX, OFFSET a
 MOV AL, 1
 mov ah, 26
 imp: INT 7
  add a, 20h
  int 7
  dec ah
  jz fin
  sub a, 20h
  inc a
  jmp imp
 fin: INT 0
 END 
