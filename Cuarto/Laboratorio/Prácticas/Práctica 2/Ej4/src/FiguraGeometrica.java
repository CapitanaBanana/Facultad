import java.io.Serializable;

abstract class FiguraGeometrica implements Comparable<FiguraGeometrica>, Serializable {
  private static final long serialVersionUID = 1L;
  private String color;

  public FiguraGeometrica(String color) {
    this.color = color;
  }

  @Override
  public int compareTo(FiguraGeometrica otra) {
    return Integer.compare(this.area(), otra.area());// devuelve < 0 si this es menor que otra, > 0 si es mayor, y 0 si
                                                     // son iguales
  }

  abstract void dibujar();

  abstract int area();

  void setColor(String color) {
    this.color = color;
  }

  String getColor() {
    return color;
  }

}
