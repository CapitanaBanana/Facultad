package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class LlamadaNacional extends Llamada {
    private static double precioXSegundo = 3;

    public LlamadaNacional(String origen, String destino, int duracion) {
        super(origen, destino, duracion);
    }

    public double calcularMontoLlamada() {
        double resultado = this.getDuracion() * precioXSegundo;
        return resultado += super.calcularIva(resultado);
    }

}