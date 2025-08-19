package ar.edu.unlp.info.oo2.facturacion_llamadas;

public abstract class Llamada {
    private String origen;
    private String destino;
    private int duracion;
    private static double iva = 0.21;

    public Llamada(String origen, String destino, int duracion) {
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
    }

    public abstract double calcularMontoLlamada();

    protected double calcularIva(double precioSinIva) {
        return precioSinIva * iva;
    }

    public String getRemitente() {
        return destino;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public String getOrigen() {
        return origen;
    }
}
