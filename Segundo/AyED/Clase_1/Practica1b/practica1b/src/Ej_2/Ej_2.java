/* 2. Escriba un método de clase que dado un número n devuelva un nuevo arreglo de
tamaño n con los n primeros múltiplos enteros de n mayores o iguales que 1.
Ejemplo: f(5) = [5; 10; 15; 20; 25]; f(k) = {nk/k : 1..k}
Agregue al programa la posibilidad de probar con distintos valores de n ingresándolos por
teclado, mediante el uso de System.in. La clase Scanner permite leer de forma sencilla
valores de entrada.*/
package Ej_2;
import java.util.Scanner;

public class Ej_2 {

    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int num=s.nextInt();
        int[] vector= new int[num];
        llenar_vector(vector,num);
        for (int i=0; i<num; i++){
            System.out.println((i+1)+": "+ vector[i]);
        }

    }
    public static void llenar_vector(int[] vector, int num){
        for (int i=0, j=num;i<num; i++, j+=num){
            vector[i]=j;
        }
    } 
}
