import java.util.Comparator;

public enum Edad {
  ATP(0),
  MAS_13(13),
  MAS_16(16),
  MAS_18(18);

  private int edad;

  Edad(int edad) {
    this.edad = edad;
  }

  public int minEdad() {
    return this.edad;
  }

}
