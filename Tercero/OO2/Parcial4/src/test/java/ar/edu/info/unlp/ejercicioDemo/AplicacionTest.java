package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase
 * 
 */
public class AplicacionTest {

    Tarea almuerzo, compras, ve|rduleria, cocinar, mesa, supermercado;
    Aplicacion app;

    @BeforeEach
    void setUp() throws Exception {
        supermercado = new TareaSimple("supermercado", "Tarea simple", 3);
        verduleria = new TareaSimple("verduleria", "Tarea simple", 1);
        cocinar = new TareaSimple("cocinar", "Tarea simple", 2);
        mesa = new TareaSimple("mesa", "Tarea simple", 1);
        supermercado.Iniciar();
        TimeUnit.SECONDS.sleep(2);
        supermercado.Completar();
        verduleria.Iniciar();
        TimeUnit.SECONDS.sleep(1);
        verduleria.Completar();
        cocinar.Iniciar();
        ArrayList<Tarea> subtareas = new ArrayList<Tarea>();
        subtareas.add(supermercado);
        subtareas.add(verduleria);
        compras = new TareaCompleja("compras", "Tarea compleja", subtareas);
        ArrayList<Tarea> subtareas2 = new ArrayList<Tarea>();
        subtareas2.add(compras);
        subtareas2.add(cocinar);
        subtareas2.add(mesa);
        almuerzo = new TareaCompleja("almuerzo", "Tarea compleja", subtareas2);
        app = new Aplicacion();
        app.addTarea(almuerzo);

    }

    @Test
    public void testAvanceDeTarea() {
        assertEquals(430.0, almuerzo.CalcularAvance());
    }
}
