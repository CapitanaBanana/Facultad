.data
A: .word 2
B: .word 3
RES: .word 0
.code
ld r1, A(r0)
ld r2, B(r0)
dadd r3, r0, r0

loop: daddi r2, r2, -1
dadd r3, r3, r1
bnez r2, loop
nop
sd r3, RES(r0)
halt
