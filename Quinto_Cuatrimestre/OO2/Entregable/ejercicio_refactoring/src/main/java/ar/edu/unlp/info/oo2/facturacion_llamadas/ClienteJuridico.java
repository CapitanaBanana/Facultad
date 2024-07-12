package ar.edu.unlp.info.oo2.facturacion_llamadas;

public class ClienteJuridico extends Cliente {
    private String cuit;
    static double descuentoJur = 0.15;

    public ClienteJuridico(String nombre, String numeroTelefono, String cuit) {
        super(nombre, numeroTelefono);
        this.cuit = cuit;
    }

    public double aplicarDescuento(double monto) {
        return monto * (1 - descuentoJur);
    }

    public String getCuit() {
        return this.cuit;
    }
}
