package tp3;

import java.io.Console;

public class Prueba {
    public static void main(String[] args) {
        ArbolBinario<Integer> arbol = new ArbolBinario<>(0);
        arbol.agregarHijoIzquierdo(new ArbolBinario<>(7));
        arbol.agregarHijoDerecho(new ArbolBinario<>(3));
        arbol.getHijoIzquierdo().agregarHijoIzquierdo(new ArbolBinario<>(1));
        arbol.getHijoIzquierdo().agregarHijoDerecho(new ArbolBinario<>(5));
        arbol.getHijoDerecho().agregarHijoDerecho(new ArbolBinario<>(7));
        arbol.getHijoDerecho().getHijoDerecho().agregarHijoIzquierdo(new ArbolBinario<>(1));
        //arbol.getHijoDerecho().getHijoDerecho().agregarHijoDerecho(new ArbolBinario<>(8));
        //EJ 4: RED BINARIA(IMPRIMO TODO AHI)
        RedBinariaLlena red=new RedBinariaLlena(arbol);
        System.out.println("maximo retardo: "+red.princesaAccesible());
        //EJ 2 A, B Y C 
        recorrerArbol(arbol);
        System.out.println();
        System.out.println("hojas "+ arbol.contarHojas()); 
        System.out.println();

        ArbolBinario<Integer> espejo= arbol.espejo();
        recorrerArbol(espejo);
        System.out.println();
        //ESPEJO DE NUEVO PARA CHEQUEAR HABERLO HECHO BIEN
        ArbolBinario<Integer> espejo2= espejo.espejo();
        recorrerArbol(espejo2);
        System.out.println();

        System.out.println("niveles "+arbol.contarNiveles());
        System.out.println();
        arbol.entreNiveles(2, 3);
        System.out.println();

        //EJ 3 A Y B. IMPRIMEN PARES DEL ARBOL REOCRRIENDO INORDEN Y POSTORDEN
        //OJO, IMPRIMEN IGUAL PQ QUEDAN JUSTO EN EL MISMO LUGAR, CAMBIANDO LA RAIZ POR UN PAR SE VE LA DIFERENCIA 
        ContadorArbol contador= new ContadorArbol(arbol);
        System.out.println(contador.numerosParesInOrden());
        System.out.println();
        System.out.println(contador.numerosParesPostOrden());
        System.out.println();


        //EJ 5: SUMA NIVEL
        ProfundidadDeArbolBinario profundidad= new ProfundidadDeArbolBinario(arbol);
        System.out.println("Profundidad: "+ profundidad.sumaElementosProfundidad(2));
    }

    //PARA IR IMPRIMIENDO LOS ARBOLES Y VER QUE ESTEN BIEN
    private static void recorrerArbol(ArbolBinario<Integer> nodo){
        if (!(nodo.esVacio())){
            if (nodo.tieneHijoIzquierdo())
                recorrerArbol(nodo.getHijoIzquierdo());
                System.out.print(nodo.toString()+ " ");
            if (nodo.tieneHijoDerecho())
                recorrerArbol(nodo.getHijoDerecho());
        }
    }
    // ESTO NI LO USO PQ HACIENDO PRINT EN UNA LISTA SE IMPRIME MAS LINDO
    private static void imprimirLista(ListaEnlazadaGenerica<Integer> lista){
        lista.comenzar();
        for (int i = 0; i < lista.tamanio(); i++) {
            System.out.println(lista.proximo());
        }
    }
    
}
