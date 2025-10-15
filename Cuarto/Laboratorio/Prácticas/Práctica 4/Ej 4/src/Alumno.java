public class Alumno {
    private int nro;
    private String nombres;
    private String apellidos;
    private int edad;
    private String materia;
    private int nota;

    public Alumno(int nro, String nombres, String apellidos, int edad, String materia, int nota) {
        this.nro = nro;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.materia = materia;
        this.nota = nota;
    }

    public int getNro() {
        return nro;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getMateria() {
        return materia;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nro=" + nro +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", materia=" + materia +
                ", nota=" + nota +
                '}';
    }
}
