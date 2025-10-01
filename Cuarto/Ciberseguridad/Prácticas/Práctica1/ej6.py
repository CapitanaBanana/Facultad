from pwn import *
import hashlib
con = remote("ic.catedras.linti.unlp.edu.ar", 11006)
con.recvuntil(b"palabra:")
con.recvline() 
word = con.readline().strip()
word = hashlib.md5(word)

con.sendline(word.hexdigest())
print(con.readall().decode())
