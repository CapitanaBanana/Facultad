#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

int main()
{
  FIN;
  int n, x;
  cin >> n;
  vector<int> divs(1000001, 0); // acá no le puedo poner 1e6. Tambi´nen para inicalizarlo fácil tiene que ser vector y no directamente int divs[1000001]

  for (int i = 1; i <= 1e6; i++)      // 1e6 es el máximo input que puedo recibir
    for (int j = i; j <= 1e6; j += i) // itero sumando i, me queda como una criba con todos los divisores de todos los números
      divs[j]++;
  for (int i = 0; i < n; i++)
  {
    cin >> x;
    cout << divs[x] << "\n"; // solo busco lo que necesito e imprimo
  }
  return 0;
}
