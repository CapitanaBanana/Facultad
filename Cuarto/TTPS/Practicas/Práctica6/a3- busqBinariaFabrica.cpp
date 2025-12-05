#include <bits/stdc++.h>
using namespace std;
using ll = long long;

int main()
{
  ios::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  ll t;
  cin >> n >> t;

  vector<ll> k(n);
  for (ll &x : k)
    cin >> x;

  ll lo = 0;
  ll hi = *max_element(k.begin(), k.end()) * t; // límite superior

  while (lo < hi)
  {
    ll mid = lo + (hi - lo) / 2;

    // calcular producción en mid tiempo
    __int128 total = 0; // evitar overflow
    for (ll x : k)
    {
      total += mid / x;
      if (total >= t)
        break; // poda
    }

    if (total >= t)
      hi = mid; // posible respuesta
    else
      lo = mid + 1; // necesitamos más tiempo
  }

  cout << lo << "\n";
  return 0;
}
/* Si yo te digo un tiempo X…

¿podés saber cuántos productos pueden hacer todas las máquinas en ese tiempo ? Sí
Si una máquina tarda k[i],
entonces en X segundos hace : x / k[i] y sumas eso para todas las maquinas vas probando : de menos tiempo a mas

lo = 0

hi = t * max(k)(el tiempo más lento posible)

Mientras lo <
hi :

mid = (lo + hi) / 2

calculo cuántos productos hago en mid

si alcanzo t → puedo intentar menos
tiempo(hi = mid)

si no alcanzo → necesito más tiempo(lo = mid + 1)

Al final lo es la respuesta.
Buscamos el tiempo X más pequeño tal que la suma de (X / k[i]) sea ≥ t. */