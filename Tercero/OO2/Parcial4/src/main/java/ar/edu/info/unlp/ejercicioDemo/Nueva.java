package ar.edu.info.unlp.ejercicioDemo;

public class Nueva extends Estado {
    public Nueva(TareaSimple tarea) {
        super(tarea);
    }

    public void Iniciar() {
        tarea.setEstado(new Iniciada(tarea));
        tarea.setMomentoInicio(System.currentTimeMillis());
    }

    public void Completar() {
    }

    public long CalcularTiempoUtilizado() {
        return 0;
    }

}