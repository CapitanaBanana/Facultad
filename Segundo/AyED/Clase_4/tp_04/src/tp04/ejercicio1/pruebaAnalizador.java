package tp04.ejercicio1;

import java.io.Console;

public class pruebaAnalizador {
    public static void main(String[] args) {
        ArbolGeneral<AreaEmpresa> arbol = new ArbolGeneral<>(new AreaEmpresa ("m",14));
        ArbolGeneral<AreaEmpresa> n1 = new ArbolGeneral<>(new AreaEmpresa("l", 10));
        n1.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("g",9)));
        n1.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("h",12)));
        n1.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("i",19)));
        arbol.agregarHijo(n1);
        ArbolGeneral<AreaEmpresa> n2 = new ArbolGeneral<>(new AreaEmpresa("k",25));
        n2.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("d",6)));
        n2.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("e",10)));
        n2.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("f",18)));
        arbol.agregarHijo(n2);
        ArbolGeneral<AreaEmpresa> n3 = new ArbolGeneral<>(new AreaEmpresa("j",13));
        n3.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("a",4)));
        n3.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("b",7)));
        n3.agregarHijo(new ArbolGeneral<>(new AreaEmpresa("c",5)));
        arbol.agregarHijo(n3);
        AnalizadorArbol analizador= new AnalizadorArbol();
        System.out.println("El maximo promedio es: "+ analizador.devolverMaximoPromedio(arbol));
    }
}
