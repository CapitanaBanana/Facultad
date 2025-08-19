package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PlazoFijo implements	Inversion{
  private LocalDate fechaDeConstuticion;
  private double montoDepositado;
  private double porcentajeDeInteres;

  public PlazoFijo(LocalDate fechaDeConstitucion, double montoDepositado, double porcentajeDeInteres){
    this.fechaDeConstuticion= fechaDeConstitucion;
    this.montoDepositado = montoDepositado;
    this.porcentajeDeInteres = porcentajeDeInteres;
  }

  public LocalDate getFechaDeConstuticion() {
    return this.fechaDeConstuticion;
  }

  public double getPorcentajeDeInteres() {
    return this.porcentajeDeInteres;
  }

  public double getMontoDepositado() {
    return this.montoDepositado;
  }
  public double valorActual(){
    long diferenciaDias= ChronoUnit.DAYS.between(fechaDeConstuticion, LocalDate.now());
    return this.montoDepositado + this.montoDepositado * (porcentajeDeInteres/100) * diferenciaDias;
  }
}
