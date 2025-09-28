#include <bits/stdc++.h>
using namespace std;
int main()
{
  int n;
  int cant = 0;
  cin >> n;
  vector<int> array(n);
  vector<int> result(n, 1);
  for (int i = 0; i < n; i++)
  {
    cin >> array[i];
  }
  for (int i = n - 1; i >= 0; i--)
  {
    for (int j = i + 1; j < n; j++)
    {
      if (array[j] > array[i])
      {
        result[i] = max(result[i], result[j] + 1);
      }
    }
  }
  cout << *max_element(result.begin(), result.end()) << '\n';
}
