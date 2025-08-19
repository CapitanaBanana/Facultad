package practica;

public class practica {
    public static void main(String[] args) {
        ArbolGeneral<Integer> g = new ArbolGeneral<Integer> (1);
		
		ArbolGeneral<Integer> e = new ArbolGeneral<Integer> (3);
		ArbolGeneral<Integer> f = new ArbolGeneral<Integer> (2);
		ArbolGeneral<Integer> j = new ArbolGeneral<Integer> (5);
		
		ArbolGeneral<Integer> a = new ArbolGeneral<Integer> (7);
		ArbolGeneral<Integer> b = new ArbolGeneral<Integer> (9);
		ArbolGeneral<Integer> c = new ArbolGeneral<Integer> (6);
		
		ArbolGeneral<Integer> d = new ArbolGeneral<Integer> (10);
		
		ArbolGeneral<Integer> h = new ArbolGeneral<Integer> (6);
		ArbolGeneral<Integer> i = new ArbolGeneral<Integer> (10);
		
		g.agregarHijo(e);
		g.agregarHijo(f);
		g.agregarHijo(j);
		
		e.agregarHijo(a);
		e.agregarHijo(b);
		e.agregarHijo(c);
		
		f.agregarHijo(d);
		
		j.agregarHijo(h);
		j.agregarHijo(i);
        ListaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
		caminonodos(g, lista);
        System.out.println(lista.toString());
}
    static void imprimirTodosCaminos (ArbolGeneral<Integer> a, ListaGenerica<Integer> l, ListaEnlazadaGenerica<ListaGenerica<Integer>> listadelistas){
        l.agregarFinal(a.getDato());
        if (a.esHoja()){
            listadelistas.agregarFinal(l.clonar());
            l.eliminarEn(l.tamanio());
        }
        else{
            
            ListaGenerica<ArbolGeneral<Integer>> hijos= a.getHijos();
            while (!hijos.fin())
                imprimirTodosCaminos(hijos.proximo(),l,listadelistas);    
            l.eliminarEn(l.tamanio());
        }
    }

    static boolean imprimirCaminoHasta(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, int num){
        l.agregarFinal(a.getDato());
        if (a.getDato()==num){
            return true;
        }
        else {
            ListaGenerica<ArbolGeneral<Integer>> hijos= a.getHijos();
            while (!hijos.fin()){
                if (imprimirCaminoHasta(hijos.proximo(), l, num))
                    return true;
                l.eliminarEn(l.tamanio());
            }
            return false;
        }
    }
    static ListaGenerica<ListaGenerica<ArbolGeneral<Integer>>> caminosPares(ArbolGeneral<Integer> arbol){
        ListaGenerica<ArbolGeneral<Integer>> lista = new ListaEnlazadaGenerica<ArbolGeneral<Integer>>();
        ListaGenerica<ListaGenerica<ArbolGeneral<Integer>>> listadelistas = new ListaEnlazadaGenerica<ListaGenerica<ArbolGeneral<Integer>>>();
        camino(arbol,lista,listadelistas);
        while (!listadelistas.fin()){
            ListaGenerica<ArbolGeneral<Integer>> aux = listadelistas.proximo();
            while (!aux.fin()){
                System.out.print(aux.proximo().getDato());
            }
        System.out.println("");    
    }
        return listadelistas;
    }
    static void camino(ArbolGeneral<Integer> a, ListaGenerica<ArbolGeneral<Integer>> lista, ListaGenerica<ListaGenerica<ArbolGeneral<Integer>>> listadelistas){
        lista.agregarFinal(a);
        if (a.esHoja()){
            if (lista.tamanio()%2==0){
                listadelistas.agregarFinal(lista.clonar());//si es par, agrego una copia de la lista;
            }
            lista.eliminarEn(lista.tamanio()); //sino borro el último elemento para irme por otro camino
        }
        else{
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            while (!hijos.fin())
                camino(hijos.proximo(), lista, listadelistas);
            lista.eliminarEn(lista.tamanio());
        }

    }
    static void caminoMasLargo(ArbolGeneral<Integer> a, ListaGenerica<ArbolGeneral<Integer>> camino, ListaGenerica<ArbolGeneral<Integer>> act){
        act.agregarFinal(a);
        if (a.esHoja()){
            if (act.tamanio()>camino.tamanio()){
                camino.comenzar();
                while(!camino.fin()){
                    camino.eliminar(camino.proximo());
                }
                act.comenzar();
                while (!act.fin()){
                    camino.agregarFinal(act.proximo());
                }
            }
        }
        else{
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            hijos.comenzar();
            while (!hijos.fin()){
                caminoMasLargo(hijos.proximo(), camino, act);
                act.eliminarEn(act.tamanio());//va adentro para no tener que hacer un eliminar si es hoja
            }
            
        }
        
    }
    static Integer sumaImparesPostOrden(ArbolBinario<Integer> a, int nro){
        int suma=0;
        if (a.tieneHijoDerecho()){
            suma = suma+ (sumaImparesPostOrden(a.getHijoDerecho(), nro));
        }
        if (a.tieneHijoIzquierdo()){
            suma = suma+ sumaImparesPostOrden(a.getHijoIzquierdo(), nro);
        }
        if (a.getDato()%2==1 && a.getDato()>nro){
            suma=suma+ a.getDato();
        }
        return suma;
    }
        static void buscarPrincesa(ArbolGeneral<Integer> a){
            ListaGenerica<ListaGenerica<Integer>> listadecaminos= new ListaEnlazadaGenerica<ListaGenerica<Integer>>();
            ListaGenerica<Integer> camino = new ListaEnlazadaGenerica<Integer>();
            buscar(a,camino,listadecaminos);
            boolean ok=false; 
            while(!listadecaminos.fin() && !ok){
                ok=true;
                camino= listadecaminos.proximo();
                while (!camino.fin() && ok){
                    if(camino.proximo()==3){
                        ok=false;
                    }
                }
            }
            System.out.println(camino.toString());
        }
    
