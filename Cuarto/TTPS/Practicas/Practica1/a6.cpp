#include <bits/stdc++.h>
using namespace std;
int main()
{
  int n;
  int k;
  int cant = 0;
  cin >> n >> k;
  vector<int> scores(n);
  for (int i = 0; i < n; i++)
  {
    cin >> scores[i];
  }
  int kscore = scores[k - 1];
  for (int i = 0; i < n; i++)
  {
    if (scores[i] >= kscore and scores[i] > 0)
    {
      cant++;
    }
  }
  cout << cant << '\n';
}
