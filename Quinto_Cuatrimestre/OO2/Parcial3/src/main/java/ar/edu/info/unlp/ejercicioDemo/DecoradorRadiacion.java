package ar.edu.info.unlp.ejercicioDemo;

public class DecoradorRadiacion extends Decorator {
    private double radiacion;

    public DecoradorRadiacion(Reporte reporte, double radiacion) {
        super(reporte);
        this.radiacion = radiacion;
    }

    @Override
    public String generarReporte() {
        return reporte.generarReporte() + "Radiacion: " + radiacion + "\n";
    }

}
