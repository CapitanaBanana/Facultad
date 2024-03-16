/*5. Dado un arreglo de valores tipo int se desea calcular el valor máximo, mínimo, y
promedio en un único método. Escriba tres métodos de clase, donde respectivamente:
a. Devuelva lo pedido por el mecanismo de retorno de un método en Java ("return").
b. Devuelva lo pedido interactuando con algún parámetro (el parámetro no puede ser de
tipo arreglo).
c. Devuelva lo pedido sin usar parámetros ni la sentencia "return". */
package Ej_5;
import java.util.Random;
public class Ej_5 {
    public static void main(String[] args) {
        Random r = new Random();
        int[] vec= new int[10];
        for (int i=0; i<10;i++){
            vec[i] = r.nextInt(101);
        }
        //retornar un vector
        int[] resultados1= retornar1(vec);
        System.out.println("Devolución por medio de return:");
        System.out.println("Máximo: " + resultados1[0]);
        System.out.println("Mínimo: " + resultados1[1]);
        System.out.println("Promedio: " + resultados1[2]);

        //objeto como parámetro
        retornar resultados2= new retornar();
        retornar2(vec,resultados2);
        System.out.println("Devolución por medio de objeto:");
        System.out.println("Máximo: " + resultados2.getMax());
        System.out.println("Mínimo: " + resultados2.getMin());
        System.out.println("Promedio: " + resultados2.getProm());
        //sin return ni parámetro
        retornar3(vec);
        System.out.println("Devolución sin return ni objeto:");
        System.out.println("Máximo: " + retornar3.getMax());
        System.out.println("Mínimo: " + retornar3.getMin());
        System.out.println("Promedio: " + retornar3.getProm());
    }
    public static int[] retornar1(int[]vec){
        int suma=0;
        int max= -1;
        int min=999;
        for (int i=0; i< vec.length; i++){
            suma+= vec[i];
            if (vec[i]> max){
                max= vec[i];
            }
            if (vec[i]< min){
                min=vec[i];
            }
        }
        return new int[]{max, min, (suma/vec.length)};
    }
    public static void retornar2(int[]vec, retornar res){
        int suma=0;
        for (int i=0; i< vec.length; i++){
            suma+= vec[i];
            if (vec[i]> res.getMax()){
                res.setMax(vec[i]);
            }
            if (vec[i]< res.getMin()){
                res.setMin(vec[i]);
            }
        }
        res.setProm(suma/vec.length);
    }
    public static void retornar3(int[]vec){
        int suma=0;
        for (int i=0; i< vec.length; i++){
            suma+= vec[i];
            if (vec[i]> retornar3.getMax()){
                retornar3.setMax(vec[i]);
            }
            if (vec[i]< retornar3.getMin()){
                retornar3.setMin(vec[i]);
            }
        }
        retornar3.setProm(suma/vec.length);
    }
}
