package ar.edu.info.unlp.ejercicioDemo;

public class Cuadrado implements Figura{
  private double lado;
  public double getArea(){
    return this.lado*this.lado;
  }
  public double getPerimetro(){
    return this.lado*4;
  }

  public double getLado() {
    return this.lado;
  }

  public void setLado(double lado) {
    this.lado = lado;
  }

}
