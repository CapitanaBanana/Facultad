package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase
 * 
 */
public class JuegoTest {

	Juego juego;

	@BeforeEach
	void setUp() throws Exception {
		juego = new Juego();

	}

	@Test
	public void testJugar() {
		assertEquals("Tijera", juego.jugar("Papel", "Tijera"));
		assertEquals("Papel", juego.jugar("Papel", "Piedra"));
		assertEquals("Empate", juego.jugar("Piedra", "Piedra"));
	}
}
