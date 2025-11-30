from sympy import mod_inverse
from pwn import *
con = remote("ic.catedras.linti.unlp.edu.ar", 11012)

con.recvuntil(b"p= ")
p = int(con.recvline().strip())
con.recvuntil(b"q= ")
q = int(con.recvline().strip())
con.recvuntil(b"e= ")
e = int(con.recvline().strip())
con.recvuntil(b"c= ")
c = int(con.recvline().strip())

n = p*q
d = int(mod_inverse(e, (p-1)*(q-1)))
m = pow(c, d, n)
m_hex = hex(m)[2:]
if len(m_hex) % 2:
    m_hex = '0' + m_hex
mensaje = bytes.fromhex(m_hex)

con.sendline(mensaje)
print(con.recvall())
