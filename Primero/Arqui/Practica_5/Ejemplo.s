
.data
a: .word 45
b: .word 12
c: .double 0

.code
ld r1, a(r0)
mtc1 r1, f1
cvt.d.l f1,f1

ld r2, b(r0)
mtc1 r2,f2
cvt.d.l f2, f2

div.d f3, f1, f2
s.d f3, c(r0)
halt
