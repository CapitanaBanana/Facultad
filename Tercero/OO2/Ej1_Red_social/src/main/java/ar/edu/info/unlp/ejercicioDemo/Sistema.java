package ar.edu.info.unlp.ejercicioDemo;

import java.util.List;
import java.util.ArrayList;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class Sistema {

	private ArrayList<Usuario> usuarios;

	public Sistema() {
		usuarios = new ArrayList<Usuario>();
	}

	public void registrarUsuario(Usuario usuario) {
		if (!existeUsuario(usuario.getScreenName()))
			this.usuarios.add(usuario);
	}

	private boolean existeUsuario(String nombre) {
		return (usuarios.stream().anyMatch(usuario -> usuario.getScreenName().equals(nombre)));
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void eliminarUsuario(String screenName) {
		Usuario usuario = this.usuarios.stream().filter(u -> u.getScreenName().equals(screenName)).findAny().orElse(null);
		if (usuario != null) {
			usuario.borrarTweets();
			usuarios.remove(usuario);
			for (Usuario u : usuarios) {
				u.borrarReTweetsDe(screenName);
			}
		}
	}
}
