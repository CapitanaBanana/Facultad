.data
a: .word 3
b: .word 3
c: .word 3
D: .word 0
.code
ld r1, a(r0)
ld r2, b(r0)
ld r3, c(r0)
dadd r4, r0, r0
bne r1,r2, seguir
daddi r4, r4, 1
seguir: bne r1,r3, seguir2
daddi r4, r4, 1
seguir2: bne r2,r3, seguir3
daddi r4, r4, 1
seguir3: sd r4, D(r0)
halt
