package tp3;

public class ContadorArbol extends ArbolBinario<Integer>{
    private ArbolBinario<Integer> arbol;


    public ContadorArbol(ArbolBinario<Integer> arbol) {
        this.arbol=arbol;
    }

    public ListaEnlazadaGenerica<Integer> numerosParesInOrden(){
        ListaEnlazadaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        inOrden(this.arbol, lista);
        return lista;
    }
    public void inOrden(ArbolBinario<Integer> arbol, ListaEnlazadaGenerica<Integer> lista){
        if (arbol.tieneHijoIzquierdo())
            inOrden(arbol.getHijoIzquierdo(),lista);
        if((arbol.getDato()%2)==0)
            lista.agregarFinal(arbol.getDato());
        if (arbol.tieneHijoDerecho())
            inOrden(arbol.getHijoDerecho(),lista);
    }
    public ListaEnlazadaGenerica<Integer> numerosParesPostOrden(){
        ListaEnlazadaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        postOrden(this.arbol, lista);
        return lista;
    }
    public void postOrden(ArbolBinario<Integer> arbol, ListaEnlazadaGenerica<Integer> lista){
        if (arbol.tieneHijoIzquierdo())
            postOrden(arbol.getHijoIzquierdo(),lista);
        if (arbol.tieneHijoDerecho())
            postOrden(arbol.getHijoDerecho(),lista);
        if((arbol.getDato()%2)==0)
            lista.agregarFinal(arbol.getDato());
    }
}
