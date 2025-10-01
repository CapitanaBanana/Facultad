from pwn import *
import base64
con = remote("ic.catedras.linti.unlp.edu.ar", 11004)

con.readuntil(
    "Bienvenidos! Tienen un segundo para realizar el ROT ")
ROT_line = con.readline().strip()
rot_str = ROT_line.split()[0]
ROT = int(rot_str)

frase = con.readline().decode().strip()


def rotN(s, n):
    out = []
    for c in s:
        if 'a' <= c <= 'z':
            out.append(chr((ord(c)-97+n) % 26 + 97))
        elif 'A' <= c <= 'Z':
            out.append(chr((ord(c)-65+n) % 26 + 65))
        else:
            out.append(c)
    return ''.join(out)


res = rotN(frase, ROT)

con.sendline(res.encode())
print(con.readall().decode())
