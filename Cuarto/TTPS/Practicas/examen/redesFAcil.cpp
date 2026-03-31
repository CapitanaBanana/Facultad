// Buscar min distancia entre puntos
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

  double mod() const // distancia entre puntos. tengo que haccer la resta entre los p que me interesen y después apalicarle la operación
  {
    return sqrtl((double)x * x + (double)y * y);
  }
};

int main()
{
  FIN;
  tipo n;
  cin >> n;
  tipo min = 1e9;
  vector<tipo> puntos(n);
  for (int i = 0; i < n; i++)
  {
    int y;
    cin >> puntos[i] >> y;
  }
  sort(puntos.begin(), puntos.end());

  tipo max = -1;
  for (int i = 0; i < n - 1; i++)
  {
    tipo dist = abs(puntos[i] - puntos[i + 1]);
    if (dist > max)
    {
      max = dist;
    }
  }

  cout << max;

  return 0;
}