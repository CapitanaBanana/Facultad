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
  sort(coins.begin(), coins.end());
  result[0] = 1;

  for (int c : coins)
  {
    for (int i = c; i <= n; i++)
    {
      result[i] += result[i - c];
      if (result[i] >= MOD)
        result[i] -= MOD;
    }
    /*Porque el orden de los loops controla si estás permitiendo permutar o no.primero contás formas usando solo moneda 2
    luego formas usando monedas{2, 3}
    luego formas usando{2, 3, 5} */
  }
  cout << result[n];
}