public class Paint {
  private FiguraGeometrica[] paleta;

  Paint(){

  }
  void setPaleta(FiguraGeometrica[] paleta){
    this.paleta = paleta;
  }
  FiguraGeometrica[] getPaleta(){
    return paleta;
  }
  void init(){}
}
