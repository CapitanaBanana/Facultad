/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

/**
 *
 * @author josem
 */
public class Circulo {
    private double radio;
    private String relleno;
    private String linea;
    
    public Circulo(double unRadio, String unRelleno, String unaLinea){
        this.radio= unRadio;
        this.relleno= unRelleno;
        this.linea=unaLinea;
    }
    public Circulo(){}
    
    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public String getRelleno() {
        return relleno;
    }

    public void setRelleno(String relleno) {
        this.relleno = relleno;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
    public double calcularPerimetro(){
        double aux;
        aux= 2* Math.PI* this.radio;
        return aux;
    }     
    public double calcularArea(){
        double aux;
        aux= Math.PI* this.radio* this.radio;
        return aux;
    }      
}
