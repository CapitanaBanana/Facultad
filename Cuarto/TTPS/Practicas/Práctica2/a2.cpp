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
  for (int i = 1; i <= n; i++)
  {
    long long res = 0;
    for (int j = 1; j <= 6 && i - j >= 0; j++)
      res = (res + result[i - j]) % MOD;
    result[i] = res;
  }

  cout << result[n] << '\n';
}
