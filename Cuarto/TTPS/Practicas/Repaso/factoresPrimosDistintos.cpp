#include <bits/stdc++.h>

using namespace std;

// const int MOD = 1000000007;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)
// dado x encontrar factores primos distintos.
//  para x=60 -> 2²,3,5 -> 3 factores primos distintos
int main()
{
  FIN;
  int q;
  cin >> q; // cantidad de tests
  for (int i = 0; i < q; i++)
  {
    tipo x;
    cin >> x;
    vector<tipo> factores;
    for (tipo d = 2; d * d <= x; d++) // Llegar hasta d∗d≤x es equivalente a ir hasta raiz de x
    {                                 // vamos por d desde 2 hasta la raiz de x
      if (x % d == 0)
      {                        // si d divide a x
        factores.push_back(d); // agregamos d una sola vez
        while (x % d == 0)
          x /= d; // dividimos completamente
      }
    }
    if (x > 1)
      factores.push_back(x);
    cout << factores.size() << "\n";
  }
}
/* Probar con todos los divisores d desde 2 hasta raiz de𝑥

Si d divide a 𝑥 entonces es un factor primo.

Lo guardamos una sola vez (aunque tenga potencia).

Dividimos a x completamente por 𝑑

Si al final queda x>1, ese último número también es primo. */