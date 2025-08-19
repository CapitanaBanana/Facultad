package practica;

public class pruebaparcialresuelto {
    public static void main(String[] args) {
        ArbolBinario<Integer> arbol7 = new ArbolBinario<Integer>(7); 
        ArbolBinario<Integer> arbol56 = new ArbolBinario<Integer>(56);
        ArbolBinario<Integer> arbol38 = new ArbolBinario<Integer>(38);
        ArbolBinario<Integer> arbol87 =new ArbolBinario<Integer>(87);
        ArbolBinario<Integer> arbol77 = new ArbolBinario<Integer>(77);
        ArbolBinario<Integer> arbol25 =new ArbolBinario<Integer>(25);

        arbol7.agregarHijoIzquierdo(arbol56);
        arbol7.agregarHijoDerecho(arbol25);

        arbol56.agregarHijoIzquierdo(arbol38);
        arbol38.agregarHijoDerecho(arbol77);
        arbol38.agregarHijoDerecho(arbol87);

        Parcialresuelto prueba = new Parcialresuelto();
        prueba.resolver(arbol7, 2);
    }
}
