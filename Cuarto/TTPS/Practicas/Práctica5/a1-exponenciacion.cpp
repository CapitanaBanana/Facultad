#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

/* muy muy lento
int main()
{
  FIN;
  int n, a, b;
  cin >> n;

  for (int i = 0; i < n; i++)
  {
    cin >> a >> b;
    tipo res = 1;
    for (int j = 1; j <= b; j++)
    {
      res = (res * a) % MOD;
    }
    cout << res << "\n";
  }
  return 0;
} */

int main()
{
  FIN;
  int n;
  tipo a, b;
  cin >> n;

  for (int i = 0; i < n; i++)
  {
    cin >> a >> b;
    tipo res = 1;
    while (b > 0)
    {
      if ((b & 1) == 1) // si el último bit es 1
      {
        res = res * a % MOD; // tengo que hacer la multiplicacion
      }
      a = (a * a) % MOD; // esto es como cuadno me voy moviendo en los bits de un num, hacia la izqierda valen el doble
      b >>= 1;           // esto va corriendo los bits. EJ 13 en binario es 1101, cuando hago esto queda 110 (6)
    }
    cout << res << "\n";
  }
  return 0;
}
