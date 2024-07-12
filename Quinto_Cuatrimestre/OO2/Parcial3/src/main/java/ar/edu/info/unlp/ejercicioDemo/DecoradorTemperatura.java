package ar.edu.info.unlp.ejercicioDemo;

public class DecoradorTemperatura extends Decorator {
    private double temperatura;

    public DecoradorTemperatura(Reporte reporte, double temperatura) {
        super(reporte);
        this.temperatura = temperatura;
    }

    @Override
    public String generarReporte() {
        return reporte.generarReporte() + "Temperatura: " + temperatura + "\n";
    }

}
