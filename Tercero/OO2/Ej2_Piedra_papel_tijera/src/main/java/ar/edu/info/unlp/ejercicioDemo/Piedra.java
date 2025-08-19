package ar.edu.info.unlp.ejercicioDemo;

public class Piedra implements Jugada {
  public String jugar(String oponente) {
    if (oponente.equals("Piedra")) {
      return "Empate";
    }
    if (oponente.equals("Papel")) {
      return "Papel";
    }
    if (oponente.equals("Tijera")) {
      return "Piedra";
    } else
      return "error";
  }
}
