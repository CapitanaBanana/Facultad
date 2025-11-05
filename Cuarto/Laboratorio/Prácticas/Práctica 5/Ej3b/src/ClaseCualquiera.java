@Servidor(direccion = "127.0.0.1", puerto = 8080, archivo = "Clase Cualquiera")
public class ClaseCualquiera {
    @Invocar
    public void unMetodo(){
        System.out.println("Hola!");
    }
}
