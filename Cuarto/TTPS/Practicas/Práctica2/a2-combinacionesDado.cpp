// dp[i]=cantidad de formas de obtener la suma i tirando un dado de 6 caras
#include <bits/stdc++.h>
const int MOD = 1000000007;
using namespace std;
int main()
{
  int n;
  cin >> n;
  vector<long long> result(n + 1, 0);
  result[0] = 1;               // para conseguir el 0 hay una sola tirada, no tirar xd
  for (int i = 1; i <= n; i++) // i sería el número al que estoy buscando las formas de construir
  {
    long long res = 0;
    // Para llegar a i, puedo haber llegado antes a i-1, i-2, i-3, i-4, i-5, o i-6, luego tiro un dado que dé el número que falta.
    for (int j = 1; j <= 6 && i - j >= 0; j++)
      res = (res + result[i - j]) % MOD; // la cantidad de formas que tengo de conseguir N son las que tenía de conseguir n-1+n-2...n-6. Es como que pienso hacia atras las tiradas considerando el último dado que tiré (j)
    result[i] = res;
  }

  cout << result[n] << '\n';
}