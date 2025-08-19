package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private List<Llamada> llamadas = new ArrayList<Llamada>();
    private String nombre;
    private String numeroTelefono;

    public Cliente(String nombre, String numeroTelefono) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    protected abstract double aplicarDescuento(double costo);

    public double calcularMontoTotalLlamadas() {
        return this.llamadas.stream()
                .mapToDouble(llamada -> aplicarDescuento(llamada.calcularMontoLlamada()))
                .sum();
    }

    public Llamada registrarLlamadaInternacional(Cliente destino, int duracion) {
        Llamada llamada = new LlamadaInternacional(this.getNumeroTelefono(), destino.getNumeroTelefono(), duracion);
        this.llamadas.add(llamada);
        return llamada;
    }

    public Llamada registrarLlamadaNacional(Cliente destino, int duracion) {
        Llamada llamada = new LlamadaNacional(this.getNumeroTelefono(), destino.getNumeroTelefono(), duracion);
        this.llamadas.add(llamada);
        return llamada;
    }

    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

}