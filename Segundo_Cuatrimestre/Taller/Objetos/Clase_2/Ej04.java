package tema2;

/**
 *
 * @author josem
 */
import PaqueteLectura.Lector;
import PaqueteLectura.GeneradorAleatorio;
public class Ej04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int dias=2, per=3, DNI, edad, i=0,j=0;
        String nom;
        
        Persona Turnos[][]=new Persona[per][dias];
        System.out.print("Nombre: ");
        nom= Lector.leerString();
        while ((!(nom.equalsIgnoreCase("ZZZ")))&& (!(j==dias && i==0))){
            edad= GeneradorAleatorio.generarInt(85);
            System.out.println("Edad: "+edad);
            DNI= GeneradorAleatorio.generarInt(99999);
            System.out.println("DNI: "+DNI);
            System.out.println("Dia: "+j+" per: "+i);
            Turnos [i][j]=new Persona(nom,edad,DNI);
            if (i<per-1) i++;
            else {i=0;
                j++;
            }
            System.out.print("Nombre: ");
            nom= Lector.leerString();
        }
        for(int k=0; k<=j; k++){
            if (k!=j)
             for(int l=0; l<per; l++)
                System.out.print(Turnos[l][k].getNombre()+" ");
            else 
                for(int l=0; l<i; l++)
                    System.out.print(Turnos[l][k].getNombre()+" ");
            
            System.out.println();
        }
    }
}
