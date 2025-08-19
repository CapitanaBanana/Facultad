//mal
package tp3;
/* recursito: caso base es que arbol sea hoja.
int maxRetardo(a:arbol){
if (a.eshoja)
    return arbol.dato
else 
    return a+ max(maximoretardo(a.hijoizquierdo), maximoretardo(a.hijoderecho));
}
*/ 
public class RedBinariaLlena {
    private ArbolBinario<Integer> arbol;
    public RedBinariaLlena(ArbolBinario<Integer> arbol){
        this.arbol=arbol;
    }
    /*public int retardoEnvio(){
        ColaGenerica<ArbolBinario<Integer>> cola= new ColaGenerica<ArbolBinario<Integer>>();
        int max=-1;
        int cant=0;
        int maxCant=-1;
        cola.encolar(this.arbol);
        cola.encolar(null);
        while(!cola.esVacia()){
            ArbolBinario<Integer> arbol=cola.desencolar();
            if (arbol!=null){
                if (arbol.tieneHijoIzquierdo()){//LE PONE AL HIJO IZQUIERDO LA SUMA DEL QUE ESTA PARADO + EL HIJO
                    arbol.getHijoIzquierdo().setDato(arbol.getHijoIzquierdo().getDato()+arbol.getDato());
                    cola.encolar(arbol.getHijoIzquierdo());//LO AGREGA A LA COLA
                }
                if (arbol.tieneHijoDerecho()){
                    arbol.getHijoDerecho().setDato(arbol.getHijoDerecho().getDato()+arbol.getDato());
                    cola.encolar(arbol.getHijoDerecho());
                }
                if (arbol.esHoja()){//SI NO TIENE HIJOS ES HOJA
                    cant++;
                    if (arbol.getDato()>=max){
                        max= arbol.getDato();//SUMO Y COMPARO CON EL MAXIMO, SI ES MAX ME GUARDO NRO DE HOJA
                        maxCant=cant;
                    }
                }
            } else if(!cola.esVacia()){
                cola.encolar(null);
            } 
        }
        System.out.println("La hoja con mayor retardo es la "+maxCant+", la cual tarda "+ max);
        return max;
    }*/
    /*
    public int maximoRetardo(){
        int max=-1;
        int cant=0;
        return retardoEnvio(this.arbol, max,cant);
    }
    private int retardoEnvio(ArbolBinario<Integer> a, int max, int cant){
        if(a.esHoja()){
            if (cant>max)
                max=cant;
            cant=0;
            return max;
        }
        else{
            retardoEnvio(a.getHijoDerecho(), max, cant+a.getDato());
            retardoEnvio(a.getHijoIzquierdo(), max, cant+a.getDato());
        }
        return
            max;
    }
     */
    public int princesaAccesible(){

        System.out.println("encontre el " + buscarPrincesa(this.arbol));
        return (buscarPrincesa(this.arbol));
    }
    private int buscarPrincesa(ArbolBinario<Integer> a){
        System.out.println(a.getDato());
        if (a.getDato()==1 | a.getDato()==2)
            return a.getDato();
        if(a.tieneHijoDerecho() && !(a.getHijoDerecho().getDato()==2))
            return buscarPrincesa(a.getHijoDerecho());
        if(a.tieneHijoIzquierdo() && !(a.getHijoIzquierdo().getDato()==2))
            return buscarPrincesa(a.getHijoIzquierdo());
        return a.getDato();
    }
}
