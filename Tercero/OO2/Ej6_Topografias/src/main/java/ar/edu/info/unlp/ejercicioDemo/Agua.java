package ar.edu.info.unlp.ejercicioDemo;

public class Agua extends Topografia{
    public double proporcionDeAgua(){
        return 1;
    }
    public boolean igual(Topografia otra){
        return otra.igual(this);
    }
    @Override
    public boolean igual(Tierra tierra) {
        return false;
    }
    @Override
    public boolean igual(Agua agua) {
        return true;
    }
}
