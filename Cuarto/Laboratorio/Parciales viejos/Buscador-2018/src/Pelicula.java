import java.util.ArrayList;

public class Pelicula {
  private ArrayList<Genero> generos = new ArrayList<>();
  private Edad edad;
  private double puntaje;
  private int vistas;

  public ArrayList<Genero> getGenero() {
    return generos;
  }

  public void setGenero(Genero genero) {
    this.generos.add(genero);
  }

  public Edad getEdad() {
    return this.edad;
  }

  public void setEdad(Edad edad) {
    this.edad = edad;
  }

  public double getPuntaje() {
    return this.puntaje;
  }

  public void setPuntaje(double puntaje) {
    this.puntaje = puntaje;
  }

  public int getVistas() {
    return this.vistas;
  }

  public void setVistas(int vistas) {
    this.vistas = vistas;
  }

}
