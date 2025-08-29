public class PaintTest {
  public static void main(String[] args) {
    Paint paint = new Paint();
    paint.init();
    FiguraGeometrica[] paleta = paint.getPaleta();
    for (FiguraGeometrica figura : paleta) {
      System.out.println("Área: " + figura.area());
      if (figura instanceof Circulo) {
        System.out.println("Es un círculo");
      } else if (figura instanceof Rectangulo) {
        System.out.println("Es un rectángulo");
      }
    }
  }
}
