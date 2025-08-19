package tp04.ejercicio1;
import tp02.ejercicio2.*;

public class RecorridosAG {
    public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden (ArbolGeneral <Integer> a, Integer n){
        ListaEnlazadaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        recorridoPreOrden(a,n,lista);
        return lista;
    }

    private void recorridoPreOrden(ArbolGeneral <Integer> a, Integer n,ListaEnlazadaGenerica<Integer> lista){
        if ((a.getDato()%2==1) & (a.getDato()>n)){
            lista.agregarFinal(a.getDato());
        }
        ListaGenerica<ArbolGeneral<Integer>> hijos= a.getHijos();
        hijos.comenzar();
        while(!hijos.fin()){
            recorridoPreOrden(hijos.proximo(), n, lista);
        }
    }
    public ListaGenerica<Integer> numerosImparesMayoresQueInOrden (ArbolGeneral <Integer> a, Integer n){
        ListaEnlazadaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        recorridoInOrden(a,n,lista);
        return lista;
    }
    private void recorridoInOrden(ArbolGeneral <Integer> a, Integer n,ListaEnlazadaGenerica<Integer> lista){
        ListaGenerica<ArbolGeneral<Integer>> hijos= a.getHijos();
        hijos.comenzar();
        if (!hijos.fin()){
            recorridoInOrden(hijos.proximo(), n, lista);
        }
        if ((a.getDato()%2==1) & (a.getDato()>n)){
            lista.agregarFinal(a.getDato());
        }
        while(!hijos.fin()){
            recorridoInOrden(hijos.proximo(), n, lista);
        }
    }
    public ListaGenerica<Integer> numerosImparesMayoresQuePostOrden (ArbolGeneral <Integer> a, Integer n){
        ListaEnlazadaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        recorridoPostOrden(a,n,lista);
        return lista;
    }
    private void recorridoPostOrden(ArbolGeneral <Integer> a, Integer n,ListaEnlazadaGenerica<Integer> lista){
        ListaGenerica<ArbolGeneral<Integer>> hijos= a.getHijos();
        hijos.comenzar();
        while(!hijos.fin()){
            recorridoPostOrden(hijos.proximo(), n, lista);
        }
        if ((a.getDato()%2==1) & (a.getDato()>n)){
            lista.agregarFinal(a.getDato());
        }
    }
    public ListaGenerica<Integer> numerosImparesMayoresQuePorNiveles (ArbolGeneral <Integer> a, Integer n){
        ListaEnlazadaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        recorridoPorNiveles(a,n,lista);
        return lista;
    }
    private void recorridoPorNiveles(ArbolGeneral <Integer> a, Integer n,ListaEnlazadaGenerica<Integer> lista){
        ColaGenerica<ArbolGeneral<Integer>> cola= new ColaGenerica<ArbolGeneral<Integer>>();
        ArbolGeneral<Integer> aux;
        cola.encolar(a);
        while(!cola.esVacia()){
            aux= cola.desencolar();
            lista.agregarFinal(aux.getDato());
            if (aux.tieneHijos()){
                ListaGenerica<ArbolGeneral<Integer>> hijos= aux.getHijos();
                hijos.comenzar();
                while(!hijos.fin())
                    cola.encolar(hijos.proximo());
            }
        }
    }
}
