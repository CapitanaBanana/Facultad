#include <bits/stdc++.h>

using namespace std;

const int MOD = 1e9 + 7;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

int main()
{
  FIN;
  int n, a, b;
  cin >> n;

  /*
  vector<int> fact(1001, 0);
  fact[1] = 1;
  for (int i = 2; i <= 1000; i++)
    fact[i] = fact[i - 1] * i %MOD;
  for (int i = 0; i < n; i++)
  {
    cin >> a >> b;
    cout << (fact[a]/(fact[b]*fact[a-b]) %MOD) << "\n";
  } */
  // esto no anda. la operación final está mal si le ponmgo el mod ahí y si se lo scao es muy grande

  // TABNLA DE PASCAL:: triángulo donde cada númer es la suma de los dos que tiene arriba. cada fila i tiene i+1 elem.
  /*  i=0      1
      i=1     1 1
      i=2    1 2 1
      i=3   1 3 3 1
      i=4  1 4 6 4 1
      i=5 1 5 10 10 5 1
    */
  int C[1001][1001];
  for (int i = 0; i <= 1000; i++)
  {
    C[i][0] = 1;
    C[i][i] = 1; // primero y último tienen 1
    for (int j = 1; j < i; j++)
    {
      C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD; // C[i][j] es la combinación i sobre j = (i/j)
    }
  }
  for (int i = 0; i < n; i++)
  {
    cin >> a >> b;
    cout << C[a][b] << "\n";
  }
  return 0;
}
