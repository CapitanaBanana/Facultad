package ar.edu.info.unlp.ejercicioDemo;

public class Cuerpo3D {
  private double altura;
  private Figura caraBasal;

  public Cuerpo3D(){}
  public Cuerpo3D(double altura, Figura caraBasal){
    this.altura=altura;
    this.caraBasal=caraBasal;
  }
  public double getVolumen(){
    return caraBasal.getArea()*altura;
  }
  public double getSuperficieExterior(){
    return caraBasal.getPerimetro()*altura+2* caraBasal.getArea();
  }

  public double getAltura() {
    return this.altura;
  }

  public void setAltura(double altura) {
    this.altura = altura;
  }

  public Figura getCaraBasal() {
    return this.caraBasal;
  }

  public void setCaraBasal(Figura caraBasal) {
    this.caraBasal = caraBasal;
  }

}
