package ar.edu.info.unlp.ejercicioDemo;

public abstract class Decorator implements Reporte {
    protected Reporte reporte;

    public Decorator(Reporte reporte) {
        this.reporte = reporte;
    }

    public abstract String generarReporte();
}
