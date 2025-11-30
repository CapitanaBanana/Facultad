from sympy import mod_inverse
from pwn import *
from sympy import factorint
con = remote("ic.catedras.linti.unlp.edu.ar", 11018)

con.recvuntil(b"p= ")
p = int(con.recvline().strip())
con.recvuntil(b"g=")
g = int(con.recvline().strip())
con.recvuntil(b"public_alice= ")
public_alice = int(con.recvline().strip())
con.recvuntil(b"private_bob= ")
private_bob = int(con.recvline().strip())

public_bob = pow(g, private_bob, p)
shared_secret = pow(public_alice, private_bob, p)


con.sendline(str(shared_secret).encode())

print(con.recvall())
