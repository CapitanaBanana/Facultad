
public class AlumnoTest {
    public static void main(String[] args) {
        Materia m= new Materia("Matematica");
        m.agregarAlumno(new Alumno(5, "e", "e", 5));
        m.agregarAlumno(new Alumno(6, "f", "f", 6));
        m.agregarAlumno(new Alumno(7, "g", "g", 7));
        m.agregarAlumno(new Alumno(8, "h", "h", 8));
        m.agregarAlumno(new Alumno(9, "i", "i", 9));
        m.agregarAlumno(new Alumno(10, "j", "j", 10));
        m.agregarAlumno(new Alumno(1, "a", "a", 1));
        m.agregarAlumno(new Alumno(2, "b", "b", 2));
        m.agregarAlumno(new Alumno(3, "c", "c", 3));
        m.agregarAlumno(new Alumno(4, "d", "d", 4));


        for(Alumno a: m.getAlumnos()){
            System.out.println(a.toString());
        }
    }

}