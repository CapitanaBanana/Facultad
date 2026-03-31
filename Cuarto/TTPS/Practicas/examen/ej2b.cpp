
#include <bits/stdc++.h>
using namespace std;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)
int main()
{
  FIN;
  int n;
  int cant = 0;
  cin >> n;
  vector<int> array(n);
  vector<int> result(n, 1); //(se inicializa en 1 porque todo elemento es LIS de largo 1 por sí mismo)
  for (int i = 0; i < n; i++)
  {
    cin >> array[i];
  }
  for (int i = n - 1; i >= 0; i--) //
  {
    for (int j = i + 1; j < n; j++) //
    {
      if (array[j] % array[i] == 0)
      {
        result[i] = max(result[i], result[j] + 1); //
      }
    }
  }
  /* Para cada posición i, buscamos todos los j a la derecha (j > i)
  Si array[j] > array[i]
  → podemos extender una subsecuencia creciente
  Entonces result[i] = max( result[i], result[j] + 1 ) */
  cout << *max_element(result.begin(), result.end()) << '\n';
}
