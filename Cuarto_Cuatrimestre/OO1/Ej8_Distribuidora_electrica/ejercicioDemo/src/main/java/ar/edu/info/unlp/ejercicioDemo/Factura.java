package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;
import java.util.Objects;

public class Factura {
  private double montoEnergiaActiva;
  private double descuento;
  private LocalDate fecha;
  private Usuario usuario;


  public Factura(double montoEnergiaActiva, double descuento, Usuario usuario) {
    this.montoEnergiaActiva = montoEnergiaActiva;
    this.descuento = descuento;
    this.usuario = usuario;
    this.fecha = LocalDate.now();
  }
  public double montoTotal(){
    return this.montoEnergiaActiva-(this.descuento/ 100)* this.montoEnergiaActiva;
  }

  public double getMontoEnergiaActiva() {
    return this.montoEnergiaActiva;
  }

  public void setMontoEnergiaActiva(double montoEnergiaActiva) {
    this.montoEnergiaActiva = montoEnergiaActiva;
  }

  public double getDescuento() {
    return this.descuento;
  }

  public void setDescuento(double descuento) {
    this.descuento = descuento;
  }

  public LocalDate getFecha() {
    return this.fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }
  
    public Usuario getUsuario() {
    return this.usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

}
