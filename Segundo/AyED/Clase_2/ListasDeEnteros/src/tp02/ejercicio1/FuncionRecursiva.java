package tp02.ejercicio1;
import java.util.Scanner;
public class FuncionRecursiva {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        System.out.println("Ingrese un numero: ");
        int num= s.nextInt();
        ListaDeEnterosEnlazada lista= new ListaDeEnterosEnlazada();
        funcion(num,lista);
        imprimir(lista);
    }
    static void funcion(int n, ListaDeEnterosEnlazada lista){
        lista.agregarFinal(n);
        if (n!=1){
            if(n%2==1)
                n=3*n+1;
            else
                n=n/2;
                funcion(n, lista);
    }
    }
    private static void imprimir(ListaDeEnterosEnlazada lista){
        System.out.println("Lista: ");
        lista.comenzar();
        while (!lista.fin())
            System.out.println(lista.proximo());
    }
}
