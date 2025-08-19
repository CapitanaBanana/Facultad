/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema2;

/**
 *
 * @author josem
 */
import PaqueteLectura.Lector;
import PaqueteLectura.GeneradorAleatorio;
public class Ej05 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int df=15;
       int dl=0;
       String loc, vis;
       int gloc=0, gvis=0, ganoriver=0,golesb=0;
       Partido partidos[]=new Partido[df];
       System.out.print("Visitante: ");
       vis= Lector.leerString();
       while ((dl<df) && (!vis.equalsIgnoreCase("zzz"))){
           gvis= GeneradorAleatorio.generarInt(10);
           System.out.println("Goles visitante: "+ gvis);
           System.out.print("Local: ");
           loc= Lector.leerString();
           gvis= GeneradorAleatorio.generarInt(10);
           System.out.println("Goles Local: "+ gloc);
           
           partidos[dl]=new Partido(loc, vis, gloc, gvis);
           dl++;
           
           System.out.print("Visitante: ");
           vis= Lector.leerString();
       }
       for(int i=0; i<dl; i++){
           System.out.println(partidos[i].getLocal()+" "+ partidos[i].getGolesLocal()+" VS "+ partidos[i].getVisitante()+" "+ partidos[i].getGolesVisitante());
           if ((partidos[i].getLocal().equalsIgnoreCase("river"))|| partidos[i].getVisitante().equalsIgnoreCase("river")){
               if (partidos[i].getGanador().equalsIgnoreCase("river"))
                   ganoriver++;
           }
           if (partidos[i].getLocal().equalsIgnoreCase("boca"))
               golesb= golesb+ partidos[i].getGolesLocal();
       }
       System.out.println("River gano "+ganoriver+" partidos.");
       System.out.println("Boca hizo "+golesb+" goles de visitante.");
    }
    
}
