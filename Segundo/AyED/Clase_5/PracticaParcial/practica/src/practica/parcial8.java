package practica;

import java.io.Console;

public class parcial8 {
        private ArbolGeneral<Integer> arbol;
        
        public parcial8 (ArbolGeneral<Integer> arbol) {
            this.arbol = arbol;
        }
        
        public ListaGenerica<Integer> resolver(){
            ListaGenerica<Integer> l = new ListaEnlazadaGenerica<Integer>();
            int suma=0;
            postOrden(arbol, l);

            System.out.println(l.toString());
            return l;
        }
        
            private void postOrden (ArbolGeneral<Integer> a, ListaGenerica<Integer> l) {
                Integer suma = 0;
                Boolean ok = false;
                
                if (a.tieneHijos()) {
                    ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
                    while (!hijos.fin()) {
                        ArbolGeneral<Integer> aux = hijos.proximo();
                        if ((hijos.tamanio()%2) == 1) {
                            ok = true;
                            suma+= aux.getDato();
                        }
                        postOrden(aux,l);
                        }
                    }
                if (!a.esHoja()) {
                    if (ok) {
                        l.agregarFinal(suma);
                    }
                }
                
            }
        }
