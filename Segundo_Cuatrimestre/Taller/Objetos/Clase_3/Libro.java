/*
Clase Libro a la cual se agregaron constructores. 
 */
package tema3;

/**
 *
 * @author vsanz
 */
public class Libro {
   private String titulo;
   //Autor primerAutor;
   //Autor primerAutor= new Autor(); String nombreAutor,String origenAutor,String biografiaAutor
   Autor primerAutor= new Autor();
   private String editorial;
   private int añoEdicion;
   private String ISBN; 
   private double precio; 
     
    
    public Libro(  String unTitulo,  String unaEditorial, 
    int unAñoEdicion, Autor unPrimerAutor  , String unISBN, double unPrecio){
         titulo = unTitulo;
         editorial = unaEditorial; 
         añoEdicion= unAñoEdicion;
        primerAutor= unPrimerAutor;
        /*  primerAutor.setOrigen(origenAutor);
         primerAutor.setBio(biografiaAutor);
         primerAutor.setNombre(nombreAutor);*/
         ISBN =  unISBN;
         precio = unPrecio;
    }
    
    public Libro(  String unTitulo,  String unaEditorial, Autor unPrimerAutor, String unISBN){
         titulo = unTitulo;
         editorial = unaEditorial; 
         añoEdicion= 2015;
         primerAutor=unPrimerAutor;
         ISBN =  unISBN;
         precio = 100;
    }
    public Libro(  String unTitulo, String unPrimerAutor){
         titulo = unTitulo;
         primerAutor.setNombre(unPrimerAutor);
    }
    
    public Libro(){
        
    }
        
    public String getTitulo(){
        return titulo;
    }
  
    public String getEditorial(){
        return editorial;
    }
    public int getAñoEdicion(){
        return añoEdicion;
    }
  
    public String getPrimerAutor(){
        return primerAutor.getNombre();
    } 
    public String getISBN(){
        return ISBN;
    } 
    public double getPrecio(){
        return precio;
    }
   
    public void setTitulo(String unTitulo){
        titulo = unTitulo;
    }
   
    public void setEditorial(String unaEditorial){
         editorial = unaEditorial;
    }
    public void setAñoEdicion(int unAño){
         añoEdicion = unAño;
    }
   
    public void setPrimerAutor(String nombreAutor,String origenAutor,String biografiaAutor){
        primerAutor.setOrigen(origenAutor);
        primerAutor.setBio(biografiaAutor);
        primerAutor.setNombre(nombreAutor);
    } 
     public void setPrimerAutor(String nombreAutor){
        primerAutor.setNombre(nombreAutor);
    } 
    public void setISBN(String unISBN){
         ISBN=unISBN;
    } 
    public void setPrecio(double unPrecio){
         precio=unPrecio;
    }
   
    
   @Override
    public String toString(){
        String aux;
        aux= titulo + " por " + primerAutor.getNombre() + " - " + añoEdicion + " - " + " ISBN: " + ISBN;
       return ( aux);
    }
        
}