package ar.edu.info.unlp.ejercicioDemo;

public class Adjunto {
  private String nombre;
  private int tamanio;

  public Adjunto(String nombre) {
    this.nombre = nombre;
    this.tamanio = nombre.length();
  }

  public Adjunto() {
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
    this.tamanio = nombre.length();
  }

  public String getNombre() {
    return this.nombre;
  }

  public int getTamanio() {
    return this.tamanio;
  }
}
