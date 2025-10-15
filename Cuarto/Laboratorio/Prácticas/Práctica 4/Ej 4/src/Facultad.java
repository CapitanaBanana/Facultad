import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Facultad {
    private List<Alumno> alumnos;

    public Alumno obtenerAMejorNota(){
        return  alumnos.stream().max(Comparator.comparingInt(Alumno::getNota)).orElse(null);
    }
    public void imprimirAlumnos(){
        System.out.println(alumnos.getFirst());
        System.out.println(alumnos.getLast());
    }
    public Alumno getLaboratorio(){
        return alumnos.stream()
                .filter(a -> a.getMateria().equals("Laboratorio de Software"))
                .findFirst()
                .orElse(null);
    }
    public List<Alumno> nombres(){
        return alumnos.stream().filter( alumno -> alumno.getNombres().startsWith("P") && alumno.getNombres().length() <7).toList();
    }
    public void ordenarPorNota(){
        Collections.sort(alumnos, (a1, a2) -> Integer.compare(a2.getNota(), a1.getNota()));
    }
    //usando referencia a metodo, reempolaza a -> a.getNota().
    public void ordenarPorNota2() {
        alumnos.sort(Comparator.comparingInt(Alumno::getNota));
    }

}
