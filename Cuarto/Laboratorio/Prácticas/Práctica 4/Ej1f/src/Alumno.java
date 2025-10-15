public class Alumno extends Persona{
    private int legajo;

    public Alumno(int legajo, String apellido, String nombre, int dni) {
        super(apellido,nombre,dni);
        this.legajo = legajo;
    }

}
