package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class ClienteDeCorreo {
	private Carpeta inbox;
	private List<Carpeta> carpetas;

	public ClienteDeCorreo() {
		this.carpetas = new ArrayList<Carpeta>();
		this.inbox = new Carpeta("Inbox");
	}

	public void crearCarpeta(String nombre) {
		Carpeta carpeta = new Carpeta(nombre);
		this.carpetas.add(carpeta);
	}

	public void recibir(Email mail) {
		inbox.recibirCorreo(mail);
	}

	public void mover(Email mail, Carpeta origen, Carpeta destino) {
		origen.borrarCorreo(mail);
		destino.recibirCorreo(mail);
	}

	public int espacioOcupado() {
		return carpetas.stream().mapToInt(carpeta -> carpeta.tamanio()).sum() + inbox.tamanio();
	}
	public Carpeta getCarpeta(String nombre){
		if (nombre=="Inbox"){
			return inbox;
		}
		else
		return carpetas.stream().filter(carpeta-> carpeta.getNombre() == nombre).findFirst().orElse(null);
	}
	public Email buscar(String cadena){
		Email aux= inbox.buscarCorreo(cadena);
		if (aux==null){
			aux= this.carpetas.stream().map(carpeta -> (carpeta.buscarCorreo(cadena))).findFirst().orElse(null);
		}
		return aux;
	}
}
