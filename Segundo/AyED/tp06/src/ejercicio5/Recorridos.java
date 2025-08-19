package ejercicio5;

import ejercicio3.*;

public class Recorridos<T> {

	public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo) {
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio() + 1];// creamos un vector para utilizarlo para marcar si fue visitado// ya arrancan todas en false
		ListaGenerica<Vertice<T>> l = new ListaEnlazadaGenerica<Vertice<T>>();
		for (int i = 1; i <= grafo.listaDeVertices().tamanio(); i++) {
			if (!marca[i])// si no esta visitado
				dfs(grafo, l, marca, i);// llamamos a dfs con el grafo la lista la marca y la posicion del dato
		}
		return l;
	}

	private void dfs(Grafo<T> grafo, ListaGenerica<Vertice<T>> l, boolean[] marca, int i) {
		marca[i] = true;// marcamos como visitado
		Vertice<T> v = grafo.listaDeVertices().elemento(i);// conseguimos el vertice en base a la lista de vertices del grafo en la posicion i que seria la del dato actual
		l.agregarFinal(v);// agregamos el dato a la lista
		ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(v);// conseguimso lista de adyacencia
		ady.comenzar();
		while (!ady.fin()) {// mientras no se termine la lista de adyacentes
			int pos = ady.proximo().verticeDestino().getPosicion();// conseguimos la posicion del dato a visitar y verificamos si la posicion no fue visitada
			if (!marca[pos])// en caso de que no sea visitado
				dfs(grafo, l, marca, pos);// llamamos con dfs para hacer lo mismo
		}
	}

	public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo) {
		ListaEnlazadaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio() + 1];
		for (int i = 1; i <= grafo.listaDeVertices().tamanio(); i++) {
			if (!marca[i])// si no esta visitado
				bfs(grafo, lista, marca, i);// llamamos a dfs con el grafo la lista la marca y la posicion del dato
		}
		return lista;
	}

	private void bfs(Grafo<T> grafo, ListaGenerica<Vertice<T>> lista, boolean[] marca, int i) {
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		cola.encolar(grafo.listaDeVertices().elemento(i));
		marca[i] = true;
		while (!cola.esVacia()) {
			Vertice<T> vertice = cola.desencolar();
			lista.agregarFinal(vertice);
			ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(vertice);
			adyacentes.comenzar();
			while (!adyacentes.fin()) {
				Arista<T> arista = adyacentes.proximo();
				int j = arista.verticeDestino().getPosicion();
				if (!marca[j]) {
					marca[j] = true;
					cola.encolar(arista.verticeDestino());
				}
			}
		}
	}
}
