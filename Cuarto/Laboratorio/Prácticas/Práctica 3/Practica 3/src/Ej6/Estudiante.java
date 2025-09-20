package Ej6;

public class Estudiante {
    private String apellido;
    private String nombre;
    private int edad;
    private String legajo;
    private int materiasAprobadas;

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getLegajo() {
        return legajo;
    }

    public int getMateriasAprobadas() {
        return materiasAprobadas;
    }
    public String getNombreyAprellido(){
        return nombre+" "+ apellido;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", legajo='" + legajo + '\'' +
                ", materiasAprobadas=" + materiasAprobadas +
                '}';
    }

    public Estudiante(String apellido, String nombre, int edad, String legajo, int materiasAprobadas) {
        this.nombre=nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.legajo = legajo;
        this.materiasAprobadas = materiasAprobadas;
    }
}
