package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class PlazoFijoTest {
	
	PlazoFijo a;
	
	@BeforeEach
	void setUp() throws Exception {
		LocalDate fecha= LocalDate.of(2023,9,8);
		a= new PlazoFijo(fecha, 1000, 1);
		
	}
	
	@Test
    public void testConstructor() {
        assertEquals(1000, a.getMontoDepositado());
				assertEquals(1, a.getPorcentajeDeInteres());
    }

    @Test
    public void testValorActual() {
        assertEquals(1300, a.valorActual());
    }
}
