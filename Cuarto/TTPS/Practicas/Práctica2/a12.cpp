#include <bits/stdc++.h>
using namespace std;
const int MOD = 1000000007;
int main()
{
  int k;
  cin >> k;
  vector<vector<long long>> matrix(5, vector<long long>(k + 1));
  for (int i = 0; i < 5; i++)
  {
    matrix[i][0] = 1;
  }
  for (int i = 1; i < k; i++)
  {
    matrix[0][i] = matrix[1][i - 1] % MOD;
    matrix[1][i] = (matrix[0][i - 1] + matrix[2][i - 1]) % MOD;
    matrix[2][i] = (matrix[1][i - 1] + matrix[3][i - 1]) % MOD;
    matrix[3][i] = (matrix[2][i - 1] + matrix[4][i - 1]) % MOD;
    matrix[4][i] = matrix[3][i - 1];
  }
  long long suma = 0;
  for (int i = 0; i < 5; i++)
  {
    suma += matrix[i][k - 1];
  }
  suma = suma % MOD;

  cout << suma;
}