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
public class Ej03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String titulo;
        String autor;
        Estante estante= new Estante(20);
        Libro libro=new Libro();
        System.out.print("Titulo: ");
        titulo= Lector.leerString();
        while (!(titulo.equalsIgnoreCase("zzz"))){
            System.out.print("Autor: ");
            autor= Lector.leerString();
            libro= new Libro(titulo,autor);
            estante.agregarLibro(libro);
            System.out.print("Titulo: ");
            titulo= Lector.leerString();
        }
        estante.imprimirEstante();
        System.out.print("Libro a buscar: ");
        String tit= Lector.leerString();
        System.out.println(estante.buscarLibro(tit).getPrimerAutor());
    }
    
}
