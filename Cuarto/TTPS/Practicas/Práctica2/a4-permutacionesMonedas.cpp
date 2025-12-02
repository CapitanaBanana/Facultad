// dp[i]=cantidad de secuencias que suman i
#include <bits/stdc++.h>
using namespace std;
int main()
{
  const int MOD = 1000000007;
  int x, n;
  cin >> x >> n;
  vector<int> coins(x);
  vector<int> result(n + 1, 0);
  for (int i = 0; i < x; i++)
  {
    cin >> coins[i];
  }
  sort(coins.begin(), coins.end()); // Para poder cortar el bucle cuando c > i.
  result[0] = 1;
  for (int i = 1; i <= n; i++)
  {
    long long res = 0;
    for (int c : coins)
    {
      if (c > i)
        break;
      res += result[i - c];
      if (res >= MOD)
        res -= MOD;
    }
    result[i] = res;
    /*      Para sumar i, podés :
            terminar con la moneda c,
            entonces antes tenías que haber sumado i - c. */
  }
  cout << result[n];
}