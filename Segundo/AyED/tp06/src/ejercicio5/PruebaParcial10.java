package ejercicio5;

import ejercicio3.Grafo;
import ejercicio3.GrafoImplListAdy;
import ejercicio3.ListaEnlazadaGenerica;
import ejercicio3.ListaGenerica;
import ejercicio3.Vertice;
import ejercicio3.VerticeImplListAdy;
public class PruebaParcial10 {
	public static void main(String[] args) {
		Vertice<String> CasaCaperucita = new VerticeImplListAdy<String>("Casa Caperucita");
		Vertice<String> Claro1 = new VerticeImplListAdy<String>("Claro 1");
		Vertice<String> Claro2 = new VerticeImplListAdy<String>("Claro 2");
		Vertice<String> Claro3 = new VerticeImplListAdy<String>("Claro 3");
		Vertice<String> Claro4 = new VerticeImplListAdy<String>("Claro 4");
		Vertice<String> Claro5 = new VerticeImplListAdy<String>("Claro 5");
		Vertice<String> CasaAbuelita = new VerticeImplListAdy<String>("Casa Abuelita");
		
		Grafo<String>bosque = new GrafoImplListAdy<String>();
		
		bosque.agregarVertice(CasaCaperucita);
		bosque.agregarVertice(CasaAbuelita);
		bosque.agregarVertice(Claro1);
		bosque.agregarVertice(Claro2);
		bosque.agregarVertice(Claro3);
		bosque.agregarVertice(Claro4);
		bosque.agregarVertice(Claro5);
		
		bosque.conectar(CasaCaperucita, Claro1,3);
		bosque.conectar(Claro1, CasaCaperucita,3);
		bosque.conectar(CasaCaperucita, Claro2,4);
		bosque.conectar(Claro2, CasaCaperucita,4);
		bosque.conectar(CasaCaperucita, Claro3,4);
		bosque.conectar(Claro3, CasaCaperucita,4);
		bosque.conectar(Claro1, Claro2,4);
		bosque.conectar(Claro2, Claro1,4);
		bosque.conectar(Claro1, Claro5,3);
		bosque.conectar(Claro5, Claro1,3);
		bosque.conectar(Claro2, Claro5,11);
		bosque.conectar(Claro5, Claro2,11);
		bosque.conectar(Claro2, Claro4,10);
		bosque.conectar(Claro4, Claro2,10);
		bosque.conectar(Claro3, Claro5,15);
		bosque.conectar(Claro5, Claro3,15);
		bosque.conectar(Claro5, CasaAbuelita,4);
		bosque.conectar(CasaAbuelita, Claro5,4);
		bosque.conectar(Claro4, CasaAbuelita,9);
		bosque.conectar(CasaAbuelita, Claro4,9);
		
		Parcial10 parcial = new Parcial10(bosque);
		
		ListaGenerica<ListaGenerica<String>> lis = parcial.recorridosMasSeguro();
		lis.comenzar();
    while(!lis.fin()){
      System.out.println(lis.proximo());
    }
		
	}

}
