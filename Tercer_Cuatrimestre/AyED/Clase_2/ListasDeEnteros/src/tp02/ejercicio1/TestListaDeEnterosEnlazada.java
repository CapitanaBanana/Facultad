package tp02.ejercicio1;
import java.util.Scanner;
public class TestListaDeEnterosEnlazada {
    public static void main(String[] args) {
        ListaDeEnterosEnlazada lista= new ListaDeEnterosEnlazada();
        Scanner s= new Scanner(System.in);
        System.out.println("Ingrese numeritos: ");
        int num= s.nextInt();
        while (num!=0) {
            lista.agregarFinal(num);
            num= s.nextInt();
        }
        imprimir(lista);
    }

    private static void imprimir(ListaDeEnterosEnlazada lista){
        System.out.println("Lista: ");
        lista.comenzar();
        while (!lista.fin())
            System.out.println(lista.proximo());
    }
}
