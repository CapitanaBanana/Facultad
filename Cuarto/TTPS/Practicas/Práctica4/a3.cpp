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
  int horario = -1;
  vector<Punto> puntos(n);

  for (int i = 0; i < n; ++i)
  {
    cin >> puntos[i].x >> puntos[i].y;
  }
  for (int i = 1; i < n - 1; ++i)
  {
    Punto v1 = puntos[i] - puntos[i - 1];
    Punto v2 = puntos[i + 1] - puntos[i];
    tipo res = v1 ^ v2;

    if (res < 0)
    {
      if (horario != -1 && horario != 1)
      {
        cout << "NO";
        return 0;
      }
      horario = 1;
    }
    else if (res > 0)
    {
      if (horario != -1 && horario != 0)
      {
        cout << "NO";
        return 0;
      }
      horario = 0;
    }
  }
  cout << "YES";

  return 0;
}