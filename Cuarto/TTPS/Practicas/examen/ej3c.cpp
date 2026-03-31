#include <bits/stdc++.h>

using namespace std;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)
struct Primos
{
  vector<int> lista;    // lista de todos los primos
  vector<int> minP;     // menor primo que divide a cada número
  vector<bool> esPrimo; // marca de primalidad

  Primos(int n) { criba(n); }

  void criba(int n)
  {
    esPrimo.assign(n + 1, true);
    minP.assign(n + 1, 0);
    esPrimo[0] = esPrimo[1] = false;

    for (int i = 2; i <= n; i++)
    {

      // Si sigue marcado como primo → lo agrego
      if (esPrimo[i])
      {
        lista.push_back(i);
        minP[i] = i; // el menor primo que divide a i es i mismo
      }

      // Marco múltiplos de i
      for (long long j = 1LL * i * i; j <= n; j += i)
      {
        if (esPrimo[j])
        {
          esPrimo[j] = false; // no es primo
          minP[j] = i;        // menor primo divisor
        }
      }
    }
  }
};
int main()
{
  FIN;
  int cant;
  tipo min, max;
  cin >> cant;
  Primos p = Primos(1000000);
  for (int i = 0; i < cant; i++)
  {
    cin >> min >> max;
    int j = 0;
    int res = 0;
    bool fin = false;
    while (!fin)
    {
      if (p.lista[j] >= min)
      {
        if (p.lista[j] <= max)
        {
          res++;
        }
        else
        {
          fin = true;
        }
      }
      j++;
    }

    cout << res << "\n";
  }
  return 0;
}