//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    HashSetAgregados<String> conjunto = new HashSetAgregados<>();
    conjunto.add("Hola");
    conjunto.add("Mundo");
    System.out.println("Cantidad agregados: " + conjunto.getCantidadAgregados());
    conjunto.add("Hola");
    System.out.println("Cantidad agregados: " + conjunto.getCantidadAgregados());
    conjunto.addAll(java.util.Arrays.asList("Chau", "Mundo", "!"));
    System.out.println("Cantidad agregados: " + conjunto.getCantidadAgregados());

  }
}