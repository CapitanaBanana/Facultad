package ar.edu.info.unlp.ejercicioDemo;

public class Cilindro extends Pieza{
  private int radio;
  private int altura;
  public Cilindro(String color, String material, int radio, int altura){
    super(material,color);
    this.radio=radio;
    this.altura=altura;
  }
  public double volumen(){
    return getAreaCara() * this.altura;
  }
  private double getAreaCara(){
    return Math.pow(this.radio,2) * Math.PI;
  }
  public double superficie(){
    return getAreaCara() * 2 + 2 * Math.PI * this.radio * this.altura;
  }
}
