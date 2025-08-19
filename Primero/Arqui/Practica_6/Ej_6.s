.data
color: .byte 255, 0, 255, 0
control: .word 0x10000
data: .word 0x10008
msj1: .asciiz "Ingrese coordenada x "
msj2: .asciiz "Ingrese coordenada y "

.text
ld $s0, control($0)
ld $s1, data($0)


daddi $t0, $0, msj1 #pongo dir msj1 en t0
sd $t0, 0($s1)#en data cargo dir msj1

daddi $t1, $0, 4
sd $t1, 0($s0) #en control tengo 4

daddi $t3, $0, 8 #en t3 tengo 8
sd $t3, 0($s0)
lbu $t2, 0($s1)#cargo x en t2

daddi $t0, $0, msj2
sd $t0, 0($s1)#paso dir msj2 a data
sd $t1, 0($s0)#imprimir

sd $t3, 0($s0) #8 en control
lbu $t4, 0($s1)#cargo y en t4


sb $t2, 5($s1) #guardo x en data con desp 5
sb $t4, 4($s1) #cargo y en data con desp 4

lwu $t5, color($0) #en t5 pongo lo de color
sw $t5, 0($s1) #guardo en data el color

daddi $t0, $0, 5
sd $t0,0($s0) 
halt
