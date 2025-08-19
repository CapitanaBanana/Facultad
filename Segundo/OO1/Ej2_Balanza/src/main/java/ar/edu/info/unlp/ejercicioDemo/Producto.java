package ar.edu.info.unlp.ejercicioDemo;

public class Producto {
	double peso;
	double precioPorKilo;
	String desc;
	
	public Producto() {
		this.peso=0;
		this.precioPorKilo=0;
		this.desc="Sin Descripcion";
	}
	public double getPrecio() {
		return precioPorKilo*peso;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getPrecioPorKilo() {
		return precioPorKilo;
	}
	public void setPrecioPorKilo(double precioPorKilo) {
		this.precioPorKilo = precioPorKilo;
	}
	public String getDescripcion() {
		return desc;
	}
	public void setDescripcion(String string) {
		this.desc = string;
		
	}
}
