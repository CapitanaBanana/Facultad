typedef long long ll;

struct Edge {
	int x, y; // y es el next node
	ll w;
	
};
 
struct Node {
	int x; ll d;
	bool const operator < (const Node aux) const {
		return d > aux.d;
	}
};
 
vector<ll> dijsktra(int n, vector<vector<Edge>> &g) {
	vector<ll> dist(n,-1);
	priority_queue<Node> q;
	q.push({0,0});
	while(q.size() > 0) {
		auto [act,d] = q.top(); q.pop();
		if(dist[act] == -1) {
			dist[act] = d;
			for(auto [x,y,w] : g[act]) {
				if(dist[y] == -1) {
					q.push({y,dist[act]+w});
				}
			}
		}
	}
	return dist;
}