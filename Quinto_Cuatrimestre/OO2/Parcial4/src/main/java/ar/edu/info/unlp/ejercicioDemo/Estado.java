package ar.edu.info.unlp.ejercicioDemo;

public abstract class Estado {
    protected TareaSimple tarea;

    public Estado(TareaSimple tarea) {
        this.tarea = tarea;
    }

    public abstract void Iniciar();

    public abstract void Completar();

    public abstract long CalcularTiempoUtilizado();

}