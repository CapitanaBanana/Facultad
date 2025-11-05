import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

public class Contenedor implements HttpHandler {
    private String direccion;
    private String archivo;
    private int puerto;

    public void run() throws IOException {
        ClaseCualquiera clase= new ClaseCualquiera();
        Class<?> clazz = clase.getClass();
        puerto= clazz.getAnnotation(Servidor.class).puerto();
        direccion= clazz.getAnnotation(Servidor.class).direccion();
        archivo= clazz.getAnnotation(Servidor.class).archivo();
        HttpServer server = HttpServer.create(new InetSocketAddress(puerto),0);
//        server.createContext("/applications/myapp", this);//le podría pasar otra clase
        server.createContext("/", this);//le podría pasar otra clase

        server.start(); //inicia sv.
        System.out.println("Servidor escuchando en http://" + direccion + ":" + puerto);

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getRemoteAddress();
        try (FileWriter myWriter = new FileWriter(archivo, true)) { // true para no sobrescribir
            myWriter.write("<Fecha>" + LocalDateTime.now() + "</Fecha>\n");
            myWriter.write("<IpCliente>" + exchange.getRemoteAddress() + "</IpCliente>\n");
        }

        ClaseCualquiera clase= new ClaseCualquiera();

        for(Method m: clase.getClass().getMethods()){

            if(m.isAnnotationPresent(Invocar.class)){
                try {
                    m.invoke(clase);


                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
//        String respuesta = "<html><body><h1>Invocación exitosa</h1></body></html>";
//        exchange.getResponseHeaders().add("Content-Type", "text/html");
//        exchange.sendResponseHeaders(200, respuesta.length());
//        exchange.getResponseBody().write(respuesta.getBytes());
//        exchange.getResponseBody().close();
        //esto de aca arriba me daba error.
        // construir respuesta
        String respuesta = "<html><body><h1>Métodos @Invocar ejecutados correctamente</h1></body></html>";
        exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
        byte[] bytes = respuesta.getBytes();
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }

    }
}
