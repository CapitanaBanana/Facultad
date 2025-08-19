package ar.edu.info.unlp.ejercicioDemo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InversorTest {
  Inversor I;
  PlazoFijo a;
	InversionEnAcciones b;

	@BeforeEach
	void setUp() throws Exception {
    I= new Inversor("pepe");
    b= new InversionEnAcciones("google", 5, 10);
		LocalDate fecha= LocalDate.of(2023,9,8);
		a= new PlazoFijo(fecha, 1000, 1);
    I.agregarInversion(a);
    I.agregarInversion(b);

		
	}
	
	@Test
    public void testConstructor() {
        assertEquals("pepe", I.getNombre());
    }

    @Test
    public void testValorActual() {
        assertEquals(1350, I.valorActual());
    }
}
