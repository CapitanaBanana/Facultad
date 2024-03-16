package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;
import java.util.List;

public class Email {
  private String titulo;
  private String cuerpo;
  private List<Adjunto> adjuntos;

  public Email(String titulo, String cuerpo, List<Adjunto> adjuntos) {
    this.titulo = titulo;
    this.cuerpo = cuerpo;
    this.adjuntos = adjuntos;
  }

  public Email(String titulo, String cuerpo) {
    this.titulo = titulo;
    this.cuerpo = cuerpo;
    this.adjuntos = new ArrayList<Adjunto>();
  }

  public String getTitulo() {
    return this.titulo;
  }

  public String getCuerpo() {
    return this.cuerpo;
  }

  public List<Adjunto> getAdjuntos() {
    return this.adjuntos;
  }

  public int tamanio() {
    return this.cuerpo.length() + this.titulo.length()
        + this.adjuntos.stream().mapToInt(adjunto -> adjunto.getTamanio()).sum();
  }

}
