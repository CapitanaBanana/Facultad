package ar.edu.info.unlp.ejercicioDemo;

public class Planta extends Empleado{
    private boolean estaCasado;
    private int cantidadHijos;
    private int aniosAntiguedad;


    public Planta(boolean estaCasado, int cantidadHijos, int aniosAntiguedad,String nombre) {
        super(nombre);
        this.estaCasado = estaCasado;
        this.cantidadHijos = cantidadHijos;
        this.aniosAntiguedad=aniosAntiguedad;
    }

    public double calcularBasico(){
        return 50000;
    }
    public double calcularAdicional(){
        double valor=0;
        if (this.estaCasado){
            valor+=5000;
        }
        valor+= this.cantidadHijos *2000;
        valor+=2000* this.aniosAntiguedad;
        return valor;
    }
}
