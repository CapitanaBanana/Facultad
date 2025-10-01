from pwn import *
import base64
con = remote("ic.catedras.linti.unlp.edu.ar", 11002)

con.readuntil(
    "Bienvenidos! Tienen un segundo para encodear en base64 esta palabra:\n")
palabra = con.readline().strip()
con.sendline(base64.b64encode(palabra))
print(con.readall().decode())
