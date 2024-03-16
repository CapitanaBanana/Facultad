/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

/**
 *
 * @author josem
 */
public class Estante {
    private Libro[]libros;
    private int df;
    private int cantLibros=0;

    public Estante(int max){
        df=max;
        this.libros=new Libro[df];
    }
    public int getDf() {
        return df;
    }

    public int getCantLibros() {
        return this.cantLibros;
    }
    public boolean estaLleno(){
        return this.df==this.cantLibros;
    }
    public void agregarLibro(Libro unLibro) {
        if (this.cantLibros<this.df){
            this.libros[this.cantLibros] = unLibro;
            this.cantLibros++;
        }
        else System.out.println("El estante esta lleno");
    }
    public Libro buscarLibro(String unTitulo){
        Libro aux= null;
        int i=0;
        while ((i< this.cantLibros) && (aux==null)){
            if (this.libros[i].getTitulo().equalsIgnoreCase(unTitulo)) 
                    aux=this.libros[i];
            i++;
        }
        return aux;
    }
    public void imprimirEstante(){
        
        for (int i=0; i<this.cantLibros; i++){
            System.out.println("Libro "+ i+": "+ this.libros[i].getTitulo());
        }
    }
    public void imprimirAutores(){
        for (int i=0; i<this.cantLibros; i++){
            System.out.println("Autor "+ i+": "+ this.libros[i].getPrimerAutor());
        }
    }
    
}
