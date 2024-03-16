package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class DateLapseTest {
	
	DateLapse lapso, lapsoCeroDias,lapsoAlReves;
	
	@BeforeEach
	void setUp() throws Exception {
		lapso=new DateLapse(LocalDate.of(2020, 1,1), LocalDate.of(2021,1,1));
		lapsoCeroDias=new DateLapse(LocalDate.of(2020, 1,1), LocalDate.of(2020,1,1));
		lapsoAlReves=new DateLapse(LocalDate.of(2023,2,2), LocalDate.of(2020, 1,1));
	}
	
    @Test
    public void testSizeInDays() {
        assertEquals(366, lapso.sizeInDays());
				assertEquals(0, lapsoCeroDias.sizeInDays());
				assertEquals(-1128, lapsoAlReves.sizeInDays());
    }
		@Test void testincludesDate(){
			assertEquals(true, lapso.includesDate(LocalDate.of(2020,1,2)));
			assertEquals(false, lapso.includesDate(LocalDate.of(2021,1,1)));
			assertEquals(true, lapso.includesDate(LocalDate.of(2020,2,1)));
			assertEquals(false, lapso.includesDate(LocalDate.of(2023,2,1)));
			assertEquals(false, lapsoCeroDias.includesDate(LocalDate.of(2020,1,1)));
			assertEquals(false, lapsoAlReves.includesDate(LocalDate.of(2021,2,1)));
			assertEquals(false, lapsoAlReves.includesDate(LocalDate.of(2023,2,2)));
		}
}
