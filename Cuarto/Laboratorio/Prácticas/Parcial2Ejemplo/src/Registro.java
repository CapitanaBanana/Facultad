import java.util.ArrayList;
import java.util.List;

public class Registro<T> {

    private T l;
    private List<T> lista = new ArrayList<>();

    public void almacenarLectura(T lectura){
        lista.add(lectura);
    }
    public List<T> getLecturas(){
        return this.lista;
    }
}