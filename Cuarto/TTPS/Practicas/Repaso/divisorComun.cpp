#include <bits/stdc++.h>
#include <algorithm>
// CREO QUE ESTO SOLO RESUELVA LA MANERA FACIL.
using namespace std;

// const int MOD = 1000000007;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)
// lista de n enteros positivos, encontrar de 2 enteros el divisor comun es lo mar grande posible. El numero mas grande. MCD
int main()
{
  FIN;
  int n;
  int max = -1;
  cin >> n;
  vector<int> array(n);
  for (int i = 0; i < n; i++)
  {
    int x;
    cin >> x;
    array[i] = x;
  }
  for (int i = 0; i < n; i++)
  {
    for (int j = i + 1; j < n; j++)
    {

      int act = __gcd(array[i], array[j]); // conseguimos el comun multiplo divisor mas grande entre los numeros
      if (act > max)
      {
        max = act;
      }
    }
  }
  cout << max;
}