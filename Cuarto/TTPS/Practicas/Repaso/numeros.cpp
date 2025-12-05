/* Bloques según cantidad de dígitos
Números de 1 dígito : del 1 al 9 → 9 * 1 = 9 dígitos
Números de 2 dígitos : del 10 al 99 → 90 * 2 = 180 dígitos
Números de 3 dígitos : del 100 al 999 → 900 * 3 = 2700 dígitos
Números de 4 dígitos : 9000 * 4 = 36000 dígitos
Para números de d dígitos :
Cantidad de números : D x 9× 10 ^(𝑑− 1) */

/* Restamos bloques hasta que el bloque de d dígitos sea demasiado grande.

Ejemplo : k = 250

Resta 9(1 dígito) → queda 241

Resta 180(2 dígitos) → queda 61

Ahora estamos en el bloque de números de 3 dígitos,
con k = 61 dentro del bloque.
Cada número aporta d dígitos, así que:

indexDentroDelBloque = k-1
numeroOffset = indexDentroDelBloque / d
digitoOffset = indexDentroDelBloque % d
El número buscado es:
numero = primer_numero_del_bloque + numeroOffset
primer_numero_del_bloque = 10^(d-1)

*/
#include <bits/stdc++.h>
using namespace std;

int main()
{
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int q;
  cin >> q;
  while (q--)
  {
    long long k;
    cin >> k;

    long long d = 1;
    long long count_in_block = 9;

    // Encontrar d: en qué bloque de dígitos cae k
    while (k > d * count_in_block)
    {
      k -= d * count_in_block;
      d++;
      count_in_block *= 10;
    }

    // Ahora k está dentro del bloque de números de d dígitos
    long long index = k - 1;
    long long number_offset = index / d;
    long long digit_offset = index % d;

    long long first_number = pow(10LL, d - 1);
    long long number = first_number + number_offset;

    string s = to_string(number);
    cout << s[digit_offset] << "\n";
  }
}
