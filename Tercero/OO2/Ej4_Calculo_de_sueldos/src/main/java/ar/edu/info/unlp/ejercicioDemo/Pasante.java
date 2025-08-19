package ar.edu.info.unlp.ejercicioDemo;

public class Pasante extends Empleado{
    private int examenesRendidos;


    public Pasante(int examenesRendidos, String nombre) {
        super(nombre);
        this.examenesRendidos = examenesRendidos;
    }
    

    public double calcularBasico(){
        return 20000;
    }
    public double calcularAdicional(){
        return 2000* this.examenesRendidos;
    }
}
