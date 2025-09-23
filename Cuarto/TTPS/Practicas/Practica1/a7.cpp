#include <bits/stdc++.h>
using namespace std;
int main()
{
  string str;
  string res = "";
  cin >> str;
  for (char c : str)
  {
    c = (char)tolower(c);
    if (!(c == 'a' or c == 'e' or c == 'i' or c == 'o' or c == 'u' or c == 'y'))
    {
      res = res + '.' + c;
    }
  }
  cout << res;
}
