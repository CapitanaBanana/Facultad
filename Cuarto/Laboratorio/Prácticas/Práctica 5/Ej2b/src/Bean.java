@Archivo(nombre="AchivoMapeado.txt")
public class Bean {
    @AlmacenarAtributo
    private String valor = "Default1";
    @AlmacenarAtributo
    private Integer valor2=20;
    @AlmacenarAtributo
    private Float valor3=30.20f;
    private Float valor4=30.20f;

    @AlmacenarMetodo
    public String getValor() {
        return valor;
    }
    @AlmacenarMetodo
    public void setValor(String valor) {
        this.valor = valor;
    }
    @AlmacenarMetodo
    public Integer getValor2() {
        return valor2;
    }
    @AlmacenarMetodo
    public void setValor2(Integer valor2) {
        this.valor2 = valor2;
    }
    @AlmacenarMetodo
    public Float getValor3() {
        return valor3;
    }
    @AlmacenarMetodo
    public void setValor3(Float valor3) {
        this.valor3 = valor3;
    }
    @AlmacenarMetodo
    public Float getValor4() {
        return valor4;
    }
    @AlmacenarMetodo
    public void setValor4(Float valor4) {
        this.valor4 = valor4;
    }
}
