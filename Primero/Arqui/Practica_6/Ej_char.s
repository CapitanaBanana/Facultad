.data
dir_control: .word 0x10000
dir_data: .word 0x10008

.code
ld $s0, dir_control($0)
ld $s1, dir_data($0)

daddi $t0, $0, 9 #pongo en control el 9
sd $t0, 0($s0) #mando 9 a ctrol

lbu $t1, 0($s1) #lee char
halt
