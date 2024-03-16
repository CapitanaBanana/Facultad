package practica;
import practica.*;

public class pruebaParcial1 {
    public static void main(String[] args) {
        ArbolGeneral<Character> a = new ArbolGeneral<Character>('a');
        ArbolGeneral<Character> b = new ArbolGeneral<Character>('b');
        ArbolGeneral<Character> c = new ArbolGeneral<Character>('c');
        ArbolGeneral<Character> d = new ArbolGeneral<Character>('d');
        ArbolGeneral<Character> e = new ArbolGeneral<Character>('e');
        ArbolGeneral<Character> f = new ArbolGeneral<Character>('f');
        ArbolGeneral<Character> g = new ArbolGeneral<Character>('g');
        ArbolGeneral<Character> h = new ArbolGeneral<Character>('h');
        ArbolGeneral<Character> i = new ArbolGeneral<Character>('i');
        a.agregarHijo(b);
        a.agregarHijo(c);
        a.agregarHijo(d);
        b.agregarHijo(e);

        c.agregarHijo(f);
        c.agregarHijo(g);

        f.agregarHijo(h);
        f.agregarHijo(i);
        parcial1 prueba= new parcial1();
        prueba.caminosPares(a);
    }
}
