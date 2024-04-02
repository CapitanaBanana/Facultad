package ar.edu.info.unlp.ejercicioDemo;

public abstract class Empleado {
    private String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }
    public double getSueldo(){
        return calcularBasico()+calcularAdicional()-calcularDescuento();
    }
    public abstract double calcularBasico();
    public abstract double calcularAdicional();
    public double calcularDescuento(){
        double valor=0;
        valor=  (13 * calcularBasico()) / 100;
        valor+= (5 * calcularAdicional()) / 100;
        return valor;
    };
}
