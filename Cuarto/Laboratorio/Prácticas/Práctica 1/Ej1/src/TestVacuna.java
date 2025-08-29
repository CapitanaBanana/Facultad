
import java.util.ArrayList;

public class TestVacuna {
    public static void main(String[] args) {
        ArrayList<Vacuna> array= new ArrayList<Vacuna>();
        array.add(new Vacuna("MARCA1", "PAIS1", "ENFERMEDAD1", 1));
        array.add(new Vacuna("MARCA2", "PAIS2", "ENFERMEDAD2", 2));
        array.add(new Vacuna("MARCA3", "PAIS3", "ENFERMEDAD3", 3));
        array.add(new Vacuna("MARCA4", "PAIS4", "ENFERMEDAD4", 4));
        array.add(new Vacuna("MARCA5", "PAIS5", "ENFERMEDAD5", 5));

        for (int i=0;i<5; i++){
            System.out.println(array.get(i).toString());
        }
        Vacuna vacuna1= new Vacuna("MARCA5", "PAIS5", "ENFERMEDAD5", 5);
        Vacuna vacuna2 = new Vacuna("MARCA5", "PAIS5", "ENFERMEDAD5", 5);
        System.out.println(vacuna2==vacuna1);
        System.out.println(vacuna2.equals(vacuna1));
    }
}
