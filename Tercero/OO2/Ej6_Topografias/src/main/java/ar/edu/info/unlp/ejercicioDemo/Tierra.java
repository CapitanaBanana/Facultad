package ar.edu.info.unlp.ejercicioDemo;

public class Tierra extends Topografia{
    public double proporcionDeAgua(){
        return 0;
    }
    @Override
    public boolean igual(Tierra tierra) {
        return true;
    }
    @Override
    public boolean igual(Agua agua) {
        return false;
    }
    public boolean igual(Topografia otra){
        return otra.igual(this);
    }
}
