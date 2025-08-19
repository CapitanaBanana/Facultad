package ar.edu.info.unlp.ejercicioDemo;

public class CajaDeAhorro extends Cuenta{
  
  
  @Override
  public boolean transferirACuenta(double monto, Cuenta cuentaDestino){
    if (this.puedeExtraer(monto*1.02)){
      extraerSinControlar(monto*0.02);
      return super.transferirACuenta(monto, cuentaDestino);
    }
    return false;
  }

  @Override 
  public void depositar(double monto){
    super.depositar(monto * 0.98);
  }
  @Override
  public boolean extraer(double monto){
    if (super.puedeExtraer(monto*1.02)){
      super.extraerSinControlar(monto*1.02);
      return true;
    }
    return false;
  }

}
