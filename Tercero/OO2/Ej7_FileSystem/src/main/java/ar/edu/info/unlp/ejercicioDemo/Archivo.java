package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Archivo extends FileSystem{
    private int tamanio;

    public Archivo(int tamanio, String nombre,LocalDate creacion){
        super(nombre, creacion);
        this.tamanio=tamanio;
    }

    public Archivo(String nombre, int tamanio){
        super(nombre, LocalDate.now());
        this.tamanio=tamanio;
    }
    public int tamanoTotalOcupado(){
        return this.tamanio;
    }
    public Archivo archivoMasGrande(){
        return this;
    }
    public Archivo archivoMasNuevo(){
        return this;
    }
    public FileSystem buscar(String nombre){
        if (this.getNombre().equals(nombre)){
            return this;
        }
        else
            return null;
    }
    public ArrayList<FileSystem> buscarTodos(String nombre){
        ArrayList<FileSystem> lista= new ArrayList<>();
        if (this.getNombre().equals(nombre)){
            lista.add(this);
            return lista;
        }
        else
            return null;
    }
    public String listadoDeContenido(){
        return this.getNombre();
    }
}
