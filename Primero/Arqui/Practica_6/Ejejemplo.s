.data
dir_control: .word 0x10000
dir_data: .word 0x10008
valor: .word -25

.code
ld $s0, dir_control($0)
ld $s1, dir_data($0)

ld $t0, valor($0)
sd $t0, 0($s1)

daddi $t0, $0, 2 #codigo de imprimir sin signo
sd $t0, 0($s0)
halt
