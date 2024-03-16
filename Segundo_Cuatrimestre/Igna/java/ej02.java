package tema2;

import PaqueteLectura.Lector;

/*
 * @author josem
 */
public class ej_igna {

    /* @param args the command line arguments
     */
    public static void main(String[] args) {
       Persona [] personas = new Persona[5];
       int i,e=1,dni,p=0;
        while(e!=0 && p<5){
                System.out.println("Ingrese Nombre");
                    String n=Lector.leerString();
                System.out.println("Ingrese DNI");
                    dni=Lector.leerInt();
                System.out.println("Ingrese Edad");
                    e=Lector.leerInt();
                if (e!=0){
                    personas [p] = new Persona(n,dni,e);
                    p++;
                }
        } 
        int pmin=0,cant=0,min=9999;
        for (i=0;i<p;i++){
          if(personas [i].getEdad()>65){
            cant=cant+1;
          }
          if(personas [i].getDNI()<min){
            min=personas[i].getDNI();
            pmin = i;
            }
        }
        System.out.println("La cantidad de personas con edad mayores a 65 son: "+cant);
        System.out.println("La persona con menor DNI: "+ personas[pmin].toString());
}
}
