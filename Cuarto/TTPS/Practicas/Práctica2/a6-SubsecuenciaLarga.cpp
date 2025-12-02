// dp[i]=longitud del LIS que empieza en la posicioˊn i
#include <bits/stdc++.h>
using namespace std;
int main()
{
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
      if (array[j] > array[i]) // te fijas si el numero de atras es mayor al que estas
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