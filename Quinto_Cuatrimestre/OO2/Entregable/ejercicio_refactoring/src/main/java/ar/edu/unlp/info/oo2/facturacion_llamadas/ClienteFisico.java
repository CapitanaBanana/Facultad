package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class ClienteFisico extends Cliente {
    private String dni;
    static double descuentoFis = 0;

    public ClienteFisico(String nombre, String numeroTelefono, String dni) {
        super(nombre, numeroTelefono);
        this.dni = dni;
    }

    public double aplicarDescuento(double costo) {
        return costo - costo * descuentoFis;
    }

    public String getDni() {
        return dni;
    }
}