public class Circulo extends FiguraGeometrica {
  private int radio;

  Circulo(int radio, String color) {
    super(color);
    this.radio = radio;
  }

  void dibujar() {
    System.out.println("Dibujando un círculo de color " + getColor());
  }

  int area() {
    return (int) (Math.PI * radio * radio);
  }

  int getRadio() {
    return radio;
  }

  int setRadio(int radio) {
    return this.radio = radio;
  }
}
