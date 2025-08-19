package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarpetaTest {
  Carpeta con;
  Carpeta sin;
  Email mail1;
  Email mail2;

  @BeforeEach
  void setUp() throws Exception{
    con= new Carpeta("Carpeta Con Archivos");
    sin= new Carpeta("Carpeta sin Archivos");
    mail1= new Email("email piola", "alto cuerpazo bb");
    mail2= new Email("email repiola", "alto");
  }
  @Test
  void testAgregarYBorrarCorreo(){
    assertEquals(0, sin.getEmails().size());
    con.recibirCorreo(mail1);
    assertEquals(1, con.getEmails().size());
    con.recibirCorreo(mail2);
    assertEquals(2, con.getEmails().size());
    con.borrarCorreo(mail2);
    assertEquals(1, con.getEmails().size());
  }
  @Test
  void testBuscarCorreo(){
    con.recibirCorreo(mail1);
    con.recibirCorreo(mail2);
    assertEquals(mail1, con.buscarCorreo("piola"));
    assertEquals(null, con.buscarCorreo("jeje"));
    assertEquals(null, sin.buscarCorreo("jeje"));
  }
  @Test
  public void testTamanio(){
    con.recibirCorreo(mail1);
    con.recibirCorreo(mail2);
    assertEquals(0,sin.tamanio());
    assertEquals(44,con.tamanio());
  }
}
