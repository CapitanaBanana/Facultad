package ar.edu.info.unlp.ejercicioDemo;

public class TareaSimple extends Tarea {
    private Estado estado;
    private int duracionEstimada;
    private long momentoInicio;
    private long momentoFin;

    public TareaSimple(String nombre, String descripcion, int duracionEstimada) {
        super(nombre, descripcion);
        this.estado = new Nueva(this);
        this.duracionEstimada = duracionEstimada;
    }

    @Override
    public void Iniciar() {
        estado.Iniciar();
    }

    @Override
    public void Completar() {
        estado.Completar();
    }

    @Override
    public int CalcularDuracionEstimada() {
        return this.duracionEstimada;
    }

    @Override
    public long CalcularTiempoUtilizado() {
        return estado.CalcularTiempoUtilizado();
    }

    @Override
    public double CalcularAvance() {
        return this.CalcularTiempoUtilizado() / this.CalcularDuracionEstimada();
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setMomentoFin(long momentoFin) {
        this.momentoFin = momentoFin;
    }

    public void setMomentoInicio(long momentoInicio) {
        this.momentoInicio = momentoInicio;
    }

    public long getMomentoInicio() {
        return momentoInicio;
    }

    public long getMomentoFin() {
        return momentoFin;
    }

}
