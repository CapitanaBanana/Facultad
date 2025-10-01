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
  for (int i = 1; i <= n; i++)
  {
    long long res = 0;
    for (int c : coins)
    {
      if (c > i)
        break;
      res = (res + result[i - c]) % MOD;
    }
    result[i] = res;
  }
  cout << result[n];
}