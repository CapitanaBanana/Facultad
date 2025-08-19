package practica;

public class pruebaparcialvideo {
    public static void main(String[] args) {
        ArbolGeneral<Integer> arbol8 = new ArbolGeneral<Integer>(8); 
        ArbolGeneral<Integer> arbol3 = new ArbolGeneral<Integer>(3);
        ArbolGeneral<Integer> arbol5 = new ArbolGeneral<Integer>(5);
        ArbolGeneral<Integer> arbol4 =new ArbolGeneral<Integer>(4);
        ArbolGeneral<Integer> arbol7 = new ArbolGeneral<Integer>(7);
        ArbolGeneral<Integer> arbol6 =new ArbolGeneral<Integer>(6);
        ArbolGeneral<Integer> arbol1 =new ArbolGeneral<Integer>(1);
        ArbolGeneral<Integer> arbol2 = new ArbolGeneral<Integer>(2);
        ArbolGeneral<Integer> arbol9 = new ArbolGeneral<Integer>(9);
    ArbolGeneral<Integer> arbol10 = new ArbolGeneral<Integer>(10);
arbol8.agregarHijo(arbol3);
arbol8.agregarHijo(arbol5);
arbol3.agregarHijo (arbol4);
arbol3.agregarHijo(arbol7);
arbol3.agregarHijo(arbol6);
arbol5.agregarHijo(arbol1);
arbol5.agregarHijo(arbol9);
arbol5.agregarHijo (arbol10);
arbol7.agregarHijo(arbol2);
        System.out.println("El camino a la hoja más lejana es: ");
        ListaGenerica<Integer> l = Parcialvideo.caminoAHojaMasLejana(arbol8);
        System.out.println(l.toString());
    }
}
