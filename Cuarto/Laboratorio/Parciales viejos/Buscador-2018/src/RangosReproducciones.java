public enum RangosReproducciones {
  MUY_POCAS(0, 9), POCAS(10, 99), BASTANTES(100, 499), MUCHAS(500, 1000), MUCHISIMAS(1001, 99999999);

  private double desde;
  private double hasta;

  RangosReproducciones(double desde, double hasta) {
    this.desde = desde;
    this.hasta = hasta;
  }

  public boolean contiene(int v) {
    if (desde <= v && hasta >= v) {
      return true;
    } else {
      return false;
    }
  }

}
