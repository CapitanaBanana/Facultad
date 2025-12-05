// ir conectando componentes conexas, ver cuántas hay y cual es la más grande
#include <bits/stdc++.h>
using namespace std;

struct DSU
{
  vector<int> parent, size;
  // parent:quien es el representante del conjunto al que pertenece el nodo i
  // size:tamaño de cada componente conexa
  int componentes, maxSize;
  // componentes: cuántos distintos hay rn
  DSU(int n) : parent(n), size(n, 1), componentes(n), maxSize(1) // constructor. cada nodo es su propio padre, todos tienen tamaño 1, n componentes, el max tam es 1
  {
    for (int i = 0; i < n; i++)
      parent[i] = i;
  }
  int find(int x) // busca al representante de x recursivamente
  {
    if (x == parent[x])
      return x;
    return parent[x] = find(parent[x]); // esta línea hace path compression (aplana el árbol)
  }
  void unite(int x, int y) //
  {
    x = find(x);
    y = find(y);
    if (x == y)
      return;              // obtenes los representantes, si son iguales están en la misma comp. conexa y no haces nada
    if (size[x] < size[y]) // hacemos que x sea la más grande para unirle y así hacemos menos operaciones
      swap(x, y);
    parent[y] = x;
    size[x] += size[y];
    componentes--;
    maxSize = max(maxSize, size[x]);
  }
};

int main()
{
  int n, m;
  cin >> n >> m;
  DSU dsu(n);
  for (int i = 0; i < m; i++)
  {
    int u, v;
    cin >> u >> v;
    u--; // para que queden 0 indexados
    v--;
    dsu.unite(u, v);
    cout << dsu.componentes << " " << dsu.maxSize << "\n";
  }
}