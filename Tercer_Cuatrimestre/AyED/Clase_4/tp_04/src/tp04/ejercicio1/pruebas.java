package tp04.ejercicio1;

import java.io.Console;

import tp02.ejercicio2.*;

public class pruebas {
    public static void main(String[] args) {
        ArbolGeneral<Integer> arbol = new ArbolGeneral<>(1);
        ArbolGeneral<Integer> n1 = new ArbolGeneral<>(5);
        n1.agregarHijo(new ArbolGeneral<>(6));
        n1.agregarHijo(new ArbolGeneral<>(9));
        n1.agregarHijo(new ArbolGeneral<>(17));
        ArbolGeneral<Integer> n2 = new ArbolGeneral<>(3);
        n2.agregarHijo(new ArbolGeneral<>(21));
        n1.agregarHijo(n2);
        arbol.agregarHijo(new ArbolGeneral<>(3));
        arbol.agregarHijo(new ArbolGeneral<>(7));
        arbol.agregarHijo(new ArbolGeneral<>(8));
        arbol.agregarHijo(n1);
        RecorridosAG recorrido = new RecorridosAG();
        ListaGenerica<Integer> lista = recorrido.numerosImparesMayoresQuePorNiveles(arbol,3);
        lista.comenzar();
        for (int i = 0; i < lista.tamanio(); i++) {
            System.out.println(lista.proximo());
        }
        System.out.println("Altura: "+ arbol.altura());
        System.out.println("el 17 esta en: "+ arbol.nivel(17));
        System.out.println("el ancho es: "+ arbol.ancho());
        System.out.println(arbol.esAncestro(5, 17));
    }

}

