public class VacunaGenetica extends Vacuna {
  private int temperaturaMinima;
  private int temperaturaMaxima;

  public VacunaGenetica(String marca, String pais, String enfermedadPrevenida, int cantidadDeDosis,
      int temperaturaMinima, int temperaturaMaxima) {
    super(marca, pais, enfermedadPrevenida, cantidadDeDosis);
    this.temperaturaMinima = temperaturaMinima;
    this.temperaturaMaxima = temperaturaMaxima;
  }

  public int getTemperaturaMinima() {
    return temperaturaMinima;
  }

  public int getTemperaturaMaxima() {
    return temperaturaMaxima;
  }

}
