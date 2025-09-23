#include <bits/stdc++.h>
using namespace std;
int main()
{
  string str;
  cin >> str;
  bool t = false;
  bool a = false;
  bool p = false;

  for (char c : str)
  {
    if (c == 'T')
    {
      t = true;
    }
    else if (c == 'A' and t)
    {
      a = true;
    }
    else if (c == 'P' and t and a)
    {
      p = true;
    }
  }
  if (p)
  {
    cout << 's';
  }
  else
  {
    cout << 'n';
  }
}