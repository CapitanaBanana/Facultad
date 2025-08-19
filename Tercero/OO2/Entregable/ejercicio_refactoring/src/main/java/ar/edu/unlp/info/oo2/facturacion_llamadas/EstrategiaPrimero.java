package ar.edu.unlp.info.oo2.facturacion_llamadas;

import java.util.SortedSet;

public class EstrategiaPrimero implements EstrategiaSeleccion { 
    public String obtenerNumeroLibre(SortedSet<String> lineas) {
        String linea = lineas.first();
        return linea;
    }
}
