package tp02.ejercicio1;
import java.io.Console;
import java.util.Scanner;
public class ImprimirRecursivo {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        ListaDeEnterosEnlazada lista= new ListaDeEnterosEnlazada();
        int num=-1;
        System.out.println("Ingrese numeritos: ");
        num= s.nextInt();
        while (num !=0){
            lista.agregarFinal(num);
            num= s.nextInt();
        }
        lista.comenzar();
        System.out.println("Lista: ");
        imprimir(lista, 1);
        
    }
    static void imprimir (ListaDeEnterosEnlazada lista, int pos){
        if(pos==lista.tamanio()){
            System.out.println(lista.elemento(pos));
        }
        else {
            imprimir(lista, pos+1);
            System.out.println(lista.elemento(pos));
        }
    }
}
