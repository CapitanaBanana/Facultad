package ar.edu.info.unlp.ejercicioDemo;

public class Item {
  private String detalle;
  private int cantidad;
  private double costoUnitario;

  public Item(String detalle, int cantidad, double costoUnitario){
    this.detalle=detalle;
    this.cantidad=cantidad;
    this.costoUnitario=costoUnitario;
  }
  public double costo(){
    return this.cantidad*this.costoUnitario;
  }

  public String getDetalle() {
    return this.detalle;
  }

  public void setDetalle(String detalle) {
    this.detalle = detalle;
  }

  public int getCantidad() {
    return this.cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public double getCostoUnitario() {
    return this.costoUnitario;
  }

  public void setCostoUnitario(double costoUnitario) {
    this.costoUnitario = costoUnitario;
  }

}
