package ar.edu.info.unlp.ejercicioDemo;

public class DecoradorPresion extends Decorator {
    private double presion;

    public DecoradorPresion(Reporte reporte, double presion) {
        super(reporte);
        this.presion = presion;
    }

    @Override
    public String generarReporte() {
        return reporte.generarReporte() + "Presion: " + presion + "\n";
    }

}
