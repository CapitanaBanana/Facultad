import org.w3c.dom.ranges.Range;

import java.util.Comparator;
@LogSensor(nombreArchivo= "RobotitoLog")
public class Robot implements Comparable<Robot>{
    @LogAtributo
    private int id;
    private String nombre;
    private int nivelBateria;
    @LogAtributo
    private TipoRobot tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public TipoRobot getTipo() {
        return tipo;
    }


    public void setTipo(TipoRobot tipo) {
        this.tipo = tipo;
    }


    @Override
    public int compareTo(Robot o) {
        return Integer.compare(this.id,o.id);
    }
}
