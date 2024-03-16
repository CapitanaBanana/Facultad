package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class AdjuntoTest {
	
	Adjunto a;
	
	@BeforeEach
	void setUp() throws Exception {
		a= new Adjunto("AdjuntoPiola");
	}
	
    @Test
    public void testNombreCompleto() {
        assertEquals(12, a.getTamanio());
    }
}
