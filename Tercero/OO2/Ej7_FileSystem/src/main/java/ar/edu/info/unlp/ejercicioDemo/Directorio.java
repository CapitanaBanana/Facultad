package ar.edu.info.unlp.ejercicioDemo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Directorio extends FileSystem{
    private ArrayList<FileSystem> archivos;

    public void addComponente(FileSystem arch){
        archivos.add(arch);
    }
    public Directorio(LocalDate creacion, String nombre, ArrayList<FileSystem> archivos){
        super(nombre,creacion);
        this.archivos=archivos;
    }
    public Directorio(String nombre){
        super(nombre, LocalDate.now());
        this.archivos=new ArrayList<>();
    }
    public int tamanoTotalOcupado(){
        return archivos.stream().mapToInt(archivo-> archivo.tamanoTotalOcupado()).sum() +32;
    }
    public Archivo archivoMasGrande(){
        return this.archivos.stream().map(archivo -> archivo.archivoMasGrande()).max((archivo1, archivo2) -> Integer.compare(archivo1.tamanoTotalOcupado(), 
        archivo2.tamanoTotalOcupado())).orElse(null);
    }
    public Archivo archivoMasNuevo(){
        return this.archivos.stream().map(archivo -> archivo.archivoMasNuevo()).max((archivo1, archivo2) -> archivo1.getCreacion().compareTo(archivo2.getCreacion())).orElse(null);
    }
    public FileSystem buscar(String nombre){
        if (this.getNombre().equals(nombre)){
            return this;
        }
        else
            return this.archivos.stream().filter(archivo -> archivo.buscar(nombre)!=null).findFirst().orElse(null);
    }
    public ArrayList<FileSystem> buscarTodos(String nombre){
        ArrayList<FileSystem> aux= new ArrayList<>();
        aux.addAll(this.archivos.stream().filter(archivo -> archivo.buscar(nombre)!=null).toList());
        return aux;
    }
    public String listadoDeContenido(){
        StringBuilder contenido= new StringBuilder();
        for (FileSystem fileSystem : archivos) {
            contenido.append(getNombre()).append("\n");
            this.archivos.stream().forEach(archivo-> contenido.append("/").append(archivo.listadoDeContenido()));
        }
        return contenido.toString();
    }
}
