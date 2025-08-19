package ejercicio5;
import ejercicio3.*;
public class Parcial10{

  private Grafo<String> grafo;

  public Parcial10(Grafo<String> g){
    grafo=g;
  }
  private int buscar(){
    ListaGenerica<Vertice<String>> lista= grafo.listaDeVertices();
    lista.comenzar();
    while(!lista.fin()){
      Vertice<String> proximo= lista.proximo();
      if (proximo.dato()=="Casa Caperucita"){
        return proximo.getPosicion();
      }
    }
    return -1;
  }
  public ListaGenerica<ListaGenerica<String>> recorridosMasSeguro(){
    ListaGenerica<ListaGenerica<String>> caminos= new ListaEnlazadaGenerica<ListaGenerica<String>>();
    if (!grafo.esVacio()){
      int pos=buscar();
      if (pos!=-1){
        boolean[] marca= new boolean[grafo.listaDeVertices().tamanio()+1];
        ListaGenerica<String> actual= new ListaEnlazadaGenerica<String>();
        dfs(marca,pos,actual,caminos);
      }
    }
    return caminos;
  }
  private void dfs(boolean[] marca, int i, ListaGenerica<String> actual, ListaGenerica<ListaGenerica<String>> caminos){
    marca[i]=true;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    actual.agregarFinal(vertice.dato());
    if (vertice.dato()== "Casa Abuelita"){
      caminos.agregarFinal(actual.clonar());
    }
    else{
      ListaGenerica<Arista<String>> adyacentes=grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!adyacentes.fin()){
        Arista<String> adyacente= adyacentes.proximo();
        int j= adyacente.verticeDestino().getPosicion();
        if (!marca[j] & adyacente.peso()<5){
          dfs(marca, j, actual, caminos);
          marca[i]=false;
          actual.eliminarEn(actual.tamanio());}
          
      } 
    }
  }
}