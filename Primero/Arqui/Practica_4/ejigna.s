.data
A: .word 5
B: .word 5
C: .word 5
D: .word 0
.code
ld r1,A(r0)
ld r2,B(r0)
ld r4,D(r0)
ld r3,C(r0)
bne r1,r3,Segui
daddi r4,r4,1
Segui: bne r1,r2,Se
daddi r4,r4,1
Se: bne r2,r3,Terminamos
daddi r4,r4,1
Terminamos: sd r4,D(r0)
halt
