// cantidad de tiradas del nro i que suma n
#include <bits/stdc++.h>
const int MOD = 1000000007;
using namespace std;
int main()
{
  int n;
  cin >> n;
  vector<long long> result(n, 0);
  result[0] = 1;
  for (int i = 1; i < n; i++)
  {
    int res = 0;
    for (int j = i - 1; j >= 0; j--)
    {
      res += result[j] % MOD;
    }
    result[i] = res;
    if (i < 6)
    {
      result[i]++;
    }
  }
  cout << result[n - 1] << '\n';
}
