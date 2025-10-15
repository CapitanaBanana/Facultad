
public class MateriaTest {
    public static void main(String[] args) {
        Materia m= new Materia("Matematica");
        m.agregarPersona(new Alumno(5, "e", "e", 5));
        m.agregarPersona(new Empleado(12,"aa","aa"));
        m.agregarPersona(new Alumno(6, "f", "f", 6));
        m.agregarPersona(new Alumno(7, "g", "g", 7));
        m.agregarPersona(new Alumno(8, "h", "h", 8));
        m.agregarPersona(new Alumno(9, "i", "i", 9));
        m.agregarPersona(new Alumno(10, "j", "j", 10));
        m.agregarPersona(new Alumno(1, "a", "a", 1));
        m.agregarPersona(new Empleado(11,"z","z"));
        m.agregarPersona(new Alumno(2, "b", "b", 2));
        m.agregarPersona(new Alumno(3, "c", "c", 3));
        m.agregarPersona(new Alumno(4, "d", "d", 4));


        for(Persona a: m.getPersonas()){
            System.out.println(a.toString());
        }
    }

}