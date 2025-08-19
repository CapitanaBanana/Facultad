package ar.edu.info.unlp.ejercicioDemo;

public abstract class Topografia {
    public abstract double proporcionDeAgua();
    public abstract boolean igual(Topografia otra);
    public abstract boolean igual(Agua agua);
    public abstract boolean igual(Tierra tierra);
}
