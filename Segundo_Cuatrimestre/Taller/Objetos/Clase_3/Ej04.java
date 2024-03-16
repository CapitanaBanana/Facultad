/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema3;

import PaqueteLectura.GeneradorAleatorio;
import PaqueteLectura.Lector;

/**
 *
 * @author josem
 */
public class Ej04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int hab;
        Persona cliente=new Persona();
        
        System.out.print("Cantidad de habitaciones: ");
        int habitaciones= Lector.leerInt();
        Hotel elHotel=new Hotel(habitaciones);
        System.out.print("Cantidad de personas a ingresar: ");
        int personas= Lector.leerInt();
        for (int i=0; i<personas; i++){
            hab=GeneradorAleatorio.generarInt(habitaciones);
            System.out.println("Habitacion: "+hab);
            System.out.print("Huesped: ");
            String hue= Lector.leerString();
            cliente= new Persona(hue,GeneradorAleatorio.generarInt(9999), GeneradorAleatorio.generarInt(85));
            System.out.println("Cliente: "+cliente.toString());
            elHotel.ingresarCliente(cliente, hab);
        }
       elHotel.habitaciones();
       elHotel.aumentarPrecios(20);
       elHotel.habitaciones();
    }
    
}
