public class VacunaPatogenoIntegro extends Vacuna {
  private String nombreVirusPatogenico;

  public VacunaPatogenoIntegro(String marca, String pais, String enfermedadPrevenida, int cantidadDeDosis,
      String nombreVirusPatogenico) {
    super(marca, pais, enfermedadPrevenida, cantidadDeDosis);
    this.nombreVirusPatogenico = nombreVirusPatogenico;
  }

  public String getNombreVirusPatogenico() {
    return nombreVirusPatogenico;
  }

}
