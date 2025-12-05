#include <bits/stdc++.h>

using namespace std;
#define FIN                \
  ios::sync_with_stdio(0); \
  cin.tie(0);              \
  cout.tie(0)

long long invs = 0; // contador de inversiones

void mergeSort(vector<int> &a)
{
  int n = a.size();
  if (n <= 1)
    return; // CASO BASE

  // DIVIDIR
  int mid = n / 2;
  vector<int> left(a.begin(), a.begin() + mid);
  vector<int> right(a.begin() + mid, a.end());

  // ORDENAR CADA MITAD
  mergeSort(left);
  mergeSort(right);

  // MEZCLAR
  int i = 0, j = 0, k = 0;

  while (i < left.size() && j < right.size())
  {
    if (left[i] <= right[j])
    {
      a[k] = left[i];
      k++;
      i++;
    }
    else
    {
      a[k] = right[j];
      k++;
      j++;
      invs += left.size() - i; // sumo inversiones
    }
  }

  // Copiar restos de left
  while (i < left.size())
    a[k++] = left[i++];

  // Copiar restos de right
  while (j < right.size())
    a[k++] = right[j++];
}

int main()
{
  FIN;
  int n, x;
  cin >> n;
  vector<int> v(n);
  for (int i = 0; i < n; i++)
  {
    cin >> x;
    v[i] = n;
  }
  mergeSort(v);

  cout << invs << "\n";
  return 0;
}
