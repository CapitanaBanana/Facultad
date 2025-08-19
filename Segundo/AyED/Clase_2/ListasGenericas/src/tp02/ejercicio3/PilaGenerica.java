package tp02.ejercicio3;

import tp02.ejercicio2.ListaGenerica;
import tp02.ejercicio2.NodoGenerico;

public class PilaGenerica<T> {
    /* primer nodo de la lista, si la lista esta vacia, inicio es null */
	/* primer nodo de la lista, si la lista esta vacia, inicio es null */
	private NodoGenerico<T> inicio;
	/*
	 * nodo actual que se va actualizando a medida que recorremos la lista, si
	 * la lista esta vacia, actual es null
	 */
	private NodoGenerico<T> actual;
	/* ultimo nodo de la lista, si la lista esta vacia, fin es null */
	private NodoGenerico<T> fin;
	/* cantidad de nodos en la lista */
	private int tamanio;
    public boolean apilar(T elem){
        NodoGenerico<T> aux = new NodoGenerico<T>();
		aux.setDato(elem);
		if (this.inicio == null) {
			this.inicio = aux;
			this.actual = aux;
			this.fin = aux;
		} else {
			aux.setSiguiente(this.inicio);
			this.inicio = aux;
		}
		this.tamanio++;
		return true;
	}
    public T desapilar(){
        T aux;
        if (this.inicio==null)
            aux=null;
        else{
            aux=this.inicio.getDato();
			this.inicio = this.inicio.getSiguiente();
			tamanio--;
        }
        return aux;
    }
	public T tope(){
        T aux;
        if (this.inicio==null)
            aux=null;
        else{
            aux=this.inicio.getDato();
        }
        return aux;
    }
	public boolean esVacia(){
        if (this.inicio==null)
            return true;
        else
            return false;
        }
}
