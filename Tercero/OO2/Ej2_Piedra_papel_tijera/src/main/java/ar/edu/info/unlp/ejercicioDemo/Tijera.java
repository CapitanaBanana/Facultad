package ar.edu.info.unlp.ejercicioDemo;

public class Tijera implements Jugada {
  public String jugar(String oponente) {
    if (oponente.equals("Piedra")) {
      return "Piedra";
    }
    if (oponente.equals("Papel")) {
      return "Tijera";
    }
    if (oponente.equals("Tijera")) {
      return "Empate";
    } else
      return "error";
  }
}
