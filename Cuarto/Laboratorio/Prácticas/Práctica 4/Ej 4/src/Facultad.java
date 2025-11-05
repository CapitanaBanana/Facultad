import java.util.*;

public class Facultad {
    private List<Alumno> alumnos = new ArrayList<>();

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

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

    //Versión lambda
    public void ordenarPorNota(){
        Collections.sort(alumnos, (a1, a2) -> Integer.compare(a2.getNota(), a1.getNota()));
    }
    //Versión con Comparator.comparingInt()
    //usando referencia a metodo, reempolaza a -> a.getNota().
    //El operador :: se llama operador de referencia a método (method reference).
    //Sirve para referirse directamente a un método existente sin tener que escribir una lambda completa que lo llame.

    public void ordenarPorNota2() {
        alumnos.sort(Comparator.comparingInt(Alumno::getNota));
    }

}
