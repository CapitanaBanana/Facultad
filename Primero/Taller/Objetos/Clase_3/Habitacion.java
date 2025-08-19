/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

import PaqueteLectura.GeneradorAleatorio;

/**
 *
 * @author josem
 */
public class Habitacion {
    private int precio=0;
    private boolean ocupada=false;
    private Persona cliente=null;
    
    public Habitacion(){
        GeneradorAleatorio.iniciar();
        ocupada=false;
        precio=GeneradorAleatorio.generarInt(6000)+2000;
    }
    public double getPrecio() {
        return precio;
    }
    public void ingresarCliente(Persona unCliente){
        cliente=unCliente;
        ocupada=true;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public void aumentarPrecio(int aumento){
        this.precio= this.precio+ aumento;
    }
    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
        ocupada=true;
    }
    public String toString(){
        String aux;
        if (ocupada== true)
            aux= ("Precio: "+this.precio+ "ocupada por "+ this.cliente.toString());
        else
            aux= "Precio: "+ this.precio;
        return aux;
    }
}
