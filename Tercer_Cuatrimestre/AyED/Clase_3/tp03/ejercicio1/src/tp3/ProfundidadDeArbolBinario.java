package tp3;

public class ProfundidadDeArbolBinario {
    private ArbolBinario<Integer> arbol;
    public ProfundidadDeArbolBinario(ArbolBinario<Integer> arbol){
        this.arbol=arbol;
    }
    public int sumaElementosProfundidad(int p){
		int niveles=0;
        int suma=0;
		ColaGenerica<ArbolBinario<Integer>> cola= new ColaGenerica<ArbolBinario<Integer>>();
		cola.encolar(this.arbol);
		cola.encolar(null);//ES NECESARIO ENCOLAR NULL AL PRINCIPIO? RTA=SI, SEPARA NIVEL 1 DE 2
		while (!cola.esVacia()){
			ArbolBinario<Integer> arbol=cola.desencolar();
			if (arbol!=null){
				if (niveles==p){
					suma= suma+ arbol.getDato();
                    System.out.println("dato: "+ arbol.getDato());
                }
				if (arbol.tieneHijoIzquierdo()) 
					cola.encolar(arbol.getHijoIzquierdo());
				
				if (arbol.tieneHijoDerecho()) 
					cola.encolar(arbol.getHijoDerecho());
			
			}
			else if(!cola.esVacia()){//BASICAMENTE SE SEPARAN LOS NIVELES POR UN NULL ENTONCES CUANDO ENCUENTRO UNO ES PORQUE CAMBIO
				niveles++;
				cola.encolar(null);
			}
		}
        return suma;
    }
}
