package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;

public class TareaCompleja extends Tarea {
    private ArrayList<Tarea> subTareas;

    public TareaCompleja(String nombre, String descripcion, ArrayList<Tarea> subTareas) {
        super(nombre, descripcion);
        this.subTareas = new ArrayList<Tarea>();
        this.subTareas = subTareas;
    }

    @Override
    public void Iniciar() {
        subTareas.stream().forEach(tarea -> tarea.Iniciar());
    }

    @Override
    public void Completar() {
        subTareas.stream().forEach(tarea -> tarea.Completar());
    }

    @Override
    public int CalcularDuracionEstimada() {
        return subTareas.stream().mapToInt(tarea -> tarea.CalcularDuracionEstimada()).sum();
    }

    @Override
    public long CalcularTiempoUtilizado() {
        return subTareas.stream().mapToLong(tarea -> tarea.CalcularTiempoUtilizado()).sum();
    }

    @Override
    public double CalcularAvance() {
        return this.CalcularTiempoUtilizado() / this.CalcularDuracionEstimada();
    }

}
