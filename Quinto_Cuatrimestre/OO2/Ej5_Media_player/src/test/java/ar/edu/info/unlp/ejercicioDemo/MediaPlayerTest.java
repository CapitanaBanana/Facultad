package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class MediaPlayerTest {
	
	MediaPlayer m; VideoFile vf; VideoStream vs; Audio a; VideoStreamAdapter vsadapter;
	
	@BeforeEach
	void setUp() throws Exception {
		m = new MediaPlayer();
		vf = new VideoFile();
		vs = new VideoStream();
		vsadapter=new VideoStreamAdapter(vs);

		a= new Audio();
		ArrayList<Media> lista = new ArrayList<>();
		lista.add(vf);
		lista.add(vsadapter);
		lista.add(a);
		

	}
	
    @Test
    public void testMedia() {
			m.Play();
			//me dio paja hacer el test xp
    }
}
