import java.util.*;

public class TestFacultad {
    public static void main(String[] args) {
        List<Alumno> lista = new ArrayList<>();
        lista.add(new Alumno(1, "Pedro", "Gomez", 22, "Laboratorio de Software", 9));
        lista.add(new Alumno(2, "Pablo", "Diaz", 21, "Matemática", 7));
        lista.add(new Alumno(3, "Lucia", "Perez", 23, "Laboratorio de Software", 8));
        lista.add(new Alumno(4, "Patricio", "Sosa", 20, "Programación", 10));

        Facultad f = new Facultad();
        // Inyectar la lista (agregá un setter o constructor en Facultad)
         f.setAlumnos(lista);

        // 1. Mejor nota
        System.out.println("Mejor alumno: " + f.obtenerAMejorNota());

        // 2. Dos primeros
        f.imprimirAlumnos();

        // 3. Curso de Laboratorio
        System.out.println("Alumno de Laboratorio: " + f.getLaboratorio());

        // 4. Nombres con P y <7 letras
        System.out.println("Nombres con P y <7 letras: " + f.nombres());

        // 5. Ordenar
        f.ordenarPorNota2();
        System.out.println("Ordenados por nota:");
        f.getAlumnos().forEach(System.out::println);
    }
}
