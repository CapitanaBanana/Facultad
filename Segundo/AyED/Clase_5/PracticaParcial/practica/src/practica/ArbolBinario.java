package practica;

public class ArbolBinario<T>{
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;   
	private ArbolBinario<T> hijoDerecho; 

	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());
	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	public int contarHojas() {
		int i=0;
		int rta=this.contar(i);//LLAMO AL OTRO METODO PARA INICIALIZAR CANT EN 0
		return rta;
	}
	private int contar(int cant){
		if(this.tieneHijoIzquierdo())
			cant= this.hijoIzquierdo.contar(cant);
		if(this.tieneHijoDerecho())
			cant= this.hijoDerecho.contar(cant);
		if (this.esHoja())
			cant++;
		return cant;
	}
	
	

    public ArbolBinario<T> espejo() {
		ArbolBinario<T> espejado= new ArbolBinario<>(this.getDato());
		if (!this.esVacio()){
				if(this.tieneHijoIzquierdo()){//DEL LADO IZ AGREGO EL DER Y LLAMO DESDE EL IZ
					espejado.agregarHijoDerecho(this.getHijoIzquierdo().espejo());
				}
				if(this.tieneHijoDerecho()){
					espejado.agregarHijoIzquierdo(this.getHijoDerecho().espejo());
				}
			return espejado;
		}
		return null;
	}

	/*public int contarNiveles(){
		int cant=0;
		ColaGenerica<ArbolBinario<T>> cola= new ColaGenerica<>();
		cola.encolar(this);
		while(!(cola.esVacia())) {
			int nivelSize = cola.getTamanio();
			for (int i = 0; i < nivelSize; i++) {
				ArbolBinario<T> arbol = cola.desencolar();
				if (arbol.tieneHijoIzquierdo()) {
					cola.encolar(arbol.hijoIzquierdo);
				}
				if (arbol.tieneHijoDerecho()) {
					cola.encolar(arbol.hijoDerecho);
				}
			}
			cant++;
		}
		return cant;
	}*/
	//ESTO ESTA AL PEPE, ERA PARA PROBAR
	public int contarNiveles(){
		int cant=0;
		ColaGenerica<ArbolBinario<T>> cola= new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()){
			ArbolBinario<T> arbol=cola.desencolar();
			if (arbol!=null){
				if (arbol.tieneHijoIzquierdo()) 
					cola.encolar(arbol.hijoIzquierdo);
				
				if (arbol.tieneHijoDerecho()) 
					cola.encolar(arbol.hijoDerecho);
			
			}
			else if(!cola.esVacia()){
				cant=cant+1;
				cola.encolar(null);
			}
		}
		return cant;
	}
	/* public void entreNiveles(int n, int m){
		System.out.println("Nodos entre "+n +" y "+ m);
		int niveles=0;
		ColaGenerica<ArbolBinario<T>> cola= new ColaGenerica<>();
		cola.encolar(this);
		while(!(cola.esVacia())) {
			int nivelSize = cola.getTamanio();
			for (int i = 0; i < nivelSize; i++) {
				ArbolBinario<T> arbol = cola.desencolar();
				if (arbol.tieneHijoIzquierdo()) {
					cola.encolar(arbol.hijoIzquierdo);
				}
				if (arbol.tieneHijoDerecho()) {
					cola.encolar(arbol.hijoDerecho);
				}
				if (niveles>=n & niveles<=m)
					System.out.print(arbol.getDato()+" ");
			}
			if (niveles==m)
				break;
			niveles++;
		}
	}*/

	// EL DE ARRIBA ANDA PERO ES MEDIO RARO, ESTE LO DIO LA CATEDRA
	public void entreNiveles(int n, int m){
		System.out.println("Nodos entre "+n +" y "+ m);
		int niveles=0;
		ColaGenerica<ArbolBinario<T>> cola= new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(this);
		cola.encolar(null);//ES NECESARIO ENCOLAR NULL AL PRINCIPIO? RTA=SI, SEPARA NIVEL 1 DE 2
		while (!cola.esVacia()){
			ArbolBinario<T> arbol=cola.desencolar();
			if (arbol!=null){
				if (niveles>=n & niveles<=m)
					System.out.print(arbol.getDato()+ " ");
				if (arbol.tieneHijoIzquierdo()) 
					cola.encolar(arbol.hijoIzquierdo);
				
				if (arbol.tieneHijoDerecho()) 
					cola.encolar(arbol.hijoDerecho);
			
			}
			else if(!cola.esVacia()){//BASICAMENTE SE SEPARAN LOS NIVELES POR UN NULL ENTONCES CUANDO ENCUENTRO UNO ES PORQUE CAMBIO
				niveles++;
				cola.encolar(null);
			}
		}
	}
}
