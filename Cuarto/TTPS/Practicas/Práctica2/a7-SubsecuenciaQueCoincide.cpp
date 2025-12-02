#include <bits/stdc++.h>

using namespace std;

typedef double tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

int main()
{
  FIN;
  int n, m;
  cin >> n >> m;
  vector<int> vec(n + 1);
  vector<int> vec2(m + 1);
  vector<vector<int>> result(n + 1, vector<int>(m + 1, 0)); // matriz de nxm inicialziada en 0

  for (int i = 1; i < n + 1; ++i)
  {
    cin >> vec[i];
  }
  for (int i = 1; i < m + 1; ++i)
  {
    cin >> vec2[i];
  }

  for (int i = 1; i < n + 1; i++)
  {
    for (int j = 1; j < m + 1; j++)
    {
      if (vec[i] == vec2[j]) // entonces ese elemento pertenece a la LCS
      {
        result[i][j] = result[i - 1][j - 1] + 1;
      }
      else // avanzamos por el que produzca mayor LCS
      {
        result[i][j] = max(result[i - 1][j], result[i][j - 1]);
      }
    }
  }
  vector<int> ans;
  int i = n, j = m;
  // backtracking, ir para atras para conseguir el resultado
  while (i > 0 && j > 0)
  {
    if (vec[i] == vec2[j])
    {
      ans.push_back(vec[i]);
      i--, j--;
    }
    else if (result[i - 1][j] >= result[i][j - 1]) // lero lero
    {
      i--;
    }
    else
    {
      j--;
    }
  }
  reverse(ans.begin(), ans.end());
  cout << result[n][m] << "\n";
  for (int x : ans)
    cout << x << " ";

  return 0;
}