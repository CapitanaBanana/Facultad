// ¿Es posible dividir el array en ≤ k subarrays donde ninguno tenga suma mayor a X?
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
bool can(long long X, const vector<long long> &x, int k)
{
  int segments = 1;  // empezamos con 1 subarray
  long long sum = 0; // suma del subarray actual

  for (long long v : x)
  {
    if (sum + v > X)
    {
      // no puedo agregar v al subarray actual,
      // entonces abro un nuevo subarray
      segments++;
      sum = v;

      if (segments > k)
        return false; // demasiados subarrays
    }
    else
    {
      sum += v; // lo agrego al subarray actual
    }
  }

  return true; // se pudo con <= k
}
int main()
{
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n, k;
  cin >> n >> k;

  vector<ll> x(n);
  ll lo = 0, hi = 0;

  for (ll &v : x)
  {
    cin >> v;
    lo = max(lo, v); // no puede ser menor al mayor elemento
    hi += v;         // suma total
  }

  while (lo < hi)
  {
    ll mid = lo + (hi - lo) / 2;

    if (can(mid, x, k)) // le pasamos X, el arreglo y k
      hi = mid;
    else
      lo = mid + 1;
  }

  cout << lo << "\n";
  return 0;
}
