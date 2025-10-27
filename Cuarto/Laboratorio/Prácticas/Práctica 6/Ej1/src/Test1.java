class Exception1 extends Exception{}
class Exception2 extends Exception1{}
public class Test1 {
    public static void main(String[] args) {
        try {
            throw new Exception2();
        }
     catch( Exception2 e2) {
        System.out.println("Se capturó la Excepción2");
    }
        catch(Exception1 e1) {
            System.out.println("Se capturó la Excepción1");

    }}
}