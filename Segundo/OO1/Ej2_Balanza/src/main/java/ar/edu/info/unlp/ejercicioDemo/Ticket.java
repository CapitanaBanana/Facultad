package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;

public class Ticket {
	private LocalDate fecha;
	private int cantProd;
	private double pesoTotal;
	private double precioTotal;
	
	public Ticket(int cantProd, double pesoTotal, double precioTotal) {
		this.cantProd=cantProd;
		this.pesoTotal=pesoTotal;
		this.precioTotal=precioTotal;
		this.fecha=LocalDate.now();
	}
	
	public double impuesto() {
		return this.precioTotal*0.21;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Object getCantidadDeProductos() {
		return cantProd;
	}
}
