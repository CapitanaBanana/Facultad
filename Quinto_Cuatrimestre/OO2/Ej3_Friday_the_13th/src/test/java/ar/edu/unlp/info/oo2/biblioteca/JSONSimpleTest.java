package ar.edu.unlp.info.oo2.biblioteca;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 
A WallpostTest is a test class for testing the behavior of Wallpost*/
class JSONSimpleTest {

  Biblioteca biblioteca;
  VoorheesExporter exporter;
  Socio socioA;
  Socio socioB;
  JSONParser parser;

  @BeforeEach
  void setUp() throws Exception {
    biblioteca = new Biblioteca();
    exporter=new JSONSimpleAdapter();
    biblioteca.setExporter(exporter);
    socioA=new Socio("pepe", "pepe@gmail", "3212");
    socioB=new Socio("juan", "juan@gmail", "1234");
    parser=new JSONParser();
}


  @Test
  void testAgregarSocio() {
    assertEquals("[]", biblioteca.exportarSocios());
    biblioteca.agregarSocio(socioA);
    String s= ("[\r\n" + //
                    "\t{\r\n" + 
                    "\t\t\"nombre\": \"pepe\",\r\n" + //
                    "\t\t\"email\": \"pepe@gmail\",\r\n" + //
                    "\t\t\"legajo\": \"3212\"\r\n" + //
                    "\t}\r\n" + //
                    "]");
    try {
        assertEquals(parser.parse(s), parser.parse(biblioteca.exportarSocios()));
    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    } 
  }
