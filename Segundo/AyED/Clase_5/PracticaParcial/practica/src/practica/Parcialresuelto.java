package practica;

public class Parcialresuelto {
    public void resolver(ArbolBinario<Integer> arbol, int min){
        ListaGenerica<Integer> camact = new ListaEnlazadaGenerica<Integer>();
        ListaGenerica<Integer> camino = new ListaEnlazadaGenerica<Integer>();
        camino(camino,camact,arbol,min,0);
        System.out.println(camino.toString());
    }
    private int camino(ListaGenerica<Integer> camino, ListaGenerica<Integer> camact,ArbolBinario<Integer> a, int min, int cant){
        if (a.getDato() %2 ==0)
            cant= cant+1;
        camact.agregarFinal(a.getDato());
        if (a.esHoja()){
            if (cant>=min){
                camact.comenzar();
                while (!camact.fin())
                    camino.agregarFinal(camact.proximo());
            }
            else camact.eliminarEn(camact.tamanio());
        }
        else if (camino.esVacia()){
            if (a.tieneHijoDerecho())
                cant=cant+ camino(camino,camact,a.getHijoDerecho(),min,cant);
            if (a.tieneHijoIzquierdo())
                cant=cant+ camino(camino,camact,a.getHijoIzquierdo(),min,cant);
        }
        
        return cant;
    }
}
