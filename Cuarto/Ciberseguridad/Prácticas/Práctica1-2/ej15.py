from sympy import mod_inverse
from pwn import *
from sympy import factorint
con = remote("ic.catedras.linti.unlp.edu.ar", 11017)

con.recvuntil(b"n= ")
n = int(con.recvline().strip())
con.recvuntil(b"e= ")
e = int(con.recvline().strip())
con.recvuntil(b"c= ")
c = int(con.recvline().strip())

factors = factorint(n)
p = list(factors.keys())[0]
q = list(factors.keys())[1]
d = int(mod_inverse(e, (p-1)*(q-1)))
m = pow(c, d, n)
print(bytes.fromhex(hex(m)[2:]).decode())

m_hex = hex(m)[2:]
if len(m_hex) % 2:
    m_hex = '0' + m_hex
mensaje = bytes.fromhex(m_hex)

con.sendline(mensaje)
print(con.recvall())
