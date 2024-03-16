.data
num1: .word 0
num2: .word 0
control: .word 0x10000
data: .word 0x10008
msj: .asciiz "Ingresar Numero "
.code
ld $s0,control($0)
ld $s1, data ($0)
daddi $a0,$0,num1
jal ingreso
daddi $a0,$0,num2
jal ingreso
jal resultado
halt
ingreso: daddi $t0,$0,msj#mandamos mensaje a data
sd $t0,0($s1)

daddi $t0,$0,4#damos la instruccion de imprimir mensaje
sd $t0, 0($s0)

daddi $t0,$0,8#pedimos numero
sd $t0,0($s0)
ld $t1,0($s1)
daddi $t3,$0,1#para chekear si se activo la flag
slti $t2,$t1,0
beq $t2,$t3,ingreso
daddi $t3,$0,0
slti $t2,$t1,9
beq $t2,$t3,ingreso
sd $t1,0($a0)
jr $ra

resultado: ld $t0,num1($0)
ld $t1,num2($0)
dadd $t2,$t1,$t0
sd $t2,0($s1)
daddi $t0,$0,1
sd $t0,0($s0)
jr $ra