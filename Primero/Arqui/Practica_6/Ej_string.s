.data
dir_control: .word 0x10000
dir_data: .word 0x10008
valor: .asciiz "hola"

.code
ld $s0, dir_control($0)
ld $s1, dir_data($0)

daddi $t0, $0, valor #mando direc de string a data
sd $t0, 0($s1)

daddi $t0, $0, 4 #codigo de imprimir sin signo
sd $t0, 0($s0)
halt
