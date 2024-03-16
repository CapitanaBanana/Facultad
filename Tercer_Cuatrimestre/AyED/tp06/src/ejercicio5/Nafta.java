package ejercicio5;
import ejercicio3.*;

public class Nafta{
  public Grafo<String> grafo;
  public Nafta(Grafo<String> g){
    grafo=g;
  }
  private int buscar(String ciudad){
    ListaGenerica<Vertice<String>> lista= grafo.listaDeVertices();
    lista.comenzar();
    while(!lista.fin()){
      Vertice<String> proximo= lista.proximo();
      if (proximo.dato()==ciudad)
        return proximo.getPosicion();
    }
    return -1;
  }
  public ListaGenerica<String> caminoConMenorCargaDeCombustible(String ciudad1, String ciudad2, int tanqueAuto){
    Minimo min= new Minimo();
    min.setCamino(new ListaEnlazadaGenerica<String>());
    if(!grafo.esVacio()){
      int salida=buscar(ciudad1);
      int destino= buscar(ciudad2);
      if(salida!=-1 & destino!=-1){
        ListaGenerica<String> lista= new ListaEnlazadaGenerica<String>();
        boolean[] marca= new boolean[grafo.listaDeVertices().tamanio() +1];
        min.setMin(9999);
        int cant=0;
        dfs(marca,salida, lista,tanqueAuto, tanqueAuto, min, cant, ciudad2);
      }
    }
    return min.getCamino();
  }
  private void dfs(boolean [] marca, int i, ListaGenerica<String> lista, int tanqueAuto, int naftaRestante, Minimo min, int cant, String destino){
    marca[i]=false;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    lista.agregarFinal(vertice.dato());
    if(vertice.dato()==destino){
      if (min.getMin()>cant){
        min.setCamino(lista.clonar());
        min.setMin(cant);
      }
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!adyacentes.fin()){
        Arista<String> adyacente= adyacentes.proximo();
        int j= adyacente.verticeDestino().getPosicion();
        if (!marca[j] & adyacente.peso()<=tanqueAuto){
          if (adyacente.peso()<naftaRestante){
            naftaRestante=tanqueAuto;
            cant++;
          }
          naftaRestante=naftaRestante- adyacente.peso();
          dfs(marca, j,lista, tanqueAuto,naftaRestante, min,cant, destino);
          marca[j]=false;
          naftaRestante= naftaRestante+adyacente.peso();
          lista.eliminarEn(lista.tamanio());
        }
      }
    }
  }
}