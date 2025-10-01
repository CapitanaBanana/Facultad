import binascii

HEX = """08296632232822342f27356637332366252f2034273466252928661e09146a66252e236
866162334296624332328296a662a2766202a2721662223662335322366342332296623
35660f053d092c7619257628193e7634676767737e7f737f73737f192527352f192e272
52d232334343b""".replace("\n","").replace(" ","")

ct = bytes.fromhex(HEX)

def score_english(bs: bytes) -> float:
    # puntuación simple por caracteres imprimibles y letras frecuentes
    common = set(b" etaoinshrdlucmfwypvbgkqjxzETAOINSHRDLUCMFWYPVBGKQJXZ")
    s = 0.0
    for c in bs:
        if 32 <= c <= 126:
            s += 1.0
        if c in common:
            s += 0.5
    return s

best_k, best_pt, best_score = 0, b"", float("-inf")
for k in range(256):
    pt = bytes([b ^ k for b in ct])
    sc = score_english(pt)
    if sc > best_score:
        best_k, best_pt, best_score = k, pt, sc

print(f"Mejor clave: {best_k} (0x{best_k:02x}, '{chr(best_k)}')")
print(best_pt.decode("utf-8", errors="replace"))
