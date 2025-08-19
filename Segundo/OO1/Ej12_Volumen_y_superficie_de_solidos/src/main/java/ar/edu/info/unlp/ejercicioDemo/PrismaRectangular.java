package ar.edu.info.unlp.ejercicioDemo;

public class PrismaRectangular extends Pieza{
  private int ladoMayor;
  private int ladoMenor;
  private int altura;

  public PrismaRectangular(String color, String material, int ladoMayor, int ladoMenor, int altura){
    super(material,color);
    this.ladoMayor=ladoMayor;
    this.ladoMenor=ladoMenor;
    this.altura=altura;
  }
  public double volumen(){
    return getAreaCara() * this.altura;
  }
  private double getAreaCara(){
    return this.ladoMayor* this.ladoMenor;
  }
  public double superficie(){
    return 2*(getAreaCara()+this.ladoMayor*this.altura+this.ladoMenor*this.altura);
  }
}
