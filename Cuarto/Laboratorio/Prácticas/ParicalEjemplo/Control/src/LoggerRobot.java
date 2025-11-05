import java.io.FileWriter;
import java.io.IOException;
import java.lang.classfile.Attribute;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class LoggerRobot {
    public void registrarEstado(Robot robot) throws IOException {
        Class<?> clazz= Robot.class;
        String nombre= clazz.getAnnotation(LogSensor.class).nombreArchivo();
        try (FileWriter writer= new FileWriter(nombre,true)) {
            //get fields me trae solo cosas publicas, declared fields tb privadas.
            for(Field f: Robot.class.getDeclaredFields()){
                if(f.isAnnotationPresent(LogAtributo.class)){
                    writer.write("<Nombre atributo>" + f.getName() + "<Nombre atributo>" );
                    f.setAccessible(true);
                    writer.write("<Valor atributo>" + f.get(robot) + "<Valor atributo>" );
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }




    }

}
