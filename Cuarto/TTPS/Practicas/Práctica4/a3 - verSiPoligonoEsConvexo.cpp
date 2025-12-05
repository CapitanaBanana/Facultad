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
  for (int i = 0; i < n; ++i)
  {
    Punto current = puntos[i];
    Punto v1, v2;
    // SIEMPRE HACER LAS RESTAS CONSISTENTES, de origen a lo otro o del otro a origen
    if (i == 0) // si estoy en el primero, hago que el anterior sea el último
      v1 = puntos[n - 1] - current;
    else
      v1 = puntos[i - 1] - current;
    if (i == n - 1)
      v2 = puntos[0] - current; // si estoy en el último, el anterior es el primero
    else
      v2 = puntos[i + 1] - current;
    tipo res = v1 ^ v2;

    if (res < 0)
    {
      if (horario != -1 && horario != 1) // horario=1 es horario
      {
        cout << "NO";
        return 0;
      }
      horario = 1;
    }
    else if (res > 0)
    {
    https:                               // chatgpt.com/
      if (horario != -1 && horario != 0) // horario en 0 es antihorario
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