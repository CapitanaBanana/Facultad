.data
dir_control: .word 0x10000
dir_data: .word 0x10008

.code
ld $s0, dir_control($0)
ld $s1, dir_data($0)

daddi $t0, $0, 8 #pongo en control el 8

sd $t0, 0($s0)
ld $t1, 0($s0) #leo num en t1

sd $t0, 0($s0)
ld $t2, 0($s0) #leo num en t2

dadd $t3, $t1, $t2
sd $t3,0($s1)

daddi $t0, $0, 2 #pongo en control el 8
sd $t0, 0($s0) #mando a imprimir
halt
