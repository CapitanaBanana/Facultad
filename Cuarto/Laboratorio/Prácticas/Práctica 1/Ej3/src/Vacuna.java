public class Vacuna {
    String marca;
    String pais;
    String enfermedadPrevenida;
    int cantidadDeDosis;


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEnfermedadPrevenida() {
        return enfermedadPrevenida;
    }

    public void setEnfermedadPrevenida(String enfermedadPrevenida) {
        this.enfermedadPrevenida = enfermedadPrevenida;
    }

    public void setCantidadDeDosis(int cantidadDeDosis) {
        this.cantidadDeDosis = cantidadDeDosis;
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append(this.marca);
        string.append(this.pais);
        string.append(this.enfermedadPrevenida);
        string.append(this.cantidadDeDosis);
        return string.toString();

    }

    public Vacuna(String marca, String pais, String enfermedadPrevenida, int cantidadDeDosis) {
        this.marca = marca;
        this.pais = pais;
        this.enfermedadPrevenida = enfermedadPrevenida;
        this.cantidadDeDosis = cantidadDeDosis;
    }
}
