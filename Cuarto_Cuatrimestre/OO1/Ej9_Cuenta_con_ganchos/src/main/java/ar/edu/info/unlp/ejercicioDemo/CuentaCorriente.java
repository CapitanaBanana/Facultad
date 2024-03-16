package ar.edu.info.unlp.ejercicioDemo;

public class CuentaCorriente extends Cuenta{
  private double descubierto;

  public CuentaCorriente(){
    this.descubierto=0;
  }
  public double getDescubierto(){
    return this.descubierto;
  }
  public void setDescubierto(double descubierto){
    this.descubierto=descubierto;
  }
  @Override
  public boolean puedeExtraer(double monto){
    if (this.getSaldo() + descubierto >= monto)
      return true;
    return false;
  }
  
}
