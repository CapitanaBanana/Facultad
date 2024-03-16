package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class Inversor {

	private String nombre;
	private List<Inversion> inversiones;
	
	public Inversor(String nombre){
		this.nombre=nombre;
		this.inversiones=new ArrayList<Inversion>();
	}
	public String getNombre(){
		return this.nombre;
	}
	public void agregarInversion(Inversion inversion){
		inversiones.add(inversion);
	}
	public double valorActual(){
		double total=0;
		for (Inversion inversion : inversiones) {
			total+=inversion.valorActual();
		}
		return total;
	}
	

}
