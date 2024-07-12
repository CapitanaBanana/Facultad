package ar.edu.info.unlp.ejercicioDemo;

public class Iniciada extends Estado {
    public Iniciada(TareaSimple tarea) {
        super(tarea);
    }

    public void Iniciar() {
    }

    public void Completar() {
        tarea.setMomentoFin(System.currentTimeMillis());
        tarea.setEstado(new Completada(tarea));
    }

    public long CalcularTiempoUtilizado() {
        return 0;
    }

}
