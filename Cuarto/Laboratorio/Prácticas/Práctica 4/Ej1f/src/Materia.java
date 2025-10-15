
import java.util.SortedSet;
import java.util.TreeSet;

public class Materia {
    private String nombre;
    private SortedSet<Persona> nomina = new TreeSet<>();

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public void agregarPersona(Persona p){
        nomina.add(p);
    }

    public SortedSet<Persona> getPersonas() {
        return nomina;
    }
}
