package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class LlamadaInternacional extends Llamada {
    private static double precioXSegundo = 150;
    private static double precioEstablecerLlamada = 50;

    public LlamadaInternacional(String origen, String destino, int duracion) {
        super(origen, destino, duracion);
    }

    public double calcularMontoLlamada() {
        double resultado = this.getDuracion() * precioXSegundo;
        return resultado += super.calcularIva(resultado) + precioEstablecerLlamada;
    }
}