/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema3;

/**
 *
 * @author josem
 */
public class Triangulo {
    private double a,b,c;
    private String relleno;
    private String linea;
    
    public Triangulo(double ladoA,double ladoB, double ladoC,String colorRelleno,String colorLinea){
        a=ladoA;
        b=ladoB;
        c=ladoC;
        relleno=colorRelleno;
        linea=colorLinea;
    }
    public void setLados(double ladoA,double ladoB, double ladoC){
        a=ladoA;
        b=ladoB;
        c=ladoC;   
    }
    public void setRelleno(String colorRelleno){
        relleno=colorRelleno;
    }
    public void setLinea(String colorLinea){
        linea=colorLinea;
    }
    public String getLados(){
        String aux= "A: "+a+ "B: "+b+"C: "+c;
        return aux;        
    }
    public String getLinea(){
        return linea;
    }
    public String getRelleno(){
        return relleno;
    }
    public double calcularPerimetro(){
        double per= a+b+c;
        return per;
    }
    public double calcularArea(){
        double s= (this.a+this.b+this.c)/2;
        double area= Math.sqrt(s*(s-a)*(s-b)*(s-c));
        return area;
    }
}