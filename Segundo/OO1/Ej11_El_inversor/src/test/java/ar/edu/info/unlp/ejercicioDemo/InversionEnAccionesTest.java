package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
  public class InversionEnAccionesTest {
	
	InversionEnAcciones a;
	
	@BeforeEach
	void setUp() throws Exception {
		a= new InversionEnAcciones("google", 5, 10);
		
	}
	
	@Test
    public void testConstructor() {
        assertEquals("google", a.getNombre());
				assertEquals(5, a.getCantidad());
        assertEquals(10, a.getValorUnitario());
    }

    @Test
    public void testValorActual() {
        assertEquals(50, a.valorActual());
    }
}

