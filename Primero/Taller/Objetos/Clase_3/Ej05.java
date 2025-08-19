/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema3;

import PaqueteLectura.Lector;

/**
 *
 * @author josem
 */
public class Ej05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circulo circulito;
        String relleno;
        String linea;
        double radio;
        
        System.out.print("Radio: ");
        radio= Lector.leerDouble();
        while (radio != 0){
            System.out.print("relleno: ");
            relleno= Lector.leerString();
            System.out.print("Linea: ");
            linea= Lector.leerString();
            circulito= new Circulo(radio, relleno, linea);
            System.out.println("--------------------------");
            System.out.println("Perimetro: "+ circulito.calcularPerimetro());
            System.out.println("Area: "+ circulito.calcularArea());
            
            System.out.print("Radio: ");
            radio= Lector.leerDouble();
        }
        
        
    }
    
}
