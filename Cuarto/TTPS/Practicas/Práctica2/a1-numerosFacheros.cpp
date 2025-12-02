#include <bits/stdc++.h>
using namespace std;
const int MOD = 1000000007;
int main()
{
  int k;
  cin >> k;
  vector<vector<long long>> matrix(5, vector<long long>(k + 1)); // Los dígitos que puede tener son 5: 1,3,5,7,9.
  for (int i = 0; i < 5; i++)
  {
    matrix[i][0] = 1; // Con un solo número, hay 1 nro que termina con cada digito
  }
  for (int i = 1; i < k; i++) // construyo la tabla de izquierda a derecha (de menos dígitos a más)
  {                           // voy llenando la tabla sumando los anteriores. Ejemplo: con 3 dígitos terminando en 1, tengo que hacer la suma de los de dos dígitos terrminados en 3. _ _ 3. Terminados en 5, son los de dos dígitos terminados en 3 y en 7 _ _ 3 + _ _ 7.
    matrix[0][i] = matrix[1][i - 1] % MOD;
    matrix[1][i] = (matrix[0][i - 1] + matrix[2][i - 1]) % MOD;
    matrix[2][i] = (matrix[1][i - 1] + matrix[3][i - 1]) % MOD;
    matrix[3][i] = (matrix[2][i - 1] + matrix[4][i - 1]) % MOD;
    matrix[4][i] = matrix[3][i - 1];
  }
  long long suma = 0;
  for (int i = 0; i < 5; i++)
  {
    suma += matrix[i][k - 1];
  } // después solo sumo toma la columna del número de dígitos que me interesa.
  suma = suma % MOD;

  cout << suma;
}