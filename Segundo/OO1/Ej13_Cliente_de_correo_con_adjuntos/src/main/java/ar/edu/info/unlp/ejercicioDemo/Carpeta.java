package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;
import java.util.List;

public class Carpeta {
  private String nombre;
  private List<Email> correos;

  public Carpeta(String nombre) {
    this.nombre = nombre;
    this.correos = new ArrayList<Email>();
  }

  public String getNombre() {
    return this.nombre;
  }

  public List<Email> getEmails(){
    return this.correos;
  }
  public void recibirCorreo(Email mail) {
    this.correos.add(mail);
  }

  public void borrarCorreo(Email mail) {
    correos.remove(mail);
  }

  public Email buscarCorreo(String cadena) {
    return this.correos.stream()
        .filter(mail -> (mail.getTitulo().contains(cadena) || mail.getCuerpo().contains(cadena))).findFirst()
        .orElse(null);
  }

  public int tamanio() {
    return this.correos.stream().mapToInt(correo -> correo.tamanio()).sum();
  }
}
