/* 1. Escriba tres métodos de clase (static) que reciban por parámetro dos números
enteros (tipo int) a y b e impriman todos los números enteros comprendidos entre a; b
(inclusive), uno por cada línea en la salida estándar. Para ello, dentro de una nueva clase
escriba un método por cada uno de los siguientes incisos:
a. Que realice lo pedido con un for.
b. Que realice lo pedido con un while.
c. Que realice lo pedido sin utilizar estructuras de control iterativas (for, while, do
while).
Por último, escriba en el método de clase main el llamado a cada uno de los métodos creados,
con valores de ejemplo.  */
package Ej_1;

public class Ej_1 {
    public static void main(String[] args) {
        recorrido_for(5, 10);
        System.out.println();
        recorrido_while(5, 10);
        System.out.println();
        recorrido(5, 10);
        System.out.println();
    }
    public static void recorrido_for(int a,int b){
        for (int i=a; i<=b; i++){
            System.out.println(i);
        }
    }
    public static void recorrido_while(int a,int b){
        while (a<=b){
            System.out.println(a);
            a++;
        }
    }
    public static void recorrido(int a,int b){
        if (a<=b){
            System.out.println(a);
            recorrido(a+1, b);
        }
    }
}
