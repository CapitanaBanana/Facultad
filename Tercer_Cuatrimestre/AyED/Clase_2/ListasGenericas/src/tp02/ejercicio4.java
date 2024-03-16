package tp02;
import java.io.Console;
import java.util.Scanner;
import tp02.ejercicio3.PilaGenerica;

public class ejercicio4 {
    public static void main(String[] args) {
        PilaGenerica<Character> pila= new PilaGenerica<Character>();
        Scanner S= new Scanner(System.in);
        System.out.println("Ingrese una cadena para ver si está balanceada");
        String cadena= S.nextLine();
        if (analizarCadena(pila,cadena))
            System.out.println("Esta balanceada :)");
        else 
            System.out.println(":(");
    }
    
    private static boolean analizarCadena(PilaGenerica<Character> pila, String cadena){
        boolean ok=true;
        for (int i=0; i<cadena.length();i++){
            char c= cadena.charAt(i);
            if (c=='('|c=='{'|c=='['){
                pila.apilar(c);
            }    
            else if(c==']'|c==')'|c=='}'){
                Character comp= pila.desapilar();
                if (c==']'){
                    if (comp!='[')
                        ok=false;
                }
                else if (c==')'){
                    if (comp!='(')
                        ok=false;
                }
                else if (comp!='{')
                        ok=false;
            }
            if (!ok) break;
        }
        if (ok){
            if (!(pila.esVacia()))
                ok=false;
        }
        return ok;
    }
}
