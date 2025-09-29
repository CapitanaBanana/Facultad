#include <bits/stdc++.h>
using namespace std;
int main()
{
  int x, n;
  cin >> x >> n;
  vector<int> coins(x);
  vector<int> result(n, 0);
  for (int i = 0; i < x; i++)
  {
    cin >> coins[i];
  }
  for (int i = 0; i < n; i++)
  {
    for (int j = 0; j < i; j++)
    {
      for (int k = 0; k < x; k++)
      {
        if (result[j] != -1)
        {
          if (i == j + coins[k])
          {
            if (result[j] <= result[i] or result[i] == 0)
            {
              result[i] = result[j] + 1;
            }
          }
          else if (j != 0)
          {
            result[j] = -1;
          }
        }
      }
    }
  }
  cout << result[n] << '\n';
}
