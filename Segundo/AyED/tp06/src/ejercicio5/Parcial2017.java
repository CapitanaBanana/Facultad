package ejercicio5;
import ejercicio3.Grafo;
import ejercicio3.GrafoImplListAdy;
import ejercicio3.ListaEnlazadaGenerica;
import ejercicio3.ListaGenerica;
import ejercicio3.Vertice;
import ejercicio3.VerticeImplListAdy;
import ejercicio3.Arista;

public class Parcial2017{

  private int buscar(String ciudad, Grafo<String> grafo){
    ListaGenerica<Vertice<String>> lista= grafo.listaDeVertices();
    lista.comenzar();
    while(!lista.fin()){
      Vertice<String> proximo= lista.proximo();
      if (proximo.dato()==ciudad)
        return proximo.getPosicion();
    }
    return -1;
  }

  ListaGenerica<String> caminoDistanciaMaxima(Grafo<String> ciudades, String origen, String destino, int distanciaMaxima){
    ListaGenerica<String> lista= new ListaEnlazadaGenerica<String>();
    int pos= buscar(origen, ciudades);
    if (pos!= -1){
      boolean[] marca= new boolean [ciudades.listaDeVertices().tamanio()+1];
      dfs(ciudades, pos, marca, lista, destino);
    }
    return lista;
  }
  private boolean dfs(Grafo<String> grafo, int i, boolean[] marca, ListaGenerica<String> lista, String destino){
    boolean fin=false;
    marca[i]=true;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i); 
    lista.agregarFinal(vertice.dato());
    if (vertice.dato()==destino){
      return true;
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!adyacentes.fin() & !fin){
        Arista<String> adyacente= adyacentes.proximo();
        int j= adyacente.verticeDestino().getPosicion();
        if (!marca[j] & adyacente.peso()<=80)
          fin=dfs(grafo, j, marca, lista, destino);
      }
      if(!fin)
        lista.eliminarEn(lista.tamanio());
    }
    return fin;
  }
}