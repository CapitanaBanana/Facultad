package tp04.ejercicio1;

import tp02.ejercicio2.ColaGenerica;
import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;

public class AnalizadorArbol{
    public int devolverMaximoPromedio(ArbolGeneral<AreaEmpresa> arbol){
        ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<ArbolGeneral<AreaEmpresa>>();
        int max=-1;
        int niveles=0;
        int cant=0;
        int maxNivel;
        int act=0;
        cola.encolar(arbol);
        cola.encolar(null);
        while (!cola.esVacia()){
            ArbolGeneral<AreaEmpresa> aux= cola.desencolar();
            if (aux!=null){
                cant++;
                act= act+ aux.getDato()._tardanza;
                if (aux.tieneHijos()){
                    ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos= aux.getHijos();
                    while (!hijos.fin()){
                        cola.encolar(hijos.proximo());
                    }
                }   
            }
            else { 
                act= act/cant;
                    System.out.println("nivel: "+ niveles+" promedio: "+ act);
                    if (act>=max){
                        max = act;
                        maxNivel=niveles;
                    }
                act=0;
                cant=0;
                if (!cola.esVacia()){
                    cola.encolar(null);
                    niveles++;
                }
            }
                
        }
        return max; 
    }
}
class AreaEmpresa{
    String _area;
    Integer _tardanza;
    AreaEmpresa(String area, Integer tardanza){
        _area=area;
        _tardanza=tardanza;
    }
}
