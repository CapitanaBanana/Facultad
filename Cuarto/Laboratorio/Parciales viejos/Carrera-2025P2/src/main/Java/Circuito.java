import java.util.ArrayList;
import java.util.List;

@ConfiguradorSimulacion(vueltas=4, longitudPista=200, capacidadPista=5)
public class Circuito{
    private int vueltas;
    private int longitudPista;
    private int capacidadPista;
    public List<Auto> iniciarCircuito(int cantAutos, List<String> pilotos){
        //retorna una lista de autos de tamaño //cantAutos
        Class<?> clazz= this.getClass();
        vueltas=clazz.getAnnotation(ConfiguradorSimulacion.class).vueltas();
        longitudPista=clazz.getAnnotation(ConfiguradorSimulacion.class).longitudPista();
        capacidadPista=clazz.getAnnotation(ConfiguradorSimulacion.class).capacidadPista();
        List<Auto> lista=new ArrayList<>();
        for(String p:pilotos){
            Auto temp= new Auto();
            temp.setPiloto(p);
            temp.setDetallesCarrera(vueltas,longitudPista);
            lista.add(temp);
        }
        return lista;
    }

}
