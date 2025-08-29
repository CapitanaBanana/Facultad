public class Paint {
  private FiguraGeometrica[] paleta;

  Paint() {
  }

  void setPaleta(FiguraGeometrica[] paleta) {
    this.paleta = paleta;
  }

  FiguraGeometrica[] getPaleta() {
    return paleta;
  }

  void init() {
    paleta = new FiguraGeometrica[4];
    paleta[0] = new Circulo(2, "azul");
    paleta[1] = new Circulo(3, "amarillo");
    paleta[2] = new Rectangulo(2, 3, "verde");
    paleta[3] = new Rectangulo(4, 10, "rojo");
  }
}
