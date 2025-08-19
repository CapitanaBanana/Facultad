package ar.edu.info.unlp.ejercicioDemo;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
  public class Usuario {
  private String domicilio;
  private String nombre;
  private List<Factura> facturas;
  private List<Consumo> consumos;

  public Usuario() {
  }

  public Usuario(String domicilio, String nombre) {
    this.domicilio = domicilio;
    this.nombre = nombre;
    this.facturas = new ArrayList<Factura>();
    this.consumos = new ArrayList<Consumo>();
  }
  public void agregarMedicion(Consumo medicion){
    this.consumos.add(medicion);
  }
  public Factura facturarEnBaseA(double precioKWh){
    if (this.consumos.size()>= 1){
      Consumo ultimoConsumo = consumos.stream().max((c1, c2)-> c1.getFecha().compareTo(c2.getFecha())).orElse(null);
      double desc=0;
      if (ultimoConsumo.factorDePotencia() > 0.8){
        desc=10;
      }
      return new Factura(ultimoConsumo.costoEnBaseA(precioKWh), desc, this);
    }
    else return new Factura(0, 0, this);
  }

  public double ultimoConsumoActiva(){
    if (this.consumos.size()>= 1)
      return this.consumos.stream().max((c1, c2)-> c1.getFecha().compareTo(c2.getFecha())).orElse(null).getConsumoEnergiaActiva();
    else return 0;
  }

  public Consumo ultimoConsumo(){
    return this.consumos.stream().max((c1, c2)-> c1.getFecha().compareTo(c2.getFecha())).orElse(null);
  }

  public String getDomicilio() {
    return this.domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
    public List<Factura> getFacturas() {
    return this.facturas;
  }

  public void setFacturas(List<Factura> facturas) {
    this.facturas = facturas;
  }

  public List<Consumo> getConsumos() {
    return this.consumos;
  }

  public void setConsumos(List<Consumo> consumos) {
    this.consumos = consumos;
  }


}
