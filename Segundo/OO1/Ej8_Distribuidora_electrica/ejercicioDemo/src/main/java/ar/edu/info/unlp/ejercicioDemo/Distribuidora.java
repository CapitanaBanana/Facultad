package ar.edu.info.unlp.ejercicioDemo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Distribuidora {
  private double precioKW;
  private List<Usuario> usuarios;


  public Distribuidora() {
  }
  public Distribuidora(double precioKWh) {
    this.precioKW = precioKWh;
    this.usuarios= new ArrayList<Usuario>();
  }

  public Distribuidora(double precioKWh, List<Usuario> usuarios) {
    this.precioKW = precioKWh;
    this.usuarios = new ArrayList<Usuario>();
  }

  public double consumoTotalActiva(){
    return usuarios.stream().mapToDouble(usuario -> usuario.ultimoConsumoActiva()).sum();
  }
  public List<Factura> facturar(){
    return usuarios.stream().map(usuario -> usuario.facturarEnBaseA(precioKW)).collect(Collectors.toList());
  }

  public void agregarUsuario(Usuario usuario){
    this.usuarios.add(usuario);
  }

  public double getPrecioKW() {
    return this.precioKW;
  }

  public void setPrecioKWh(double precioKWh) {
    this.precioKW = precioKWh;
  }

  public List<Usuario> getUsuarios() {
    return this.usuarios;
  }

  public void setUsuarios(List<Usuario> usuarios) {
    this.usuarios = usuarios;
  }

}
