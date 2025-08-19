package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class Presupuesto{
	private LocalDate fecha;
  private List<Item> items;
  private String cliente;

  public Presupuesto(String cliente){
    this.fecha=LocalDate.now();
    this.cliente=cliente;
    items= new ArrayList<>();
  }

  public LocalDate getFecha() {
    return this.fecha;
  }

  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }

  public List<Item> getItems() {
    return this.items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public String getCliente() {
    return this.cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public void agregarItem(Item item){
    items.add(item);
  }

  public Double calcularTotal(){
    Double suma=0.0;
    for (Item item : this.items) {
      suma= suma+item.costo();
    }
    return suma;
  }
}
