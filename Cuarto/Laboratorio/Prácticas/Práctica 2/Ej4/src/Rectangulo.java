public class Rectangulo extends FiguraGeometrica {
  private static final long serialVersionUID = 1L;
  private int alto;
  private int ancho;

  Rectangulo(int alto, int ancho, String color) {
    super(color);
    this.alto = alto;
    this.ancho = ancho;
  }

  void dibujar() {
    System.out.println("Dibujando un rectángulo de color " + getColor());
  }

  int area() {
    return alto * ancho;
  }
}
