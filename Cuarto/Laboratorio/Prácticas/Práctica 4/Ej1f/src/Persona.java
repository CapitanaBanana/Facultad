public abstract class Persona implements Comparable<Persona> {
     String apellido;
     String nombre;
     int dni;

    public Persona(String apellido, String nombre, int dni) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }
    @Override
    public int compareTo(Persona o) {
        return apellido.compareTo(o.apellido);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dni=" + dni +
                '}';
    }
}
