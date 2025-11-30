from sympy import mod_inverse
from sympy import factorint
n = 1452449184624535635757449085988204487494222248509493899299759
e = 65537
C = 1280743944712857143060627969938538851911171950125979945026152

# factors = factorint(n)
# p = list(factors.keys())[0]
# q = list(factors.keys())[1]

# tardaba mucho, lo hice con uno online :p
p = 1153324775179431312178120797679
q = 1259358348907893108175391571521
d = int(mod_inverse(e, (p-1)*(q-1)))
m = pow(C, d, n)
print(bytes.fromhex(hex(m)[2:]).decode())
