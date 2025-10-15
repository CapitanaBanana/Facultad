import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanMapper {
    public static void guardar(Object o) throws IOException {
        Class<?> clase = o.getClass();
        Archivo aArchivo = clase.getAnnotation(Archivo.class);
        String nombre;
        if (aArchivo != null){
            nombre=aArchivo.nombre();
        }
        else{
            nombre= clase.getSimpleName();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre))) {
            writer.write("<nombreClase>" + clase.getSimpleName()+ "</nombreClase>\n");
            for (Field field : clase.getDeclaredFields()) {
                // 3. Verificar si tiene @AlmacenarAtributo
                if (field.isAnnotationPresent(AlmacenarAtributo.class)) {
                    field.setAccessible(true); // acceso aunque sea private
                    Object valor = field.get(o);
                    // 📌 4. Guardar nombre del atributo y valor
                    writer.write("<nombreAtributo>" + field.getName() + "</nombreAtributo>\n");
                    writer.write("<nombreValor>" + valor + "</nombreValor>\n");
                }
            }

    } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }}
