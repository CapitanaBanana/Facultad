#include <bits/stdc++.h>

using namespace std;

const int MOD = 1000000007;
typedef long long tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

bool isPrime(tipo n)
{
  // límites
  if (n <= 1)
    return false;
  if (n <= 3)
    return true;

  // cosa re loca para ver si es primo o no
  if (n % 2 == 0 || n % 3 == 0)
    return false;

  for (int i = 5; i * i <= n; i = i + 6)
    if (n % i == 0 || n % (i + 2) == 0)
      return false;

  return true;
}
int nextPrime(int N)
{

  // Base case
  if (N <= 1)
    return 2;

  tipo prime = N;
  bool found = false;

  // Loopea continuamente hasta que isprime retorna que si en algo mayor a n
  while (!found)
  {
    prime++;
    if (isPrime(prime))
      found = true;
  }
  return prime;
}
int main()
{
  FIN;
  int n, x;
  cin >> n;
  for (int i = 0; i < n; i++)
  {
    cin >> x;
    cout << nextPrime(x) << "\n";
  }
  return 0;
}
