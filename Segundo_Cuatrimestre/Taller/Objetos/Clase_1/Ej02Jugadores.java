
package tema1;

import PaqueteLectura.GeneradorAleatorio;
import PaqueteLectura.Lector;

public class Ej02Jugadores {

  
    public static void main(String[] args) {
        int df=5;
        double [] Alturas = new double [df];
        int i;
        double suma = 0;
        double promedio;
        int cant = 0;
        for (i=0; i<df; i++){
            System.out.println("Altura jugador "+(i+1));
            Alturas[i]= Lector.leerDouble();
            suma= suma+ Alturas[i];
        }
        promedio= suma/df; 
        System.out.println("El promedio es "+ promedio);
        
        for (i=0; i<df; i++)
            if (Alturas[i]>promedio)
                cant++;
        
        System.out.println("La cantidad de jugadores con altura por encima del promedio es "+ cant);
       
    }
    
}
