#include <bits/stdc++.h>

using namespace std;

typedef long long tipo;

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
  int n;
  double eps = 1e-7;
  cin >> n;

  vector<Punto> puntos(3);
  for (int i = 0; i < n; ++i)
  {
    for (int j = 0; j < 3; j++)
    {
      cin >> puntos[j].x >> puntos[j].y;
    }

    Punto v1 = puntos[1] - puntos[0];
    Punto v2 = puntos[2] - puntos[0];
    double res = v1 ^ v2;

    if (res > eps)
    {
      cout << "LEFT" << "\n";
    }
    else if (res < -eps)
    {
      cout << "RIGHT" << "\n";
    }
    else
      cout << "TOUCH" << "\n";
  }
  return 0;
}