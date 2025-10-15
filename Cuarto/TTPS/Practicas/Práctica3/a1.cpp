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
    graph[u].push_back(v);
    graph[v].push_back(u);
  }
  vector<bool> visited(n + 1, false);
  vector<int> parent(n + 1, -1);
  q.push(1);
  visited[1] = true;
  while (!q.empty())
  {
    int nodo = q.front();
    q.pop();
    for (auto &&i : graph[nodo])
    {
      if (visited[i] == false)
      {
        visited[i] = true;
        parent[i] = nodo;
        q.push(i);
      }
    }
  }
  if (!visited[n])
  {
    cout << "IMPOSSIBLE\n";
    return 0;
  }

  vector<int> path;
  for (int x = n; x != -1; x = parent[x])
    path.push_back(x);
  reverse(path.begin(), path.end());

  cout << path.size() << "\n";
  for (int x : path)
    cout << x << " ";
  cout << "\n";
}