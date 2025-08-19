package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailTest {
  Email e;
  Email eSinA;
  Adjunto a;
  Adjunto a2;
  Adjunto a3;
  @BeforeEach
  void setUp() throws Exception{
    a=new Adjunto("a");
    a2=new Adjunto("abc");
    a3=new Adjunto("defgh");
    eSinA= new Email("email", "cuerpo");
    List<Adjunto> adjuntos= new ArrayList<Adjunto>();
    adjuntos.add(a);
    adjuntos.add(a2);
    adjuntos.add(a3);
    e= new Email("email2", "",adjuntos);
  }
  @Test
  public void testConstructor(){
    assertEquals("email",eSinA.getTitulo());
    assertEquals("cuerpo",eSinA.getCuerpo());
    assertEquals("",e.getCuerpo());
  }
  @Test
  public void testEspacio(){
    assertEquals(15,e.tamanio());
    assertEquals(11,eSinA.tamanio());
  }
}
