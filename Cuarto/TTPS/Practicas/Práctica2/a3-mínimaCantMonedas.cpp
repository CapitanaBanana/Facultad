// dp[i]=la minima cantidad de monedas necesarias para sumar i
#include <bits/stdc++.h>
using namespace std;
int main()
{
  int x, n;
  cin >> x >> n;
  vector<int> coins(x);
  vector<int> result(n + 1, 1e9); // inicializa el vector con infinito
  for (int i = 0; i < x; i++)
  {
    cin >> coins[i];
  }
  result[0] = 0;      // caso base, la unica forma de conseguir 0 pesos es con 0 monedas
  for (int c : coins) // 3 (o moneda que nos sdan)
  {
    for (int i = c; i <= n; i++) // de 3 a N
    {
      result[i] = min(result[i], result[i - c] + 1);
      // Para hacer suma i, si usás una moneda de valor c:
      // antes debías haber llegado a i - c y ahora agregas una moneda más
    }
  }
  if (result[n] == 1e9)
  {
    cout << -1 << '\n';
  }
  else
  {
    cout << result[n] << '\n';
  }
}