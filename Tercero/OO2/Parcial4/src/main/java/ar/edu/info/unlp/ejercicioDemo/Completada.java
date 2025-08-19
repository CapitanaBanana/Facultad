package ar.edu.info.unlp.ejercicioDemo;

public class Completada extends Estado {
    public Completada(TareaSimple tarea) {
        super(tarea);
    }

    public void Iniciar() {

    }

    public void Completar() {

    }

    public long CalcularTiempoUtilizado() {
        return tarea.getMomentoFin() - tarea.getMomentoInicio();
    }

}
