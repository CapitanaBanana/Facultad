package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PersonaTest {
    Empresa e;
    Empleado a;
    Empleado b;
    Empleado c;
    @BeforeEach
  void setUp() throws Exception {
    e= new Empresa();
  }


  @Test
  void getSueldo() {
    a=new Pasante(2, "pasante");
    b=new Planta(true, 2, 3,"planta");
    c=new Temporario(3,true, 3,"temporario");
    e.agregarEmpleado(a);
    e.agregarEmpleado(b);
    e.agregarEmpleado(c);
    assertEquals("pasante Sueldo: 21200.0\r\n" + //
                "planta Sueldo: 57750.0\r\n" + //
                "temporario Sueldo: 28633.0\r\n", e.obtenerSalarios());
    
  }
}
