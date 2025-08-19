package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class ReporteDeConstruccion {

	private List<Pieza> piezas;
	
	
	public ReporteDeConstruccion(){
		this.piezas=new ArrayList<Pieza>();
	}

	public void agregarPieza(Pieza pieza){
		this.piezas.add(pieza);
	}

	public double getVolumenDeMaterial(String material){
		double total=0;
		for (Pieza pieza : piezas) {
			if (pieza.getMaterial()==material){
				total+=pieza.volumen();
			}
		}
		return total;
	}
	public double getSuperficieDeColor(String color){
		double total=0;
		for (Pieza pieza : piezas) {
			if (pieza.getColor()==color){
				total+=pieza.superficie();
			}
		}
		return total;
	}

}
