public class Alumno implements Comparable<Alumno>{
    private int legajo;
    private String apellido;
    private String nombre;
    private int dni;

    public Alumno(int legajo, String apellido, String nombre, int dni) {
        this.legajo = legajo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }


    @Override
    public int compareTo(Alumno o) {
        int comp = this.apellido.compareTo(o.apellido);
        if (comp != 0) {
            return comp;
        }
        return this.nombre.compareTo(o.nombre);
    }
}
