 .data
A: .word 1
B: .word 3
 .code
 ld r1, A(r0)
 daddi r3, r0, 16
 ld r2, B(r0)
loop: sd r1, 0(r3)
 daddi r2, r2, -1
 dsll r1, r1, 1
 daddi r3, r3, 8
 bnez r2, loop
halt
