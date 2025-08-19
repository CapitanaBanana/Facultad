package ar.edu.info.unlp.ejercicioDemo;

import java.sql.Date;
import java.time.LocalDate;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class DateLapse {
  private LocalDate from;
  private LocalDate to;
  public DateLapse(LocalDate from, LocalDate to){
    this.from=from;
    this.to=to;
  }

	public LocalDate getFrom(){
    return this.from;
  }

public LocalDate getTo(){
  return this.to;
}

public int sizeInDays(){
  return (int)java.time.temporal.ChronoUnit.DAYS.between(from,to);
}

public boolean includesDate(LocalDate other){
  return (other.isAfter(from) && other.isBefore(to));
} 
}
