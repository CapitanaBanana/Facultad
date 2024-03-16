.data
cant: .word 10
CANT: .word 0
X: .word 5
TABLA: .word 10, 9, 8, 5, 5, 4, 5, 4, 3
RES: .word 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
.code
ld r3, X(r0)
dadd r1, r0, r0
dadd r2, r0, r0
daddi r4, r0, 1
ld r6, cant(r0)
loop: ld r7, TABLA(r2)
mtc1 r7, f1
mtc1 r3, f2
c.le.d f2, f1
dadd r5, r0, r0
bc1f esmenor
dadd r5, r5, r4
daddi r1, r1, 1
esmenor: sd r5, RES(r2)
daddi r2, r2, 8
daddi r6, r6, -1
bnez r6, loop
sd r1, CANT(r0)
halt
