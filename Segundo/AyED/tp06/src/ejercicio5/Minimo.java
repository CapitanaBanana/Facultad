package ejercicio5;

import ejercicio3.ListaEnlazadaGenerica;
import ejercicio3.ListaGenerica;

public class Minimo {
  private int Min;
  private ListaGenerica<String> camino;

  public void setMin(int min){
    Min=min;
  }
  public int getMin(){
    return Min;
  }
  public void setCamino(ListaGenerica<String> c){
    camino=c;
  }
  public ListaGenerica<String> getCamino(){
    return camino;
}
}
