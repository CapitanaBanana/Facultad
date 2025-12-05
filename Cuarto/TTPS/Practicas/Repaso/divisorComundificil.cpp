#include <bits/stdc++.h>

using namespace std;

// const int MOD = 1000000007;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)
// lista de n enteros positivos, encontrar de 2 enteros el divisor comun es lo mar largo posible. El numero mas grande.
int main()
{
  FIN;
  int n;
  vector<int> criba(1e6 + 1, 0);
  cin >> n;
  vector<int> array(n);
  for (int i = 0; i < n; i++)
  {
    int x;
    cin >> x;
    array[i] = x;
    criba[x]++;
  }
  for (int d = 1e6; d >= 1; d--)
  {
    int count = 0;
    for (int i = d; i <= 1e6; i += d)
    {
      count += criba[i];
    }
    if (count >= 2)
    {
      cout << d;
      return 0;
    }
  }
}