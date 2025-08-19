/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema1;

import PaqueteLectura.GeneradorAleatorio;
import PaqueteLectura.Lector;

public class Ej03Matrices {

    public static void main(String[] args) {
        int tam=5;
	GeneradorAleatorio.iniciar();
	int[][] nros = new int[tam][tam];
        int[] sumas = new int [tam];
        int i,j,n,suma=0;
        for(i=0; i<tam;i++)
            for (j=0; j<tam; j++)
                nros[i][j]=GeneradorAleatorio.generarInt(30);
        //Paso 3. definir la matriz de enteros de 5x5 e iniciarla con nros. aleatorios 
       
        //Paso 4. mostrar el contenido de la matriz en consola
        for(i=0; i<tam;i++){
            for (j=0; j<tam; j++)
                System.out.print(nros[i][j]+" ");
            System.out.println();
        }
        //Paso 5. calcular e informar la suma de los elementos de la fila 1
        for (i=0; i<tam;i++){
            suma=suma+ nros[0][i];
        }
        System.out.println("La suma de la fila 1 es: "+suma);
        
            
        //Paso 6. generar un vector de 5 posiciones donde cada posición j contiene la suma de los elementos de la columna j de la matriz. 
        for(i=0; i<tam;i++){
            sumas[i]=0;
            for (j=0; j<tam; j++){
                sumas[i]= (sumas[i]+nros[j][i]);
            }
        }
        System.out.println("Vector con la suma de las columnas:");
        for(i=0; i<tam;i++)
            System.out.print(sumas[i]+" ");
        System.out.println();
        
        //Paso 7. lea un valor entero e indique si se encuentra o no en la matriz. En caso de encontrarse indique su ubicación (fila y columna)
        //   y en caso contrario imprima "No se encontró el elemento".
        System.out.println("Ingrese un valor a buscar: ");
        n=Lector.leerInt();
        boolean esta=false;
        i=0;
        j=0;
        while (i<tam && esta==false){
            while (j<tam && esta==false){
                if (nros[i][j]==n)
                    esta=true;
                j++;
            }
            if (esta== false)
                j=0;
            i++;
        }
        if (esta==true)
            System.out.println("El nro "+ n+" esta en "+i+", "+j);
        else 
            System.out.println("No se encontro el elemento");
    }
}
