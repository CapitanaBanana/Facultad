package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaTest {
  Sistema sistema;
  Usuario usr1, usr2, usr3;
  Tweet tweet1, tweet2;

  @BeforeEach
  void setUp() throws Exception {
    sistema = new Sistema();
    usr1 = new Usuario("NadieNuncaNada");
    usr2 = new Usuario("CapitanaBanana");
    usr3 = new Usuario("CapitanaBanana");
    tweet1 = new TweetBase("Este es un tweet", "CapitanaBanana");
    tweet2 = new TweetBase("Este es otro tweet", "NadieNuncaNada");
  }

  @Test
  public void testRegistrarUsuario() {
    sistema.registrarUsuario(usr1);
    sistema.registrarUsuario(usr2);
    sistema.registrarUsuario(usr3);
    assertEquals("[NadieNuncaNada, CapitanaBanana]", sistema.getUsuarios().toString());
  }

  @Test
  public void testEliminarUsuario() {
    usr1.twittear("Este es un tweet");
    usr1.retwittear(tweet1);
    usr2.twittear("pepe");
    sistema.registrarUsuario(usr1);
    sistema.eliminarUsuario("CapitanaBanana");
    assertEquals("[NadieNuncaNada]",
        sistema.getUsuarios().toString());
  }
}
