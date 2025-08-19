package practica;

public class Parcialvideo {
    public static ListaGenerica<Integer> caminoAHojaMasLejana (ArbolGeneral<Integer> a){
    ListaGenerica<Integer> camAct = new ListaEnlazadaGenerica<Integer>(); 
    ListaGenerica<Integer> camMasLargo = new ListaEnlazadaGenerica<Integer>();
    if (!a.esVacio())
        caminoAHojaMasLejanaRec(a, camAct, camMasLargo);
    return camMasLargo;
    }
    public static void caminoAHojaMasLejanaRec(ArbolGeneral<Integer> a, ListaGenerica<Integer> camAct, ListaGenerica<Integer> camMasLargo){
        camAct.agregarFinal (a.getDato());
        if (a.esHoja()){
            if (camAct.tamanio()>camMasLargo.tamanio()) {
            //Vaciamos el camino más largo que teníamos hallado
                camMasLargo.comenzar();
                while(!camMasLargo.fin())
                    camMasLargo.eliminar(camMasLargo.proximo());
                    //Guardar el camino actual en el camino más largo
                camAct.comenzar();
                while(!camAct.fin())
                    camMasLargo.agregarFinal (camAct.proximo());

            }
        }
            else{
                ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
                hijos.comenzar();
                while (!hijos.fin()) {
                    caminoAHojaMasLejanaRec(hijos.proximo(), camAct, camMasLargo);
                    camAct.eliminarEn(camAct.tamanio());
                }
            }
    }
}
