package ejercicio5;

import ejercicio3.Arista;
import ejercicio3.Grafo;
import ejercicio3.ListaEnlazadaGenerica;
import ejercicio3.ListaGenerica;
import ejercicio3.Vertice;

public class VisitaOslo {
  private int BuscarCiudad(Grafo<String> lugares, String lugar){
    ListaGenerica<Vertice<String>> lista= lugares.listaDeVertices();
    lista.comenzar();
    while(!lista.fin()){
      Vertice<String> dato= lista.proximo();
      if (dato.dato()== lugar)
        return dato.getPosicion();
    }
    return -1;
  }
  ListaGenerica<String> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo, ListaGenerica<String> lugaresRestringidos){
    ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
    if (!lugares.esVacio()){
      int partida=BuscarCiudad(lugares, "Ayuntamiento");
      if (partida!=-1){
        boolean [] marca= new boolean[lugares.listaDeVertices().tamanio()+1];
        lugaresRestringidos.comenzar();
        while(!lugaresRestringidos.fin()){
          marca[BuscarCiudad(lugares, lugaresRestringidos.proximo())]=true;
        }
        ListaGenerica<String> aux= new ListaEnlazadaGenerica<String>();
        paseo(lugares,partida, destino, camino, aux, maxTiempo,marca);
      }
    }
    return camino;
  }
  private void paseo(Grafo<String> lugares,int i, String destino, ListaGenerica<String> camino, ListaGenerica<String> aux, int  maxTiempo, boolean[] marca){
    marca[i]=true;
    Vertice<String> vertice= lugares.listaDeVertices().elemento(i);
    aux.agregarFinal(vertice.dato());
    if (vertice.dato()==destino){
      aux.comenzar();
      while(!aux.fin()){
        camino.agregarFinal(aux.proximo());
      }
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= lugares.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(camino.esVacia() & !adyacentes.fin()){
        Arista<String> ady= adyacentes.proximo();
        int pos= ady.verticeDestino().getPosicion();
        if((!marca[pos]) && (maxTiempo-ady.peso()>=0)){
            maxTiempo= maxTiempo-ady.peso();
            paseo(lugares, pos, destino, camino, aux, maxTiempo, marca);
            if (camino.esVacia()){
              maxTiempo= maxTiempo+ady.peso();
              marca[pos]=false;
              aux.eliminarEn(aux.tamanio());
            }
          }
        }
    }
  }
}
