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
tipo distancia(Punto i, Punto j)
{
  Punto a = i - j;
  return abs((double)a.mod());
}
int main()
{
  FIN;
  int n;
  cin >> n;
  vector<Punto> puntos(n);
  for (int i = 0; i < n; i++)
  {
    cin >> puntos[i].x >> puntos[i].y;
  }
  vector<tipo> minimos(n);
  for (int i = 0; i < n; i++)
  {
    tipo min = 9999999;
    for (int j = 0; j < n; j++)
    {
      if (j != i)
      {
        cout << "la distancxia entre" << puntos[i].x << ' ' << puntos[i].y << " y " << puntos[j].x << ' ' << puntos[j].y;
        tipo dist = distancia(puntos[i], puntos[j]);
        cout << "es: " << dist << '\n';
        if (dist < min)
        {
          min = dist;
        }
      }
    }
    minimos[i] = min;
  }
  for (int i = 0; i < n; i++)
  {
    cout << ' ' << minimos[i];
  }
  tipo max = -1;
  for (int i = 0; i < n; i++)
  {
    if (minimos[i] > max)
    {
      max = minimos[i];
    }
  }
  cout << max;
  return 0;
}