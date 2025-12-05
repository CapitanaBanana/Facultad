// BFS busca en un grafo no dirigido con n nodos y m aristas el camino más corto desde el nodo 1.
#include <bits/stdc++.h>
using namespace std;
int main()
{
  int n, m;
  cin >> n >> m;
  vector<vector<int>> graph(n + 1);
  queue<int> q;

  for (int i = 0; i < m; i++)
  {
    int u, v;
    cin >> u >> v;
    graph[u].push_back(v); // como el grafo es no dirigido, se agregan en ambos sentidos.
    graph[v].push_back(u);
  }
  vector<bool> visited(n + 1, false);
  vector<int> parent(n + 1, -1);
  // visited[i]: marca si ya visitaste el nodo i.
  // parent[i] : guarda de qué nodo llegaste a i(para reconstruir el camino).
  // Inicializa BFS desde el nodo 1:
  q.push(1);
  visited[1] = true;
  while (!q.empty())
  {
    int nodo = q.front();
    q.pop();                     // saca el primer nodo de la cola
    for (auto &&i : graph[nodo]) // recorre todos los vecinos del nodo
    {
      if (visited[i] == false) // si un nodo no fue visitado, lo marca como visitado, guarda el padre y encola el nodo
      {
        visited[i] = true;
        parent[i] = nodo; // me guardo el padre
        q.push(i);
      }
    }
  }
  if (!visited[n])
  {
    cout << "IMPOSSIBLE\n";
    return 0;
  }

  // reconstrucción del camino
  // desde n hasta 1 recorre los padres
  vector<int> path;
  for (int x = n; x != -1; x = parent[x])
    path.push_back(x);
  reverse(path.begin(), path.end()); // lo invierte porque por como lo haces queda desde n hasta 1.

  cout << path.size() << "\n";
  for (int x : path)
    cout << x << " ";
  cout << "\n";
}