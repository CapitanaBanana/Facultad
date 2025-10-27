#include <bits/stdc++.h>
using namespace std;
struct DSU
{
  vector<int> parent, size;
  int componentes;
  DSU(int n) : parent(n), size(n, 1), componentes(n)
  {
    for (int i = 0; i < n; i++)
      parent[i] = i;
  }
  int find(int x)
  {
    if (x == parent[x])
      return x;
    return parent[x] = find(parent[x]);
  }
  int unite(int x, int y)
  {
    x = find(x);
    y = find(y);
    if (x == y)
      return 1;
    if (size[x] < size[y])
      swap(x, y);
    parent[y] = x;
    size[x] += size[y];
    componentes--;
    return 0;
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
    u--;
    v--;
    dsu.unite(u, v);
  }
  cout << dsu.componentes - 1 << "\n";
  int root = dsu.find(0);
  for (int i = 1; i < n; i++)
  {
    int ri = dsu.find(i);
    if (ri != root)
    {
      dsu.unite(root, ri);
      cout << root + 1 << " " << i + 1 << "\n";
    }
  }
}