    static void buscar(ArbolGeneral<Integer> a, ListaGenerica<Integer> camino, ListaGenerica<ListaGenerica<Integer>> listadecaminos){
        camino.agregarFinal(a.getDato());
        if (a.getDato()==1){
            listadecaminos.agregarFinal(camino.clonar());
        }
        else{
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            while (!hijos.fin()){
                buscar(hijos.proximo(), camino,listadecaminos);
                camino.eliminarEn(camino.tamanio());
            } 
        }
    }
    //no anda
    public static void descifrarCodigo(ArbolBinario<Character> a, ListaGenerica<String> listadeSecuencias){
        ListaGenerica<Character> mensaje= new ListaEnlazadaGenerica<Character>();
        descifrar(mensaje,a,listadeSecuencias);
        System.out.println(mensaje.toString());
    }
    public static void descifrar (ListaGenerica<Character> mensaje, ArbolBinario<Character> a, ListaGenerica<String> encriptado){
        while (!encriptado.fin()){
            String aux= encriptado.proximo();
            for (int i=0; 0<=aux.length();i++){
                System.out.println(aux.charAt(i));
                if (a.esHoja()){
                    mensaje.agregarFinal(a.getDato());
                }
                else if (aux.charAt(i)=='0')
                    descifrar(mensaje, a.getHijoIzquierdo(), encriptado);
                else
                    descifrar(mensaje, a.getHijoDerecho(), encriptado);
            }
            }
    }
    public static ArbolBinario<Integer> minEnNivelAB(int n, ArbolBinario<Integer> a){
        ColaGenerica<ArbolBinario<Integer>> cola = new ColaGenerica<ArbolBinario<Integer>>();
        int nivel=0;
        ArbolBinario<Integer> min= new ArbolBinario<Integer>(99999);
        cola.encolar(a);
        cola.encolar(null);
        while (!cola.esVacia()){
            ArbolBinario<Integer> aux = cola.desencolar();
            if (aux !=null){
                System.out.println(aux.getDato());
                if (nivel==n){
                    if (aux.esHoja()){
                        if (aux.getDato()< min.getDato()){
                            min.setDato(aux.getDato());
                        }
                    }
                }
                if (aux.tieneHijoIzquierdo())
                    cola.encolar(aux.getHijoIzquierdo());
                if (aux.tieneHijoDerecho())
                    cola.encolar(aux.getHijoDerecho());
            }
            else if (!cola.esVacia()){
                if (nivel==n){
                    return min;
                }
                nivel++;
                cola.encolar(null);
            }
        }
        return min;
    }
    static void Igualdescendientes (ArbolBinario<Integer> a){
        ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
        if(!a.esVacio()){
            resolver(a,lista);
        }
        System.out.println(lista.toString());
        
    }
    static int resolver(ArbolBinario<Integer> a, ListaGenerica<Integer> lista){
        int hderecha=0;
        int hizquierda=0;
        if (a.tieneHijoIzquierdo()){
            hizquierda++;
            hizquierda= hizquierda + resolver(a.getHijoIzquierdo(), lista);
        }
        if (a.tieneHijoDerecho()){
            hderecha++;
            hderecha= hderecha+ resolver(a.getHijoDerecho(),lista);
        }
        if (hderecha==hizquierda)
            lista.agregarFinal(a.getDato());
        return 0;
    }
    public static void sumadatos(ArbolGeneral<Integer> a){
        ListaGenerica<Integer> lista= new ListaEnlazadaGenerica<Integer>();
        if(!a.esVacio()){
            resolver(lista,a);
            System.out.println(lista.toString());
        }
    }
    public static void resolver(ListaGenerica<Integer> lista, ArbolGeneral<Integer> a){
        if(a.tieneHijos()){
            ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
            hijos.comenzar();
            while(!hijos.fin()){
                resolver(lista,hijos.proximo());
            }
            if(hijos.tamanio()%2==1){
                hijos.comenzar();
                int suma=0;
                while(!hijos.fin()){
                    suma= suma+ hijos.proximo().getDato();
                }
                lista.agregarFinal(suma);
            }
        }
    }
    public static void buscarPPrincesa(ArbolGeneral<Integer> arbol){
        ListaGenerica<Integer> act= new ListaEnlazadaGenerica<Integer>();
        ListaGenerica<Integer> camino= new ListaEnlazadaGenerica<Integer>();
        if (!arbol.esVacio()){
            busqueda(arbol,act,camino);
            System.out.println(camino.toString());
        }
    }
    public static void busqueda(ArbolGeneral<Integer> a, ListaGenerica<Integer> act,ListaGenerica<Integer> camino){
        act.agregarFinal(a.getDato());
        if (a.getDato()==1){
            camino.comenzar();
            while (!camino.fin())
                camino.eliminar(camino.proximo());
            act.comenzar();
            while(!act.fin())
                camino.agregarFinal(act.proximo());
        }
        else
            if (!a.esHoja() && a.getDato()!=3 && camino.esVacia()){
                ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
                while(!hijos.fin()){
                    busqueda(hijos.proximo(),act,camino);
                    act.eliminarEn(act.tamanio());
                }
            }
    }
    public static void sumaImparesmayora(ArbolBinario<Integer> arbol){

        System.out.println(sumaimpares( arbol, 30));
        
    }
    public static int sumaimpares(ArbolBinario<Integer> a, Integer limite){
        int suma=0;
        if(a.tieneHijoDerecho())
            suma= suma+ sumaimpares(a.getHijoDerecho(), limite);
        if (a.tieneHijoIzquierdo())
            suma = suma +sumaimpares(a.getHijoIzquierdo(), limite);
        if(a.getDato()%2==1 && a.getDato()>limite){
            suma= suma+a.getDato();
        }
        return suma;
    
    }
    public static boolean caminonodos(ArbolGeneral<Integer> a, ListaGenerica<Integer> lista){
        lista.agregarFinal(a.getDato());
        if (a.esHoja()){
            return true;
        }
        else{
            ListaGenerica<ArbolGeneral<Integer>> hijos= a.getHijos();
            if (a.getDato()<=hijos.tamanio()){
                caminonodos(hijos.elemento(a.getDato()),lista);
            }
        }
        return false;
    }
}


