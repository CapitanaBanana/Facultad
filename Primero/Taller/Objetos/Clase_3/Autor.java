/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

/**
 *
 * @author josem
 */
public class Autor {
    String nombre, bio, origen;
    
    public Autor(){}
    public Autor(String unNombre, String unaBiografia, String unOrigen){
        nombre=unNombre;
        bio=unaBiografia;
        origen=unOrigen;
    }
    public void setNombre(String unNombre){
        nombre=unNombre;
    }
    public void setBio(String unaBiografia){
        bio=unaBiografia;
    }
    public void setOrigen(String unOrigen){
        origen=unOrigen;
    }
    public String getNombre(){
        return nombre;
    }
    public String getBio(){
        return bio;
    }
    public String getOrigen(){
        return origen;
    }
    @Override
    public String toString(){
    String aux=nombre+" es de "+ origen+". Biografia: "+bio;
    return aux;
    }
}