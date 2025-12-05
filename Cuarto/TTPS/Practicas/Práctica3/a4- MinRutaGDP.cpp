// la distancia mínima desde el nodo 0 a todos los nodos usando Dijkstra con priority_queue.
#include <limits.h>
#include <stdio.h>
#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

struct Edge
{
  int x, y; // y es el nodo destino
  ll w;     // peso de la arista
};

struct Node
{
  int x; // ciudad
  ll d;  // distancia provisional desde el origen
  bool const operator<(const Node aux) const
  {
    return d > aux.d; // Hace que el nodo con menor distancia salga primero.
  }
};
// (raiz, cantidad de nodos y pasa por referencia a g)
vector<ll> dijsktra(int source, int n, vector<vector<Edge>> &g) // Devuelve un vector dist con la distancia mínima desde source a cada nodo.
{
  vector<ll> dist(n, -1); // todas las distancias arrancan en -1: no visitada
  priority_queue<Node> q;
  q.push({source, 0}); // meto source, 0 a la cola (origen)
  while (q.size() > 0) // mientras haya nodos por procesar
  {
    auto [act, d] = q.top(); // sacamos el más cercano
    q.pop();
    if (dist[act] == -1)
    {
      dist[act] = d; // para llegar al nodo en el que estoy tengo que tomar lo que ya recorrí
      for (auto [x, y, w] : g[act])
      {
        if (dist[y] == -1)
        {
          q.push({y, dist[act] + w}); // le suma el peso de la arista actual
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

    Edge e{x, y, w};       // crea las aristas con los pesos
    graph[x].push_back(e); // las mete en el grafo
  }
  vector<ll> res = dijsktra(0, n, graph);
  for (int i = 0; i < n; i++)
  {

    cout << res[i] << ' ';
  }
}