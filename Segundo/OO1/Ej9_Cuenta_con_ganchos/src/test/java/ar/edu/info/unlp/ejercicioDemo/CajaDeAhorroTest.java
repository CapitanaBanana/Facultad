package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CajaDeAhorroTest {
  private CajaDeAhorro uno, dos;
  private CuentaCorriente tres, cuatro;

  @BeforeEach
	  void setUp() throws Exception {
	    this.uno = new CajaDeAhorro();
	    this.dos = new CajaDeAhorro();
      this.tres = new CuentaCorriente();
      this.cuatro = new CuentaCorriente();
	  }

    @Test
	  void testConstructor() {
	    assertEquals(0,uno.getSaldo(), "El saldo no es 0");
	    assertEquals(0, tres.getDescubierto(), "el descubierto no es 0");
	  }

    @Test
    void testDepositar(){
      uno.depositar(100);
      tres.depositar(100);
      assertEquals(98.0,uno.getSaldo(), "El saldo no es 98.8");
	    assertEquals(100, tres.getSaldo(), "el salgo no es 100");
      tres.setDescubierto(10);
    }

    @Test
    void testExtraer(){
      uno.depositar(100);
      tres.depositar(100);
      tres.setDescubierto(10);
      assertEquals(false,uno.extraer(100));
      assertEquals(true,uno.extraer(96));
      assertEquals(false, tres.extraer(111));
	    assertEquals(true, tres.extraer(110));
      assertEquals(0.0799999999999983,uno.getSaldo());
      assertEquals(-10,tres.getSaldo());
    }
    @Test
    void testTransferir(){
      uno.depositar(100);
      tres.depositar(100);
      assertEquals(false,uno.transferirACuenta(100, dos));
      assertEquals(true,uno.transferirACuenta(96,dos));
      assertEquals(0.0799999999999983,uno.getSaldo());
      assertEquals(94.08,dos.getSaldo());

      assertEquals(false, tres.transferirACuenta(111, cuatro));
	    assertEquals(true, tres.transferirACuenta(100,cuatro));
      assertEquals(0,tres.getSaldo());
      assertEquals(100,cuatro.getSaldo());
    }
}
