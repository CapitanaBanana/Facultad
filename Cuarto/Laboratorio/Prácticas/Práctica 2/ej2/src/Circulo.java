public class Circulo {
  private int radio;
  Circulo(int radio){
    this.radio = radio;
  }
  Circulo(){

  }
  void dibujar(){

  }
  int area(){
    return (int) (Math.PI * radio * radio);
  }
  int getRadio(){
    return radio;
  }
  int setRadio(int radio){
    return this.radio = radio;
  }
}
