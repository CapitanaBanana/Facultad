//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static java.lang.reflect.AccessibleObject.setAccessible;

public class Main {
    public static void main(String[] args) {
        Bean bean= new Bean();
        Class<?> clazz = bean.getClass();

        try {
            // está bueno hacer loo de file writer con try catch
            FileWriter myWriter = new FileWriter(clazz.getAnnotation(Archivo.class).nombre());
            myWriter.write("<nombreClase>" + bean.getClass().getSimpleName()+"<nombreClase> \n");


            for(Field f: bean.getClass().getDeclaredFields()){
                if(f.isAnnotationPresent(AlmacenarAtributo.class)){
                    AlmacenarAtributo a= f.getAnnotation(AlmacenarAtributo.class);
                    f.setAccessible(true);//sin esto, como es privado no puedo acceder
                    myWriter.write("<nombreAtributo>" + f.getName()+"<nombreAtributo>\n" );
                    myWriter.write("<nombreValor>" + f.get(bean)+"<nombreValor>\n" );
                }

            }
            myWriter.close();  // must close manually
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
//🧠 ¿Por qué f.get(bean)?
//
//Porque f.get(objeto) devuelve el valor de ese atributo en esa instancia del objeto.
//
//f → representa el campo (por ejemplo "valor2")
//
//bean → es la instancia real del objeto Bean
//
//f.get(bean) → accede al valor del atributo en ese objeto
//
//Si pusieras otra cosa, o null, o no pasás nada → no sabe de qué objeto sacar el valor.