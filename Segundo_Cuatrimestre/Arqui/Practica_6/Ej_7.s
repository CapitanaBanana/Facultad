.data
color: .byte 70, 110, 89, 0
msj: .asciiz "Ingrese un caracter (la T): "

control: .word 0x10000
data: .word 0x10008
.code
ld $s0, control($0)
ld $s1, data($0)

daddi $t4, $0, 0x65
pedir: daddi $t0, $0, msj
sd $t0, 0($s1)
daddi $t1, $0, 4#t1 p imprimir
sd $t1, 0($s0)#imprimir mensaje

daddi $t2, $0, 8 #t2 p leer
sd $t2, 0($s0)#paso el 8
ld $t3, 0($s1)
beq $t3, $t4, seguir

j pedir
seguir: nop 
halt
