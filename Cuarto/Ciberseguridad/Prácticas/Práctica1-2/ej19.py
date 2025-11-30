#!/usr/bin/env python3
"""
Crackear archivo PGP simétrico usando una wordlist (con gpg).
Uso:
  python3 crack_pgp_python.py -w diccionario -e flag.txt.gpg

Opciones útiles:
  --parallel N   -> intenta con N workers (cuidado: muchas instancias gpg pueden chocar con el agente)
  --timeout S    -> timeout por intento en segundos (default 12)
  --outdir DIR   -> carpeta donde depositar archivos decifrados (default out)
"""

import argparse
import subprocess
import tempfile
import os
import re
import sys
from concurrent.futures import ThreadPoolExecutor, as_completed
from pathlib import Path

# ajustá rango si la flag es más corta/larga
SNAKE_RE = re.compile(rb"[a-z0-9_]{6,60}")


def try_pass(passphrase: str, enc_path: Path, outdir: Path, timeout: int):
    """Intenta descifrar con una passphrase. Devuelve (ok, passphrase, outpath, matches, stderr_last_lines)."""
    outdir.mkdir(parents=True, exist_ok=True)
    out_path = outdir / f"out_{abs(hash(passphrase)) & 0xffffffff}.dec"
    # Ensuring to pass passphrase exactly (no trailing newlines)
    cmd = [
        "gpg", "--batch", "--yes",
        "--passphrase", passphrase,
        "--pinentry-mode", "loopback",
        "-o", str(out_path),
        "-d", str(enc_path)
    ]
    try:
        proc = subprocess.run(cmd, stdout=subprocess.PIPE,
                              stderr=subprocess.PIPE, timeout=timeout)
    except subprocess.TimeoutExpired:
        return (False, passphrase, None, None, ["TIMEOUT"])
    stderr_lines = proc.stderr.decode(
        errors="ignore").strip().splitlines()[-6:]
    if proc.returncode == 0:
        # leer contenido y buscar patrones snake_case
        try:
            data = out_path.read_bytes()
        except Exception as e:
            return (True, passphrase, str(out_path), [], ["NO_LEIDO:"+str(e)])
        matches = [m.decode(errors="ignore")
                   for m in set(SNAKE_RE.findall(data))]
        return (True, passphrase, str(out_path), matches, stderr_lines)
    else:
        # limpieza del archivo si quedó parcial
        try:
            out_path.unlink(missing_ok=True)
        except Exception:
            pass
        return (False, passphrase, None, None, stderr_lines)


def run_serial(wordlist, enc_path, outdir, timeout):
    with open(wordlist, "r", encoding="utf-8", errors="ignore") as f:
        for i, line in enumerate(f, 1):
            pw = line.rstrip("\r\n")
            if pw == "\\n":
                pw = ""
            print(f"[{i}] probando: '{pw}'", flush=True)
            ok, pw, outpath, matches, errlines = try_pass(
                pw, enc_path, outdir, timeout)
            if ok:
                print(">>> SUCCESS! passphrase:", pw)
                print("Archivo escrito en:", outpath)
                if matches:
                    print("Matches tipo snake_case encontrados:", matches)
                else:
                    print(
                        "No se detectaron matches tipo snake en el contenido. Inspeccioná", outpath)
                return True
            else:
                if errlines:
                    # mostrar último mensaje de error para diagnóstico
                    print("  fallo:", errlines[-1])
    print("[-] No encontrada en la wordlist (serial).")
    return False


def run_parallel(wordlist, enc_path, outdir, timeout, workers):
    # lee todas las passphrases en memoria (si la lista es enorme, no uses paralelo)
    with open(wordlist, "r", encoding="utf-8", errors="ignore") as f:
        passes = [line.rstrip("\r\n") for line in f]
    print(
        f"[+] Lanzando {len(passes)} intentos con {workers} workers (esto puede ser pesado).")
    with ThreadPoolExecutor(max_workers=workers) as exe:
        futs = {exe.submit(try_pass, pw, enc_path, outdir,
                           timeout): pw for pw in passes}
        for fut in as_completed(futs):
            ok, pw, outpath, matches, errlines = fut.result()
            if ok:
                print(">>> SUCCESS! passphrase:", pw)
                print("Archivo escrito en:", outpath)
                if matches:
                    print("Matches tipo snake_case encontrados:", matches)
                else:
                    print(
                        "No se detectaron matches tipo snake en el contenido. Inspeccioná", outpath)
                # intentamos cancelar otros futures (no garantiza parada inmediata)
                for f in futs:
                    f.cancel()
                return True
            else:
                # opcional: print de fallo (muy ruidoso si hay muchas contraseñas)
                # print(f"fail {pw}: {errlines[-1] if errlines else ''}")
                pass
    print("[-] No encontrada en la wordlist (parallel).")
    return False


def main():
    p = argparse.ArgumentParser()
    p.add_argument("-w", "--wordlist", required=True,
                   help="wordlist (diccionario)")
    p.add_argument("-e", "--enc", required=True,
                   help="archivo cifrado (flag.txt.gpg)")
    p.add_argument("--timeout", type=int, default=12,
                   help="timeout por intento (s)")
    p.add_argument("--outdir", default="out", help="directorio de salida")
    p.add_argument("--parallel", type=int, default=0,
                   help="numero de workers (0 = serial)")
    args = p.parse_args()

    wordlist = Path(args.wordlist)
    enc = Path(args.enc)
    outdir = Path(args.outdir)

    if not wordlist.exists():
        print("Wordlist no existe:", wordlist)
        sys.exit(1)
    if not enc.exists():
        print("Archivo cifrado no existe:", enc)
        sys.exit(1)

    print("[*] Asegurate de haber hecho: echo 'allow-loopback-pinentry' >> ~/.gnupg/gpg-agent.conf && gpgconf --kill gpg-agent")
    if args.parallel and args.parallel > 1:
        ok = run_parallel(wordlist, enc, outdir, args.timeout, args.parallel)
    else:
        ok = run_serial(wordlist, enc, outdir, args.timeout)

    if ok:
        print("[+] Hecho.")
    else:
        print("[-] Terminado sin éxito. Considerá usar gpg2john + john (mucho más rápido para S2K costoso).")


if __name__ == "__main__":
    main()
