package ar.edu.info.unlp.ejercicioDemo;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class FileSystem {
    private String nombre;
    private LocalDate creacion;

    public FileSystem(String nombre, LocalDate creacion){
        this.nombre=nombre;
        this.creacion=creacion;
    }
    public abstract int tamanoTotalOcupado();
    public abstract Archivo archivoMasGrande();
    public abstract Archivo archivoMasNuevo();
    public abstract FileSystem buscar(String nombre);
    public abstract ArrayList<FileSystem> buscarTodos(String nombre);
    public abstract String listadoDeContenido();

    public String getNombre(){
        return this.nombre;
    }
    public LocalDate getCreacion(){
        return this.creacion;
    }
}
