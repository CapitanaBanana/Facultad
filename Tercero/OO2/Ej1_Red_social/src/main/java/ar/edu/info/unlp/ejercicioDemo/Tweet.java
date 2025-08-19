package ar.edu.info.unlp.ejercicioDemo;

public abstract class Tweet {
  String usuario;

  public Tweet(String usuario) {
    this.usuario = usuario;
  }

  public static boolean verificarLongitud(String texto) {
    return (texto.length() >= 1 && texto.length() <= 280);
  }

  public String getUsuario() {
    return this.usuario;
  }
}
