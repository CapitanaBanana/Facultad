import java.util.*;

public class TestMotor {
    public static void main(String[] args) {
        Diccionario d = new Diccionario();

        // Agregá algunas palabras
        d.tokenizar("glucosa");
        d.tokenizar("glucemia");
        d.tokenizar("glicerina");
        d.tokenizar("fructosa");

        Motor m = new Motor();

        Collection<String> similares = m.buscarSimilares(d.getMap(), "glucaso");

        System.out.println("Palabras similares a 'glucaso':");
        for (String s : similares) {
            System.out.println(" - " + s);
        }
    }
}
