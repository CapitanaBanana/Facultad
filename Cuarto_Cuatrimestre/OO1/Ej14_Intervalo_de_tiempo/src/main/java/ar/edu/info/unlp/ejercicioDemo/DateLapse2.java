package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;

public class DateLapse2 {
  private LocalDate from;
  private int sizeInDays;
  public DateLapse2(LocalDate from, int sizeInDays){
    this.from=from;
    this.sizeInDays=sizeInDays;
  }

	public LocalDate getFrom(){
    return this.from;
  }

public int sizeInDays(){
  return this.sizeInDays;
}

public boolean includesDate(LocalDate other){
  return (other.isAfter(from) && other.isBefore(this.from.plusDays(sizeInDays)));
} 
}
