package ar.edu.info.unlp.ejercicioDemo;

public class Papel implements Jugada {
  public String jugar(String oponente) {
    if (oponente.equals("Piedra")) {
      return "Papel";
    }
    if (oponente.equals("Papel")) {
      return "Empate";
    }
    if (oponente.equals("Tijera")) {
      return "Tijera";
    } else
      return "error";
  }
}
