public enum RangosValoracion {
  MUY_MALA(0.0, 2.0), MALA(2.0, 4.0), BUENA(4.0, 7.0), EXCELENTE(7.0, 10);

  private double desde;
  private double hasta;

  RangosValoracion(double desde, double hasta) {
    this.desde = desde;
    this.hasta = hasta;
  }

  public boolean contiene(double p) {
    if (desde <= p && hasta >= p) {
      return true;
    } else {
      return false;
    }
  }

}
