import java.io.File;

public class MiArchivo extends File {
  public MiArchivo(String path) {
    super(path);
    System.out.println("Mi Archivo instanciado");
  }
}