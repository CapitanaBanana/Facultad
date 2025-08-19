package ar.edu.info.unlp.ejercicioDemo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Balanza {

	private int CantidadDeProductos;
	private double PrecioTotal;
	private double PesoTotal;
	private List<Producto> productos;

	public Balanza() {
		this.CantidadDeProductos=0;
		this.PrecioTotal=0;
		this.PesoTotal=0;
		this.productos= new ArrayList<>();
	}
	public void ponerEnCero() {
		this.CantidadDeProductos=0;
		this.PrecioTotal=0;
		this.PesoTotal=0;
	}
	public Ticket emitirTicket() {
		Ticket t = new Ticket(this.CantidadDeProductos, this.PesoTotal, this.PrecioTotal);
		return t;
	}
	public void agregarProducto(Producto prod) {
		this.CantidadDeProductos++;
		this.PesoTotal+=prod.peso;
		this.PrecioTotal+=prod.precioPorKilo*prod.peso;
		this.productos.add(prod); 
	}
	public int getCantidadDeProductos() {
		return CantidadDeProductos;
	}
	public void setCantidadDeProductos(int cantidadDeProductos) {
		CantidadDeProductos = cantidadDeProductos;
	}
	public double getPrecioTotal() {
		return PrecioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		PrecioTotal = precioTotal;
	}
	public double getPesoTotal() {
		return PesoTotal;
	}
	public void setPesoTotal(double pesoTotal) {
		PesoTotal = pesoTotal;
	}
}
