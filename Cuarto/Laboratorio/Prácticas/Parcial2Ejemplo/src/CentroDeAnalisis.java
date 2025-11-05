import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CentroDeAnalisis {
    public void guardar(Object o) throws InvocationTargetException, IllegalAccessException {
        try (FileWriter myWriter = new FileWriter("archivo", true)) { // true para no sobrescribir

        for(Method m: o.getClass().getMethods()){
            if(m.isAnnotationPresent(Analizador.class)){
                Object res= m.invoke(o);
                myWriter.write("<Resultado>" + res.toString() + "Resultado");

            }
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
