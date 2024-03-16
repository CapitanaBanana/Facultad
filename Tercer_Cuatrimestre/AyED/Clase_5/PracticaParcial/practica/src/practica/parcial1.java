package practica;

import java.io.Console;

public class parcial1 {
	public void caminosPares (ArbolGeneral<Character> arbol){
		ListaGenerica<ListaGenerica<Character>> lista = new ListaEnlazadaGenerica<ListaGenerica<Character>>();
		ListaGenerica<Character> aux= new ListaEnlazadaGenerica<Character>();
		lista.agregarFinal(aux);
		int cantidad = 0;
		caminosPares(cantidad,arbol,lista);
		for (int i=0; i<lista.tamanio(); i++){
			for (int j=0; i<lista.elemento(i).tamanio(); j++){
				System.out.print(lista.elemento(i).elemento(j)+ " ");
			}
			System.out.println();
		}
	}

	private void caminosPares(int cantidad,ArbolGeneral<Character> arbol,ListaGenerica<ListaGenerica<Character>> lista) {
		System.out.print(arbol.getDato());
		lista.elemento(cantidad).agregarFinal(arbol.getDato());
		if (arbol.esHoja()) {
			if (lista.elemento(cantidad).tamanio() % 2 != 0) {
				lista.eliminarEn(cantidad);
			} else
				cantidad = cantidad + 1;
			
		}
		else {
			ListaGenerica<ArbolGeneral<Character>> hijos = arbol.getHijos(); 
			hijos.comenzar();
			while (!hijos.fin()) {		
				caminosPares(cantidad,hijos.proximo(),lista);				
			}
		}
	}
}
