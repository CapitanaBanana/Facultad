package ar.edu.info.unlp.ejercicioDemo;

import java.sql.Struct;
import java.time.LocalDate;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class Mamifero{
  private String identificador;
  private String especie;
  private LocalDate fechaDeNacimiento; 
  private Mamifero padre = null;
  private Mamifero madre = null;

  public boolean tieneComoAncestroA(Mamifero mamifero){
    if(mamifero.equals(this.padre) || mamifero.equals(this.madre)){
      return true;
    }
    if (this.madre != null && this.madre.tieneComoAncestroA(mamifero)){
      return true; 
    }
    if (this.padre != null && this.padre.tieneComoAncestroA(mamifero)){
      return true; 
    }
      return false;
  }

  public Mamifero (){}
  public Mamifero (String identificador){
    this.identificador=identificador;
  }
  public String getIdentificador(){
    return this.identificador;
  }

  public void setIdentificador(String identificador){
    this.identificador = identificador;
  }
  public String getEspecie() {
    return this.especie;
  }

  public void setEspecie(String especie) {
    this.especie = especie;
  }

  public LocalDate getFechaDeNacimiento() {
    return this.fechaDeNacimiento;
  }

  public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public Mamifero getPadre() {
    return this.padre;
  }

  public void setPadre(Mamifero padre) {
    this.padre = padre;
  }

  public Mamifero getMadre() {
    return this.madre;
  }

  public void setMadre(Mamifero madre) {
    this.madre = madre;
  }

  public Mamifero getAbueloMaterno() {
    if (this.madre != null)
      return this.madre.getPadre();
    else
      return null;
  }

  public Mamifero getAbueloPaterno() {
    if (this.padre != null)
      return this.padre.getPadre();
    else
      return null;
  }

  public Mamifero getAbuelaMaterna() {
    if (this.madre != null)
      return this.madre.getMadre();
    else
      return null;
  }

  public Mamifero getAbuelaPaterna() {
    if (this.padre != null)
      return this.padre.getMadre();
    else
      return null;
  }

}
