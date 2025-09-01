public class VacunaSubunidadAntigenica extends Vacuna {
  private String cantidadAntigenicos;
  private String proceso;

  public VacunaSubunidadAntigenica(String marca, String pais, String enfermedadPrevenida, int cantidadDeDosis,
      String cantidadAntigenicos, String proceso) {
    super(marca, pais, enfermedadPrevenida, cantidadDeDosis);
    this.cantidadAntigenicos = cantidadAntigenicos;
    this.proceso = proceso;
  }

  public String getCantidadAntigenicos() {
    return cantidadAntigenicos;
  }

  public String getProceso() {
    return proceso;
  }

}
