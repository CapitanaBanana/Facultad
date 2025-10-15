import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Materia {
    private String nombre;
    private SortedSet<Alumno> nomina = new TreeSet<>();

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public void agregarAlumno(Alumno a){
        nomina.add(a);
    }

    public SortedSet<Alumno> getAlumnos() {
        return nomina;
    }
}
