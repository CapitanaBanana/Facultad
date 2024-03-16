package ar.edu.info.unlp.ejercicioDemo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteDeCorreoTest {
  ClienteDeCorreo cliente;
  Email mail1;
  Email mail2;

  @BeforeEach
  void setUp() throws Exception{
    cliente= new ClienteDeCorreo();
    cliente.crearCarpeta("carpeta");
    mail1= new Email("email piola", "alto cuerpazo bb");
    mail2= new Email("email repiola", "alto");
  }
  @Test
  void recibirYMoverCorreo(){
    cliente.recibir(mail1);
    cliente.recibir(mail2);
    assertEquals(2, cliente.getCarpeta("Inbox").getEmails().size());
    assertEquals(0, cliente.getCarpeta("carpeta").getEmails().size());
    cliente.mover(mail1, cliente.getCarpeta("Inbox"), cliente.getCarpeta("carpeta"));
    assertEquals(1, cliente.getCarpeta("Inbox").getEmails().size());
    assertEquals(1, cliente.getCarpeta("carpeta").getEmails().size());
  }
  @Test
  public void testBuscarCorreo(){
    cliente.recibir(mail1);
    cliente.recibir(mail2);
    assertEquals(mail1,cliente.buscar("bb"));
    assertEquals(null,cliente.buscar("pepe"));
  }
}
