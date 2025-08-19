package ar.edu.info.unlp.ejercicioDemo;

public class Esfera extends Pieza{
  private int radio;
  public Esfera(String color, String material, int radio){
    super(material,color);
    this.radio=radio;
  }
  public double volumen(){
    return 4/3.0 * Math.PI * Math.pow(this.radio,3);
  }
  public double superficie(){
    return 4 * Math.PI * Math.pow(this.radio,2);
  }
}

