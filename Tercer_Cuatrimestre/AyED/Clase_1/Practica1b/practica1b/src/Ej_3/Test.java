package Ej_3;

public class Test {
    public static void main(String[] args) {
        Estudiante [] estudiantes= new Estudiante[2];
        Profesor [] profesores = new Profesor[3];
        profesores[0]= new Profesor("pepe", "lopez", 0, "pepelopez@gmail", "unlp");
        profesores[1]= new Profesor("facu", "profe", 2, "facuupiola@gmail", "unr");
        profesores[2]= new Profesor("gena", "camele", 5, "genacamele@gmail", "utn");
        estudiantes[0]= new Estudiante("ignacio", "melo", 0, "meloignacionicolas@gmail", "la plata");
        estudiantes[1]= new Estudiante("josefina", "martinez", 5, "jose@gmail", "v elisa");

        System.out.println("Profesores");
        for (int i=0; i<=2; i++){
            System.out.println(profesores[i].tusDatos());
        }
        System.out.println("Alumnos");
        for (int i=0; i<=1; i++){
            System.out.println(estudiantes[i].tusDatos());
        }
    }
}
