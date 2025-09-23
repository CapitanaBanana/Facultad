#include <bits/stdc++.h>
using namespace std;
int main()
{
  int n;
  int cant = 0;
  vector<int> num(3);
  cin >> n;
  for (int i = 0; i < 3; i++)
  {
    cin >> num[i];
  }
  sort(num.begin(), num.end());

  int k = max(0, (n - num[2]) / num[0]);
  n -= k * num[0];
  cant += k;

  while (n != 0)
  {
    if ((n - num[0]) % num[0] == 0 or (n - num[0]) % num[1] == 0 or (n - num[0]) % num[2] == 0 or (n - num[0]) == 0)
    {
      n = n - num[0];
    }
    else if ((n - num[1]) % num[0] == 0 or (n - num[1]) % num[1] == 0 or (n - num[1]) % num[2] == 0 or (n - num[1]) == 0)
    {
      n = n - num[1];
    }
    else if ((n - num[2]) % num[0] == 0 or (n - num[2]) % num[1] == 0 or (n - num[2]) % num[2] == 0 or (n - num[2]) == 0)
    {
      n = n - num[2];
    }
    cant++;
  }

  cout << cant;
}
