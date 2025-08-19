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
public class Ej01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double a,b,c;
        String relleno, linea;
        System.out.print("Lado a: ");
        a= Lector.leerDouble();
        System.out.print("Lado b: ");
        b= Lector.leerDouble();
        System.out.print("Lado c: ");
        c= Lector.leerDouble();
        System.out.print("Color relleno: ");
        relleno= Lector.leerString();
        System.out.print("Color linea: ");
        linea= Lector.leerString();
        Triangulo tri=new Triangulo(a,b,c,relleno,linea);
        System.out.println("El perimetro es: "+ tri.calcularPerimetro());
        System.out.println("El area es: "+ tri.calcularArea());
    }
    
}