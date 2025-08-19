/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

/**
 *
 * @author josem
 */
public class Hotel {
    private Habitacion[] habitaciones;
    private int cantHabitaciones;
    private Habitacion laHabitacion;
    
    public Hotel(int unaCantidad){
        this.habitaciones= new Habitacion[unaCantidad];
        cantHabitaciones=unaCantidad;
        for (int i=0; i<cantHabitaciones; i++)
            habitaciones[i]= new Habitacion();
    }
    public void ingresarCliente(Persona unCliente, int unaHabitacion){
        habitaciones[unaHabitacion].ingresarCliente(unCliente);
    }
    public void aumentarPrecios(int aumento){
        for (int i=0; i<cantHabitaciones; i++){
            habitaciones[i].aumentarPrecio(aumento);
        }
    }
    public void habitaciones(){
        System.out.println("HOTEL COMETEESTA");
        for (int i=0; i<cantHabitaciones; i++){
            System.out.println("Habitacion "+ i+":"+ habitaciones[i].toString());
        }
        System.out.println("--------------------------------");
    }
}
