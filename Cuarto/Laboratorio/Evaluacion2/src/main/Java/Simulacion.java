import java.util.List;

public class Simulacion {
    public List<Auto> autos;
    public int capacidadPista;
    private static Simulacion instancia;

    public static Simulacion prepararSimulacion(Circuito unCircuito,int cantidadAutos, List<String> pilotos){
        instancia= new Simulacion();
        instancia.autos= unCircuito.iniciarCircuito(cantidadAutos,pilotos);
        return instancia;
    }
    private Simulacion(){};




}