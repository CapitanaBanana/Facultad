package ar.edu.info.unlp.ejercicioDemo;


import java.time.LocalDate;

public class Consumo {
  private LocalDate fecha;
  private double consumoEnergiaActiva;
  private double consumoEnergiaReactiva;

  public Consumo() {
  }

  public Consumo(LocalDate fecha, double consumoEnergiaActiva, double consumoEnergiaReactiva) {
    this.fecha = fecha;
    this.consumoEnergiaActiva = consumoEnergiaActiva;
    this.consumoEnergiaReactiva = consumoEnergiaReactiva;
  }
  public double costoEnBaseA(double precioKWh){
    return this.consumoEnergiaActiva * precioKWh;
  }

  public double factorDePotencia(){
    return this.consumoEnergiaActiva / Math.sqrt(Math.pow(consumoEnergiaReactiva,2) + Math.pow(consumoEnergiaActiva,2));
  }

  public LocalDate getFecha() {
    return this.fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public double getConsumoEnergiaActiva() {
    return this.consumoEnergiaActiva;
  }

  public void setConsumoEnergiaActiva(double consumoEnergiaActiva) {
    this.consumoEnergiaActiva = consumoEnergiaActiva;
  }

  public double getConsumoEnergiaReactiva() {
    return this.consumoEnergiaReactiva;
  }

  public void setConsumoEnergiaReactiva(double consumoEnergiaReactiva) {
    this.consumoEnergiaReactiva = consumoEnergiaReactiva;
  }

}
