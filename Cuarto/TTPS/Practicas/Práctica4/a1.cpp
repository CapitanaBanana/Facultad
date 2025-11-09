#include <bits/stdc++.h>

using namespace std;

typedef double tipo;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

struct Punto
{
  tipo x, y;
  Punto(tipo x = 0, tipo y = 0) : x(x), y(y) {}

  Punto operator+(const Punto &other) const
  {
    return Punto(x + other.x, y + other.y);
  }

  Punto operator-(const Punto &other) const
  {
    return Punto(x - other.x, y - other.y);
  }

  tipo operator*(const Punto &other) const
  {
    return x * other.x + y * other.y;
  }

  Punto operator*(tipo escalar) const
  {
    return Punto(x * escalar, y * escalar);
  }

  tipo operator^(const Punto &other) const
  {
    return x * other.y - y * other.x;
  }

  double mod() const
  {
    return sqrtl((double)x * x + (double)y * y);
  }
};

int main()
{
  FIN;
  int n;
  cin >> n;
  tipo min = 1e9;
  vector<Punto> puntos(n);
  for (int i = 0; i < n; ++i)
  {
    cin >> puntos[i].x >> puntos[i].y;

    for (int j = 0; j < i; j++)
    {
      Punto a = puntos[i] - puntos[j];
      double dist = (double)a.mod();
      if (dist < min)
      {
        min = dist;
      }
    }
  }
  cout << fixed << setprecision(11) << min;

  return 0;
}