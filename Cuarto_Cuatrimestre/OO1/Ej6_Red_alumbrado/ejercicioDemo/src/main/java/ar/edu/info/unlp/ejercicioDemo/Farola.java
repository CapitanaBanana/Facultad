package ar.edu.info.unlp.ejercicioDemo;
import java.util.ArrayList;
import java.util.List;

public class Farola {
  public boolean encendida;
  public List<Farola> vecinos;
  /*
* Crear una farola. Debe inicializarla como apagada
*/
public Farola (){
  this.encendida = false;
  this.vecinos = new ArrayList<Farola>();
}
/*
* Crea la relación de vecinos entre las farolas. La relación de vecinos entre las farolas es recíproca, es decir el receptor del mensaje será vecino de otraFarola, al igual que otraFarola también se convertirá en vecina del receptor del mensaje
*/
public void pairWithNeighbor( Farola otraFarola ){
  this.vecinos.add(otraFarola);
  otraFarola.agregarFarola(this);
}

public void agregarFarola( Farola otraFarola ){
  this.vecinos.add(otraFarola);
}
/*
* Retorna sus farolas vecinas
*/
public List<Farola> getNeighbors(){
  return vecinos;
}


/*
* Si la farola no está encendida, la enciende y propaga la acción.
*/
public void turnOn(){
  if (this.encendida == false){
    this.encendida = true; 
    for (Farola farola : vecinos) {
      farola.turnOn();
    }
  }
}

/*
* Si la farola no está apagada, la apaga y propaga la acción.
*/
public void turnOff(){
  if (this.encendida == true){
    this.encendida = false; 
    for (Farola farola : vecinos) {
      farola.turnOff();
    }
  }
  
}

/*
* Retorna true si la farola está encendida.
*/
public boolean isOn(){
  return this.encendida;
}
}
