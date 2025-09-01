import java.io.*;

public class SerializacionEjemplo {

  public static void main(String[] args) {
    // Crear objetos para serializar
    Paint paint = new Paint();
    paint.init();

    Circulo circulo = new Circulo(5, "azul");
    Rectangulo rectangulo = new Rectangulo(10, 20, "rojo");

    // Demostrar serialización
    serializarObjetos(paint, circulo, rectangulo);

    // Demostrar deserialización
    deserializarObjetos();
  }

  public static void serializarObjetos(Paint paint, Circulo circulo, Rectangulo rectangulo) {
    try {
      // Serializar objeto Paint
      FileOutputStream fileOut = new FileOutputStream("paint.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(paint);
      out.close();
      fileOut.close();
      System.out.println("Objeto Paint serializado en paint.ser");

      // Serializar Circulo
      fileOut = new FileOutputStream("circulo.ser");
      out = new ObjectOutputStream(fileOut);
      out.writeObject(circulo);
      out.close();
      fileOut.close();
      System.out.println("Objeto Circulo serializado en circulo.ser");

      // Serializar Rectangulo
      fileOut = new FileOutputStream("rectangulo.ser");
      out = new ObjectOutputStream(fileOut);
      out.writeObject(rectangulo);
      out.close();
      fileOut.close();
      System.out.println("Objeto Rectangulo serializado en rectangulo.ser");

    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  public static void deserializarObjetos() {
    try {
      // Deserializar Paint
      FileInputStream fileIn = new FileInputStream("paint.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Paint paint = (Paint) in.readObject();
      in.close();
      fileIn.close();

      System.out.println("\n--- Objeto Paint deserializado ---");
      FiguraGeometrica[] paleta = paint.getPaleta();
      for (int i = 0; i < paleta.length; i++) {
        System.out.println("Figura " + i + ":");
        paleta[i].dibujar();
        System.out.println("Área: " + paleta[i].area());
      }

      // Deserializar Circulo
      fileIn = new FileInputStream("circulo.ser");
      in = new ObjectInputStream(fileIn);
      Circulo circulo = (Circulo) in.readObject();
      in.close();
      fileIn.close();

      System.out.println("\n--- Objeto Circulo deserializado ---");
      circulo.dibujar();
      System.out.println("Radio: " + circulo.getRadio());
      System.out.println("Área: " + circulo.area());

      // Deserializar Rectangulo
      fileIn = new FileInputStream("rectangulo.ser");
      in = new ObjectInputStream(fileIn);
      Rectangulo rectangulo = (Rectangulo) in.readObject();
      in.close();
      fileIn.close();

      System.out.println("\n--- Objeto Rectangulo deserializado ---");
      rectangulo.dibujar();
      System.out.println("Área: " + rectangulo.area());

    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("Clase no encontrada");
      c.printStackTrace();
      return;
    }
  }
}
