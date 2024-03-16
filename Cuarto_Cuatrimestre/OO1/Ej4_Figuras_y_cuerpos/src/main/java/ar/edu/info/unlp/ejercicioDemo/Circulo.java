package ar.edu.info.unlp.ejercicioDemo;
import java.lang.Math;
public class Circulo implements Figura {

  private double radio;
  private double diametro;

  
  public double getArea(){
    return Math.PI*this.radio*this.radio;
  }
  public double getPerimetro(){
    return 2*Math.PI*this.radio;
  }

  public double getRadio() {
    return this.radio;
  }

  public void setRadio(double radio) {
    this.radio = radio;
    this.diametro = radio*2;
  }

  public double getDiametro() {
    return this.diametro;
  }

  public void setDiametro(double diametro) {
    this.diametro = diametro;
  }

}
