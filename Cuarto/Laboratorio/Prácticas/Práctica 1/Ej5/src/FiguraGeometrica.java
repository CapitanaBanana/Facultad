abstract class FiguraGeometrica {
  private String color;

  public FiguraGeometrica(String color) {
    this.color = color;
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
