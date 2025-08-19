package ejercicio3;
//import tp02.ejercicio2.*;
public class ColaGenerica<T> extends NodoGenerico<T>{
	private NodoGenerico<T> inicio;
	private NodoGenerico<T> actual;
	private NodoGenerico<T> fin;
	private int tamanio;
	
	public int tamanio() {
		return this.tamanio;
	}
	public boolean esVacia() {
		return this.tamanio()==0;
	}
	public void comenzar() {
		actual = inicio;
	}
	public boolean encolar(T elem) {
		NodoGenerico<T> aux = new NodoGenerico<T>();
		aux.setDato(elem);
		if (this.inicio == null) {
			this.inicio = aux;
			this.actual = aux;
			this.fin = aux;
		} else {
			fin.setSiguiente(aux);
			fin = aux;
		}
		tamanio++;
		return true;
	}
	public T desencolar() {
		if(this.inicio!=null) {
			T aux=this.inicio.getDato();
			if(this.inicio.getSiguiente()==null){
				this.inicio=null;
			}
			else {
				this.inicio=this.inicio.getSiguiente();
			}
			this.tamanio--;
			return aux;
		}
	return null;
	}
	public T tope() {
		if(this.inicio!=null) {
			T aux=this.inicio.getDato();
			return aux;
		}
		return null;
	}
	
}
