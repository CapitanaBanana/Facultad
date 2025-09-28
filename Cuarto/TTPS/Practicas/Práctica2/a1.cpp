#include <bits/stdc++.h>
using namespace std;
const int MOD = 1000000007;
int fachero(int cifras, int digito)
{
  if (cifras == 1)
  {
    return 1;
  }
  else
  {
    if (digito == 1)
    {
      return fachero(cifras - 1, 3);
    }
    if (digito == 3)
    {
      return fachero(cifras - 1, 1) + fachero(cifras - 1, 5);
    }
    if (digito == 5)
    {
      return fachero(cifras - 1, 3) + fachero(cifras - 1, 7);
    }
    if (digito == 7)
    {
      return fachero(cifras - 1, 5) + fachero(cifras - 1, 9);
    }
    if (digito == 9)
    {
      return fachero(cifras - 1, 7);
    }
  }
  return 0;
}

int main()
{
  int k;
  cin >> k;
  long long res = 0;
  res += fachero(k, 1) + fachero(k, 3) + fachero(k, 5) + fachero(k, 7) + fachero(k, 9);
  cout << res % MOD << '\n';
}