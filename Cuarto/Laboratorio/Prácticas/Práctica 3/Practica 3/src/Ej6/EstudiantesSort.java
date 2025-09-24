package Ej6;

import java.util.Arrays;
import java.util.Comparator;

public class EstudiantesSort {
  public static void print(Estudiante[] est) {
    for (Estudiante e : est) {
      System.out.println(e.toString());
    }
  }

  public static void main(String[] args) {
    Estudiante[] estudiantes = {
        new Estudiante("García", "Ana", 22, "A001", 15),
        new Estudiante("López", "Juan", 25, "A002", 10),
        new Estudiante("Martínez", "Lucía", 21, "A003", 18),
        new Estudiante("Fernández", "Pedro", 23, "A004", 12),
        new Estudiante("Sánchez", "María", 20, "A005", 20),
        new Estudiante("Pérez", "Carlos", 24, "A006", 8)
    };

    print(estudiantes);
    Arrays.sort(estudiantes, new Comparator<Estudiante>() {
      public int compare(Estudiante e1, Estudiante e2) {
        return Integer.compare(e1.getMateriasAprobadas(), e2.getMateriasAprobadas());
      }
    });
    print(estudiantes);
    Arrays.sort(estudiantes, new Comparator<Estudiante>() {
      @Override
      public int compare(Estudiante o1, Estudiante o2) {
        return Integer.compare(o2.getEdad(), o1.getEdad());
      }
    });
    print(estudiantes);
    Arrays.sort(estudiantes, new Comparator<Estudiante>() {
      @Override
      public int compare(Estudiante o1, Estudiante o2) {
        return o1.getLegajo().compareTo(o2.getLegajo());
      }
    });
    print(estudiantes);
    Arrays.sort(estudiantes, new Comparator<Estudiante>() {
      @Override
      public int compare(Estudiante o1, Estudiante o2) {
        return o1.getNombreyAprellido().compareTo(o2.getNombreyAprellido());
      }
    });
    print(estudiantes);
  }

}
