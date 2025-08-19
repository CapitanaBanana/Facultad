org 1000h
letra DB "a"
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
 mov CX, 00h
 jmp fin
 vocal: mov CX, 0ffh
fin: ret
org 2000h
 mov AH, letra
 call ES_VOCAL
hlt
end
