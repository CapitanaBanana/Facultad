package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase 
 * 
 */
public class ReporteDeConstruccionTest {
	
	ReporteDeConstruccion R;
	Cilindro C;
	Esfera E;
	PrismaRectangular P;
	
	@BeforeEach
	void setUp() throws Exception {
		R= new ReporteDeConstruccion();
		C = new Cilindro("verde", "madera", 1, 2);
		P = new PrismaRectangular("rojo", "madera", 1, 2,2);
		E = new Esfera("rojo", "metal", 1);
		R.agregarPieza(E);
		R.agregarPieza(C);
		R.agregarPieza(P);
	}
	
    @Test
    public void testVolumenDeMaterial(){
			//cilindro 6.28, prisma 4
			assertEquals(10.283185307179586,R.getVolumenDeMaterial("madera"));
			//esfera 4.18
			assertEquals(4.1887902047863905,R.getVolumenDeMaterial("metal"));
			assertEquals(0,R.getVolumenDeMaterial("cemento"));
    }
		@Test
    public void testSuperficieDeColor(){
			assertEquals(18.84955592153876,R.getSuperficieDeColor("verde"));
			//prisma 16, esfera 12.56
			assertEquals(28.566370614359172,R.getSuperficieDeColor("rojo"));
			assertEquals(0,R.getSuperficieDeColor("gris"));
    }
}
