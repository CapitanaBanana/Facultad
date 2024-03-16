.data
control: .word 0x10000
dato: .word 0x10008
msj: .asciiz "Ingrese un numero: "
CERO: .asciiz "CERO"
UNO: .asciiz "UNO"
DOS: .asciiz "DOS"
TRES: .asciiz "TRES"
CUATRO: .asciiz "CUATRO"
CINCO: .asciiz "CINCO"
SEIS: .asciiz "SEIS"
SIETE: .asciiz "SIETE"
OCHO: .asciiz "OCHO"
NUEVE: .asciiz "NUEVE"
.code
  ld $s0, control($0)
  ld $s1, dato($0)
  daddi $a0, $0, msj #direc de cadena en a0
  jal ingreso
  jal muestra
halt

ingreso: daddi $t0, $0, 4
sd $a0, 0($s1)
sd $t0, 0($s0) #imprimo
daddi $t0, $0, 8
sd $t0, 0($s0)#pido car
ld $v0, 0($s1)
slti $t2, $v0, -1
bnez $t2, ingreso
slti $t2, $v0, 10
beqz $t2, ingreso
jr $ra

muestra: daddi $t0, $0, CERO
daddi $t1, $0, 8
dmul $t2, $v0, $t1#en t2 tengo el desp a usar
dadd $t0, $t0, $t2
sd $t0, 0($s1)
daddi $t0, $0, 4
sd $t0, 0($s0)
jr $ra
