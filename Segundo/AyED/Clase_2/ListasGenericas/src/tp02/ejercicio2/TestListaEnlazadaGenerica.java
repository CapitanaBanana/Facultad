package tp02.ejercicio2;

public class TestListaEnlazadaGenerica {
    public static void main(String[] args) {
        Estudiante e1=new Estudiante("Josefina", "Martinez", 0, "josema", "calle 2");
        Estudiante e2=new Estudiante("ignacio", "melo", 0, "Igna", "calle 65");
        Estudiante e3=new Estudiante("fabrizio", "Perri", 0, "fabri", "calle 7");
        Estudiante e4=new Estudiante("manuel", "barreto", 0, "sherlock", "arana");
        ListaGenerica<Estudiante> lista= new ListaEnlazadaGenerica<Estudiante>();
        Estudiante[] es= {e1,e2,e3,e4};
        lista.agregar(es);
        lista.comenzar();
        for (int i = 0; i < lista.tamanio(); i++) {
            System.out.println(lista.proximo().tusDatos());
        }
    }
}
