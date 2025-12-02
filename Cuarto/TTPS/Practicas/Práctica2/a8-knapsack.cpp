// d𝒑[𝒊][𝒙]: Máximo valor posible utilizando los primeros 𝑖 items y utilizando exactamente 𝑥 kilos en la mochila.
#include <bits/stdc++.h>
using namespace std;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)
int main()
{
  FIN;
  int n, x;
  cin >> n >> x;          // n cant obj, x peso max.
  vector<int> p(n), v(n); // vectores para peso y valor.
  for (int i = 0; i < n; i++)
  {
    cin >> p[i];
  }
  for (int i = 0; i < n; i++)
  {
    cin >> v[i];
  }
  vector<int> dp(x + 1, -1);
  dp[0] = 0;
  for (int i = 0; i < n; i++)
  { // Vamos a actualizar la mochila considerando el objeto i.
    vector<int> nuevo(x + 1, -1);
    for (int j = 0; j <= x; j++)
    {                                                  // recorremos todos los pesos
      nuevo[j] = dp[j];                                // ponemos el valor anterior
      if (j - p[i] >= 0 and dp[j - p[i]] != -1)        // probamos si podriamos meter el valor y el peso que estoy tratando de armar ahora - el peso del objeto que estoy viendo no sea -1
                                                       // Si un peso j no es alcanzable, entonces dp[j] = -1
        nuevo[j] = max(nuevo[j], dp[j - p[i]] + v[i]); // vemos si nos quedamos con el valor o con el nuevo
        }
    dp = nuevo;
  }
  cout << *max_element(dp.begin(), dp.end()); // devolvemos el maximo valor del array dp

  return 0;
}