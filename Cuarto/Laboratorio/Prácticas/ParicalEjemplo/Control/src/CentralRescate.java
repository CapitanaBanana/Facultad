import java.util.*;

public class CentralRescate {
    private Set<Robot> lista = new TreeSet<Robot>();
    public void registrarRobot(Robot r){
        lista.add(r);
    }
    public Set<Robot> obtenerRobots(){
        return this.lista;
    }
    public Robot buscarPorNombre(String nombre){
        return lista.stream().filter(r-> r.getNombre().equals(nombre)).findAny().orElse(null);
    }
    public Robot TopBateria(){
        return lista.stream().max(Comparator.comparingInt(Robot::getNivelBateria)).orElse(null);
    }
    // si solamente pongo excepcion, se tapa el tipado de la excepción!!
    public void iniciarMision(Robot r) throws RobotNoDisponibleException, BateriaBajaException{
        if(r==null){
            throw new RobotNoDisponibleException();
        }
        if (r.getNivelBateria()<20){
            throw new BateriaBajaException();
        }
    }
    public void iniciarMisiones(){
        for(Robot r:lista){
            try {
                iniciarMision(r);
                //tratar de catchear especifico.
            } catch (RobotNoDisponibleException | BateriaBajaException e) {
                System.err.println("No se pudo iniciar misión en " + r.getNombre() + ": " + e.getMessage());
        }
    }
    }

}
