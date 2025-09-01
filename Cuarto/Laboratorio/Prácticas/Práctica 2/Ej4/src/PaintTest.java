import java.util.Arrays;

public class PaintTest {
  public static void main(String[] args) {
    Paint paint = new Paint();
    paint.init();
    FiguraGeometrica[] paleta = paint.getPaleta();
    Arrays.sort(paleta);
    for (FiguraGeometrica figura : paleta) {
      System.out.println("Área: " + figura.area());
      if (figura instanceof Circulo) {
        Circulo cirulo = (Circulo) figura;
        System.out.println("Radio:" + cirulo.getRadio());
      }
    }
  }
}
