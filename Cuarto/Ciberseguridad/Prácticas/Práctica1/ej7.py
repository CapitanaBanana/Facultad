import hashlib
from pwn import *
from pathlib import Path
from itertools import islice
con = remote("ic.catedras.linti.unlp.edu.ar", 11007)

ROCKYOU_PATH = "rockyou.txt" 


con.recvuntil(b"rockyou.txt):")
con.recvline() 
target = con.recvline().strip().decode("ascii", "ignore")
with open(ROCKYOU_PATH, "rt", encoding="latin-1", errors="ignore") as f:
      for pwd in islice(f, 100):
          pwd = pwd.rstrip("\r\n")
          if not pwd:
              continue
          h = hashlib.sha256(pwd.encode("latin-1", "ignore")).hexdigest()
          if h == target:
              found = pwd
              break

con.sendline(found.encode("latin-1"))
print(con.readall().decode())
