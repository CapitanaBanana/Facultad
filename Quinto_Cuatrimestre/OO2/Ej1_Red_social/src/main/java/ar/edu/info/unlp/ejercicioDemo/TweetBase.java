package ar.edu.info.unlp.ejercicioDemo;

public class TweetBase extends Tweet {
  private String texto;

  public TweetBase(String texto, String usuario) {
    super(usuario);
    this.texto = texto;
  }

  public String toString() {
    return ("tweet que dice " + this.texto);
  }
}
