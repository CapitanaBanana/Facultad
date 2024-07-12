package ar.edu.info.unlp.ejercicioDemo;

public abstract class Tarea {
    private String nombre;
    private String descripcion;

    public Tarea(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public abstract void Iniciar();

    public abstract void Completar();

    public abstract int CalcularDuracionEstimada();

    public abstract long CalcularTiempoUtilizado();

    public abstract double CalcularAvance();
}
