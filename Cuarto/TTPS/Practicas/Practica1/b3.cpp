#include <bits/stdc++.h>
using namespace std;
int main()
{
  int n;
  cin >> n;
  long long maxX = -1;
  long long maxY = -1;
  long long minX = pow(10, 10);
  long long minY = pow(10, 10);

  long long temp;
  for (int i = 0; i < n; i++)
  {
    cin >> temp;
    if (temp > maxX)
    {
      maxX = temp;
    }
    if (temp < minX)
    {
      minX = temp;
    }
    cin >> temp;
    if (temp > maxY)
    {
      maxY = temp;
    }
    if (temp < minY)
    {
      minY = temp;
    }
  }
  minY--;
  minX--;
  maxX++;
  maxY++;
  cout << ((maxY - minY) * 2) + ((maxX - minX) * 2);
}