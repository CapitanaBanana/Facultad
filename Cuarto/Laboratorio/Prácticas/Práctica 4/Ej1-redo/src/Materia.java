import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Materia {
    //private List<Alumno> alumnos = new ArrayList(); //esto se considera genérico, idk para mi es bien explicito.
    private Set<Alumno> alumnos = new TreeSet<>();
    private String nombre;

    public Materia(String nombre) {
        this.nombre = nombre;
    }
    public void agregarAlumno(Alumno a){
        alumnos.add(a);
    }
    public Set<Alumno> getAlumnos(){
        return this.alumnos;
    }

}
