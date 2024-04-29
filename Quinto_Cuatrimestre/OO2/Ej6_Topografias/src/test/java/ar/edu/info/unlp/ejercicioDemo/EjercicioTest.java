package ar.edu.info.unlp.ejercicioDemo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EjercicioTest {
    Agua agua, agua2;
    Tierra tierra, tierra2;
    Mixta mixta, mixtaAlReves, mixtaFlashera, mixtaFlashera2, mixtaFlashera3;
    ArrayList<Topografia> lista, listaAlReves, listaFlashera, listaFlashera2;
    @BeforeEach
	void setUp() throws Exception {
		agua = new Agua(); agua2= new Agua();
		tierra = new Tierra(); tierra2 = new Tierra();
        lista= new ArrayList<>(); listaAlReves = new ArrayList<>();
        lista.add(agua); lista.add(tierra); lista.add(agua2); lista.add(tierra2);
        listaAlReves.add(tierra);  listaAlReves.add(agua);  listaAlReves.add(tierra2); listaAlReves.add(agua2);
        mixta = new Mixta(lista); mixtaAlReves = new Mixta(listaAlReves);
        //cosas del d
        listaFlashera= new ArrayList<>(); listaFlashera2 = new ArrayList<>();
        listaFlashera.add(agua); listaFlashera.add(tierra); listaFlashera.add(mixta); listaFlashera.add(tierra2);
        listaFlashera2.add(agua); listaFlashera2.add(tierra); listaFlashera2.add(mixtaAlReves); listaFlashera2.add(tierra2);
        mixtaFlashera= new Mixta(listaFlashera);
        mixtaFlashera2= new Mixta(listaFlashera2);
        mixtaFlashera3 = new Mixta(listaFlashera);
        
	}
	
    @Test
    public void testA() {
        assertEquals(agua.proporcionDeAgua(), agua2.proporcionDeAgua());
        assertEquals(true, agua.igual(agua2));
        assertEquals(false, agua.igual(tierra));
    }
    @Test
    public void testB() {
        assertEquals(tierra.proporcionDeAgua(), tierra2.proporcionDeAgua());
        assertEquals(false, tierra.igual(agua));
        assertEquals(true, tierra.igual(tierra2));
    }
    @Test
    public void testC() {
        assertEquals(mixta.proporcionDeAgua(), 0.5);
        assertEquals(mixtaAlReves.proporcionDeAgua(), mixta.proporcionDeAgua());
        assertEquals(false, mixta.igual(mixtaAlReves));
    }
    @Test
    public void testD() {
        assertEquals(mixtaFlashera.proporcionDeAgua(), mixtaFlashera2.proporcionDeAgua());
        assertEquals(false, mixtaFlashera.igual(mixtaFlashera2));
        assertEquals(true, mixtaFlashera.igual(mixtaFlashera3));
        assertEquals(true, mixtaFlashera.igual(mixtaFlashera));
    }
}
