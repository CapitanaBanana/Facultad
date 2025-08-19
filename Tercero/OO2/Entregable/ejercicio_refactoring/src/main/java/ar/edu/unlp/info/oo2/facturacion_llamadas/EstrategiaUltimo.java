package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.SortedSet;

public class EstrategiaUltimo implements EstrategiaSeleccion {
    public String obtenerNumeroLibre(SortedSet<String> lineas) {
        String linea = lineas.last();
        return linea;
    }
}