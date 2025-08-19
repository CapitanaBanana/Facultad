package ar.edu.info.unlp.ejercicioDemo;

public class Temporario extends Empleado{
    private int horasTrabajadas;
    private boolean estaCasado;
    private int cantidadHijos;


    public Temporario(int horasTrabajadas, boolean estaCasado, int cantidadHijos, String nombre) {
        super(nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.estaCasado = estaCasado;
        this.cantidadHijos = cantidadHijos;
    }
    
    public double calcularBasico(){
        return (20000 + (300*this.horasTrabajadas));
    }
    public double calcularAdicional(){
        double valor=0;
        if (this.estaCasado){
            valor+=5000;
        }
        valor+= this.cantidadHijos *2000;
        return valor;
        
    }
}