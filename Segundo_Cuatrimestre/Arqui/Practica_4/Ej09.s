.data
a: .word 2
x: .word 4
y: .word 3
RES: .word 0
.code
ld r1, a(r0)
ld r2, x(r0)
ld r3, y(r0)

loop: daddi r1, r1, -1
dadd r2, r2, r3

bnez r1, loop
nop
sd r2, RES(r0)
halt
