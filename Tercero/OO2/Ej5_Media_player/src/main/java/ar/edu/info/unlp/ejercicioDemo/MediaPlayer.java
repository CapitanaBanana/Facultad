package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class MediaPlayer{
	private ArrayList<Media> media;

	public MediaPlayer(){
		media= new ArrayList<Media>();
	}

	public void Play(){
		for (Media m : media) {
			m.Play();
		}
	}
}
