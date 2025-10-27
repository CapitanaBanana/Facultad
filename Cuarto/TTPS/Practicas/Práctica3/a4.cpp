#include <limits.h>
#include <stdio.h>
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

struct Edge
{
  int x, y; // y es el next node
  ll w;
};

struct Node
{
  int x;
  ll d;
  bool const operator<(const Node aux) const
  {
    return d > aux.d;
  }
};

vector<ll> dijsktra(int source, int n, vector<vector<Edge>> &g)
{
  vector<ll> dist(n, -1);
  priority_queue<Node> q;
  q.push({source, 0});
  while (q.size() > 0)
  {
    auto [act, d] = q.top();
    q.pop();
    if (dist[act] == -1)
    {
      dist[act] = d;
      for (auto [x, y, w] : g[act])
      {
        if (dist[y] == -1)
        {
          q.push({y, dist[act] + w});
        }
      }
    }
  }
  return dist;
}

int main()
{
  int n, m;
  cin >> n >> m;
  vector<vector<Edge>> graph(n);
  for (int i = 0; i < m; i++)
  {
    int x, y, w;
    cin >> x >> y >> w;
    x--;
    y--;

    Edge e{x, y, w};
    graph[x].push_back(e);
  }
  vector<ll> res = dijsktra(0, n, graph);
  for (int i = 0; i < n; i++)
  {

    cout << res[i] << ' ';
  }
}