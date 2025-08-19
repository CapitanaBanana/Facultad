package ejercicio5;
import ejercicio3.Grafo;
import ejercicio3.GrafoImplListAdy;
import ejercicio3.ListaEnlazadaGenerica;
import ejercicio3.ListaGenerica;
import ejercicio3.Vertice;
import ejercicio3.VerticeImplListAdy;

public class PruebaEj5 {
  public static void main(String[] args) {
  Vertice<String> v1 = new VerticeImplListAdy<String>("Buenos Aires");
        Vertice<String> v2 = new VerticeImplListAdy<String>("Santiago");
        Vertice<String> v3 = new VerticeImplListAdy<String>("Lima");
        Vertice<String> v4 = new VerticeImplListAdy<String>("Montevideo");
        Vertice<String> v5 = new VerticeImplListAdy<String>("Asuncion");
        Vertice<String> v6 = new VerticeImplListAdy<String>("Caracas");
        Vertice<String> v7 = new VerticeImplListAdy<String>("La Habana");

        Grafo<String> ciudades = new GrafoImplListAdy<String>();

        ciudades.agregarVertice(v1);
        ciudades.agregarVertice(v2);
        ciudades.agregarVertice(v3);
        ciudades.agregarVertice(v4);
        ciudades.agregarVertice(v5);
        ciudades.agregarVertice(v6);
        ciudades.agregarVertice(v7);

        ciudades.conectar(v1, v2,3);
        ciudades.conectar(v1, v3,2);
        ciudades.conectar(v1, v4,4);
        ciudades.conectar(v1, v5,10);
        ciudades.conectar(v2, v5,500);
        ciudades.conectar(v2, v7,4);
        ciudades.conectar(v3, v5,4);
        ciudades.conectar(v4, v5,6);
        ciudades.conectar(v6, v5,11);
        ciudades.conectar(v6, v7,10);
      Mapa mapa = new Mapa();
      mapa.MapaCiudades=ciudades;
      System.out.println("Camino de 1 a 2");
      System.out.println(mapa.DevolverCamino("Buenos Aires", "Asuncion").toString());

      System.out.println("Camino de 1 a 2 exceptuando");
      ListaGenerica<String> lista= new ListaEnlazadaGenerica<String>();
      lista.agregarFinal("Santiago");
      lista.agregarFinal("Montevideo");
      lista.agregarFinal("Lima");
      System.out.println(mapa.devolverCaminoExceptuando("Buenos Aires", "Asuncion", lista).toString());

      System.out.println("Camino minimo");
      System.out.println(mapa.caminoMasCorto("Buenos Aires", "Asuncion").toString());

      System.out.println("Camino sin combustible");
      System.out.println(mapa.caminoSinCargarCombustible("Buenos Aires", "Asuncion", 7).toString()); 

      System.out.println("Menos Cargas");
      System.out.println(mapa.caminoConMenorCargaDeCombustible("Buenos Aires", "Asuncion", 5).toString()); 
  }
}
