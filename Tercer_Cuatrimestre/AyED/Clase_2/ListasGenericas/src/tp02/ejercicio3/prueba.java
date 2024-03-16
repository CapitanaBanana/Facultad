package tp02.ejercicio3;

import java.io.Console;

public class prueba {
    public static void main(String[] args) {
        ColaGenerica<String> cola=new ColaGenerica<String>();
        System.out.println(cola.esVacia());
        cola.encolar("fabri");
        cola.encolar("capi");
        cola.encolar("igna");
        System.out.println(cola.esVacia());
        System.out.println(cola.desencolar());
        System.out.println(cola.desencolar());
        System.out.println(cola.desencolar());
        System.out.println(cola.esVacia());
        System.out.println();
        PilaGenerica<String> pila= new PilaGenerica<String>();
        System.out.println(pila.esVacia());
        pila.apilar("fabri");
        pila.apilar("capi");
        pila.apilar("igna");
        System.out.println(pila.esVacia());
        System.out.println(pila.desapilar());
        System.out.println(pila.desapilar());
        System.out.println(pila.desapilar());
        System.out.println(pila.esVacia());
    }
}
