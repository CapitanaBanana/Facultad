package practica;


public class ColaGenerica<T>{
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

	public int getTamanio() {
		return this.tamanio;
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
    
    public T tope(){
        T aux;
		if (this.inicio == null) {
			aux= null;
		} else {
            aux= this.inicio.getDato();
		}
		return aux;
	}
    public boolean esVacia(){
        if (this.inicio == null) 
			return true;
        else return false;
    }
}
