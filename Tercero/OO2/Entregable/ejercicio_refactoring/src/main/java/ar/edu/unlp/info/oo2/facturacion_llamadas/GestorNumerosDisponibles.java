package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.TreeSet;
import java.util.SortedSet;

public class GestorNumerosDisponibles {
    private SortedSet<String> lineas = new TreeSet<String>();
    private EstrategiaSeleccion tipoGenerador = new EstrategiaUltimo();

    public SortedSet<String> getLineas() {
        return lineas;
    }

    public boolean agregarNumeroTelefono(String numero) {
        boolean encontre = this.getLineas().contains(numero);
        if (!encontre) {
            this.getLineas().add(numero);
            return true;
        }
        return false;
    }
    
    public String obtenerNumeroLibre() {
        String linea = tipoGenerador.obtenerNumeroLibre(lineas);
        lineas.remove(linea);
        return linea;
    }

    public void cambiarTipoGenerador(EstrategiaSeleccion tipoGenerador) {
        this.tipoGenerador = tipoGenerador;
    }
}
