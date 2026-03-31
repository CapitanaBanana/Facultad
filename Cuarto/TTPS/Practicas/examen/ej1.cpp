#include <bits/stdc++.h>
typedef long long tipo;
using namespace std;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

int main()
{
  FIN;
  tipo n, x;
  cin >> n;
  tipo res;
  vector<int> vec(n);
  for (int i = 0; i < n; i++)
  {
    cin >> x;
    vec[i] = x;
  }

  cout << res << "\n";
  return 0;
}
