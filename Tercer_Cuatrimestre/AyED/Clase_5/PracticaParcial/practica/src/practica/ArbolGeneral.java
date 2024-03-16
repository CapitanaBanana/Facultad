package practica;

import practica.*;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
        this(dato);
        if (hijos==null)
            this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
        else
            this.hijos = hijos;
    }

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}

	public Integer altura() {
		int alt=0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()){
			ArbolGeneral<T> aux = cola.desencolar();
			if (aux!= null){
				if (aux.tieneHijos()){
					ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
					hijos.comenzar();
					while (!hijos.fin()){
						cola.encolar(hijos.proximo());
					}
				}
			}
			else if (!cola.esVacia()){
				alt++;
				cola.encolar(null);
			}
		}
		return alt;
	}
	
	public Integer nivel(T dato) {
		Integer alt=0;
		boolean seguir = true;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia() & seguir){
			ArbolGeneral<T> aux= cola.desencolar();
			if ((aux != null) && (aux.getDato() == dato))
				seguir = false;
			else {
			if (aux!=null){
				if (aux.tieneHijos()){
					ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
					hijos.comenzar();
					while (!hijos.fin()){
						cola.encolar(hijos.proximo());
					}
				}
			}
			else if (!cola.esVacia()){
				alt++;
				cola.encolar(null);
			}
			}
			}
		if (seguir == true)
			return -1;
		else
			return alt;
	}

	public Integer ancho() {
		int max = 1;
		int cant = 0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()){
			ArbolGeneral<T> aux = cola.desencolar();
			if(aux!= null){
				if (aux.tieneHijos()){
					ListaGenerica<ArbolGeneral<T>> hijos = aux.getHijos();
					hijos.comenzar(); 
					while (!hijos.fin()){
						cant++;
						cola.encolar(hijos.proximo());
					}
					if (cant>=max)
						max=cant;
				}
			}
			else{
				cant=0;
				if (!cola.esVacia())
					cola.encolar(null);
			}
				
		}
		return max;
	}
	public Boolean esAncestro(T a, T b){
		ArbolGeneral<T> ancestro= buscarAncestro(a, this);
		if (ancestro!= null)
			return buscarDescendiente(b,ancestro);
		else return false;
	}
	private ArbolGeneral<T> buscarAncestro(T a, ArbolGeneral<T> arbol){
		ArbolGeneral res=null;
		System.out.println(arbol.getDato());
		if (arbol.getDato()==a){
			return arbol;
			
		}
		else {
			ListaGenerica<ArbolGeneral<T>> hijos = arbol.getHijos();
			hijos.comenzar();
			while(!hijos.fin()){
				res = buscarAncestro(a, hijos.proximo());
			}
		}
		return res;
	}
	private boolean buscarDescendiente(T b, ArbolGeneral<T> arbol){
		boolean res=false;
		System.out.println(arbol.getDato());
		if (arbol.getDato()==b){
			return true;
			
		}
		else {
			ListaGenerica<ArbolGeneral<T>> hijos = arbol.getHijos();
			hijos.comenzar();
			while((!hijos.fin())& !res){
				res = buscarDescendiente(b, hijos.proximo());
			}
		}
		return res;
	}
}