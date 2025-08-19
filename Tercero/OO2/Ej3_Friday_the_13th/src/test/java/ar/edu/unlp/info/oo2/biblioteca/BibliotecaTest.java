package ar.edu.unlp.info.oo2.biblioteca;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 
A WallpostTest is a test class for testing the behavior of Wallpost*/
class BibliotecaTest {

  Biblioteca biblioteca;
  VoorheesExporter exporter;
  Socio socioA;
  Socio socioB;

  @BeforeEach
  void setUp() throws Exception {
    biblioteca = new Biblioteca();
    exporter=new VoorheesExporter();
    biblioteca.setExporter(exporter);
    socioA=new Socio("pepe", "pepe@gmail", "3212");
    socioB=new Socio("juan", "juan@gmail", "1234");
}


  @Test
  void testAgregarSocio() {
    assertEquals("[]", biblioteca.exportarSocios());
    biblioteca.agregarSocio(socioA);
    assertEquals("[\r\n" + //
                "\t{\r\n" + 
                "\t\t\"nombre\": \"pepe\",\r\n" + //
                "\t\t\"email\": \"pepe@gmail\",\r\n" + //
                "\t\t\"legajo\": \"3212\"\r\n" + //
                "\t}\r\n" + //
                "]", biblioteca.exportarSocios());
  }
}