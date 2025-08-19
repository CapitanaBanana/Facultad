package ar.edu.info.unlp.ejercicioDemo;

import java.util.Scanner;

public class Juego {
  Jugada j;

  public String jugar(String primero, String segundo) {
    if (primero.toLowerCase().equals("Tijera".toLowerCase())) {
      j = new Tijera();
      return (j.jugar(segundo));
    }
    if (primero.toLowerCase().equals("Papel".toLowerCase())) {
      j = new Papel();
      return (j.jugar(segundo));
    }
    if (primero.toLowerCase().equals("Piedra".toLowerCase())) {
      j = new Piedra();
      return (j.jugar(segundo));
    } else
      return "error";
  }

}
