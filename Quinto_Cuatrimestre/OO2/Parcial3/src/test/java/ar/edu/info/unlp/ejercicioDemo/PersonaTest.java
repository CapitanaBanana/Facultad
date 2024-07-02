package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase
 * 
 */
public class PersonaTest {

    Aplicacion app;

    @BeforeEach
    void setUp() throws Exception {
        app = new Aplicacion();

    }

    @Test
    public void testNombreCompleto() {
        assertEquals("pepe", app.generarReporte1());
    }
}
