package ejercicio5;
import ejercicio3.*;
import javafx.stage.StageStyle;
public class Mapa {
  Grafo<String> MapaCiudades;
  public void SetMapaCiudades(Grafo<String> mapaCiudades){
    MapaCiudades=mapaCiudades;
  }

  public int BuscarCiudad(String ciudad){
    ListaGenerica<Vertice<String>> lista = MapaCiudades.listaDeVertices();
    lista.comenzar();
    while(!lista.fin()){
      Vertice<String> dato= lista.proximo();
      if (dato.dato()== ciudad)
        return dato.getPosicion();
    }
    return -1;
  }
  public ListaGenerica<String> DevolverCamino(String ciudad1, String ciudad2){
    ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
    int partida= BuscarCiudad(ciudad1);
    if (partida!= -1){
      boolean[] marcas= new boolean [MapaCiudades.listaDeVertices().tamanio()+1];
      buscarCamino(MapaCiudades, camino, marcas, partida,ciudad2);
    }
    return camino; 
  }

  private boolean buscarCamino(Grafo<String> grafo, ListaGenerica<String> camino, boolean[] marcas, int i,String ciudad2){
    boolean destino=false;
    marcas[i]=true;
    Vertice<String> vertice = grafo.listaDeVertices().elemento(i);
    camino.agregarFinal(vertice.dato());
    if(vertice.dato()==ciudad2)
      destino= true;
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!destino & !adyacentes.fin()){
        int j= adyacentes.proximo().verticeDestino().getPosicion();
        if(!marcas[j])
          destino= buscarCamino(grafo, camino, marcas, j, ciudad2);
      }
      if (!destino)
        camino.eliminarEn(camino.tamanio());
    }
    return destino;
  }









  public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, ListaGenerica<String> ciudades){
    ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
    if (!MapaCiudades.esVacio()){
      int partida= BuscarCiudad(ciudad1);
      if (partida!= -1) {
      boolean[] marca= new boolean[MapaCiudades.listaDeVertices().tamanio()+1];
      while (!ciudades.fin()){
        int restringida= BuscarCiudad(ciudades.proximo());
        if (restringida!= -1)
          marca[restringida]=true;
      }
      caminoExceptuando(MapaCiudades, camino, partida, marca,ciudad2);
    }     
  }
  return camino;
}
  private boolean caminoExceptuando(Grafo<String> grafo, ListaGenerica<String> camino, int i, boolean[] marca, String destino){
    boolean llegada= false;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    camino.agregarFinal(vertice.dato());
    if (vertice.dato()== destino)
      llegada=true;
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!llegada & !adyacentes.fin()){
        int j= adyacentes.proximo().verticeDestino().getPosicion();
        if (!marca[j])
          llegada= caminoExceptuando(grafo, camino, j,marca, destino);
      }
      if(!llegada)
        camino.eliminarEn(camino.tamanio());
    }
    return llegada;
  }

  public ListaGenerica<String> caminoMasCorto (String ciudad1, String ciudad2){
    ListaGenerica<String> minimo= new ListaEnlazadaGenerica<String>();
    if(!MapaCiudades.esVacio()){
      int partida= BuscarCiudad(ciudad1);
      if (partida !=-1){
        boolean[] marca= new boolean[MapaCiudades.listaDeVertices().tamanio()+1];
        ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
        Minimo min= new Minimo();
        min.setMin(9999);
        int distancia=0;
        masCorto(MapaCiudades, marca, ciudad2, partida, camino, min, minimo, distancia);
      }
    }
    return minimo;
  }
  private void masCorto(Grafo<String> MapaCiudades,boolean[] marca, String destino, int i, ListaGenerica<String> camino, Minimo min, ListaGenerica<String> minimo, int distancia){
    marca[i]= true;
    Vertice<String> vertice= MapaCiudades.listaDeVertices().elemento(i);
    camino.agregarFinal(vertice.dato());
    if (vertice.dato()== destino){
      if (distancia<min.getMin()){
        min.setMin(distancia);
        clonar(minimo, camino);
      }
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= MapaCiudades.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!adyacentes.fin()){
        Arista<String> adyacente= adyacentes.proximo();
        int j= adyacente.verticeDestino().getPosicion();
        if (!marca[j]){
          distancia = distancia+ adyacente.peso();
          masCorto(MapaCiudades, marca, destino, j, camino, min, minimo, distancia);
          camino.eliminarEn(camino.tamanio());
          distancia= distancia- adyacente.peso();
          marca[j]=false;
        }
      }
    }

  }
  private void clonar(ListaGenerica<String> minimo, ListaGenerica<String> camino){
    minimo.comenzar();
    while(!minimo.fin()){
      minimo.eliminar(minimo.proximo());
    }
    camino.comenzar();
    while(!camino.fin()){
      minimo.agregarFinal(camino.proximo());
    }
  }

  /* public ListaGenerica<String> caminoMasCorto (String ciudad1, String ciudad2){
    Vertice<String> v1 = new VerticeImplListAdy<String>("Buenos Aires");
      Vertice<String> v2 = new VerticeImplListAdy<String>("Santiago");
      Grafo<String> gaux= new GrafoImplListAdy<String>();
      gaux.conectar(v1, v2,99999);
    ListaGenerica<Vertice<String>> minimo = new ListaEnlazadaGenerica<Vertice<String>>();
    minimo.agregarFinal(v1);
    minimo.agregarFinal(v2);
    if (!MapaCiudades.esVacio()){
      int partida= BuscarCiudad(ciudad1);
      if (partida!=-1){
        boolean [] marca= new boolean[MapaCiudades.listaDeVertices().tamanio()+1];
        ListaGenerica<Vertice<String>> actual = new ListaEnlazadaGenerica<Vertice<String>>();
        masCorto(MapaCiudades, actual, minimo, marca, partida, ciudad2);
      }
    }
    ListaGenerica<String> lista= new ListaEnlazadaGenerica<String>();
    minimo.comenzar();
    while(!minimo.fin()){
      lista.agregarFinal(minimo.proximo().dato());
    }
    return lista;
  }
  public void masCorto(Grafo<String> grafo, ListaGenerica<Vertice<String>> actual, ListaGenerica<Vertice<String>> minimo, boolean[] marca, int i, String destino){
    marca[i]=true;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    actual.agregarFinal(vertice);
    if (vertice.dato()==destino){
      if (distancia(actual)< distancia(minimo)){
        actual.comenzar();
        minimo.comenzar();
        while(!minimo.fin()){
          minimo.eliminar(minimo.proximo());
        }
        while(!actual.fin()){
          minimo.agregarFinal(actual.proximo());
        }
      }
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!adyacentes.fin()){
        int j= adyacentes.proximo().verticeDestino().getPosicion();
        if(!marca[j]){
          masCorto(grafo, actual, minimo, marca, j, destino);
          marca[j]=false;
          actual.eliminarEn(actual.tamanio());
        }
      }
      
    }
    
  }

  private int distancia(ListaGenerica<Vertice<String>> lista){
    int suma=0;
    lista.comenzar();
    int i=1;
    while(i< lista.tamanio()){
      suma=suma+MapaCiudades.peso(lista.elemento(i),lista.elemento(i+1));
      i++;
    }
    System.out.println(suma);
    return suma;
  } */
  /* public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto){
    int partida= BuscarCiudad(ciudad1);
    ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
    if (partida!= -1){
      boolean[] marca= new boolean [MapaCiudades.listaDeVertices().tamanio()+1];
      sinCargar(MapaCiudades, marca, partida, ciudad2, camino, tanqueAuto);
    }
    return camino;
  }
  private int sinCargar(Grafo<String> grafo, boolean[] marca, int i, String ciudad2, ListaGenerica<String> camino, int tanque){
    marca[i]= true;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    camino.agregarFinal(vertice.dato());
    if (vertice.dato()== ciudad2){
      return 9999;
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while((tanque!= 9999) && (!adyacentes.fin())){
        Arista<String> proxima= adyacentes.proximo();
        int j=proxima.verticeDestino().getPosicion();
        if (!marca[j]& (tanque-(proxima.peso())>=0)){
          tanque= tanque-(proxima.peso());
          tanque= sinCargar(grafo, marca, j, ciudad2, camino, tanque);
          if (tanque!=9999)
          camino.eliminarEn(camino.tamanio());
          tanque= tanque+(proxima.peso());
          marca[j]=false;
          
        }
        
      }
    }
    return tanque;
  } */
  public ListaGenerica<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto){
    int partida= BuscarCiudad(ciudad1);
    ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
    if (partida!= -1){
      ListaGenerica<String> aux= new ListaEnlazadaGenerica<String>();
      boolean[] marca= new boolean [MapaCiudades.listaDeVertices().tamanio()+1];
      sinCargar(MapaCiudades, marca, partida, ciudad2, camino, aux, tanqueAuto);
    }
    return camino;
  }
  private void sinCargar(Grafo<String> grafo, boolean[] marca, int i, String destino, ListaGenerica<String> camino, ListaGenerica<String> aux, int tanque){
    marca[i]=true;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    camino.agregarFinal(vertice.dato());
    if(vertice.dato()==destino){
      camino.comenzar();
      while(!camino.fin()){
        aux.agregarFinal(camino.proximo());
      }
    }
    else{
      ListaGenerica<Arista<String>> adyacentes = MapaCiudades.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while((!adyacentes.fin() & (aux.esVacia()))){
        Arista<String> arista= adyacentes.proximo();
        int j= arista.verticeDestino().getPosicion();
        if (!marca[j]){
          if (tanque-arista.peso() >=0 ){
            tanque= tanque-arista.peso();
            sinCargar(grafo, marca, j, destino, camino, aux, tanque);
            if (aux.esVacia()){
              tanque= tanque+arista.peso();
              camino.eliminarEn(camino.tamanio());
              marca[j]=false;
            }
            
          }
        }
      }
    }
  }

  public ListaGenerica<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto){
    ListaGenerica<String> minimo= new ListaEnlazadaGenerica<String>();
    if (!MapaCiudades.esVacio()){
      int partida= BuscarCiudad(ciudad1);
      if (partida!= -1){
        boolean[] marca= new boolean[MapaCiudades.listaDeVertices().tamanio()+1];
        Minimo min= new Minimo();
        min.setMin(9999);
        int cargas=0;
        ListaGenerica<String> camino= new ListaEnlazadaGenerica<String>();
        menorCarga(marca, partida, MapaCiudades, minimo,camino, ciudad2, tanqueAuto,tanqueAuto, min,cargas);
      }
    }
    return minimo;
  }
  private void menorCarga(boolean[] marca, int i,Grafo<String> grafo, ListaGenerica<String>minimo,ListaGenerica<String> camino, String destino,int tanqueAuto, int max, Minimo min, int cargas){
    marca[i]=true;
    Vertice<String> vertice= grafo.listaDeVertices().elemento(i);
    camino.agregarFinal(vertice.dato());
    if(vertice.dato()==destino){
      if (cargas<min.getMin()){
        clonar(minimo, camino);
      }
    }
    else{
      ListaGenerica<Arista<String>> adyacentes= grafo.listaDeAdyacentes(vertice);
      adyacentes.comenzar();
      while(!adyacentes.fin()){
        Arista<String> adyacente = adyacentes.proximo();
        int j= adyacente.verticeDestino().getPosicion();
        if (!marca[j]){
          if(max>= adyacente.peso()){
            if (tanqueAuto-adyacente.peso()<0){
              cargas=cargas+1;
              tanqueAuto= max;
            }
            tanqueAuto= tanqueAuto-adyacente.peso();
            menorCarga(marca, j, grafo, minimo, camino, destino, tanqueAuto,max, min, cargas);
            tanqueAuto=tanqueAuto+adyacente.peso();
            camino.eliminarEn(camino.tamanio());
            marca[j]=false;
          }
        }
      }
    }
  }
}

