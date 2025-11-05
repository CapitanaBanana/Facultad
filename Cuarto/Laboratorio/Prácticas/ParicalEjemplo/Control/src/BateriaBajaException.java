public class BateriaBajaException extends Exception{
    public BateriaBajaException() {
        super("Batería insuficiente (<20%)");
    }
}
public class RobotNoDisponibleException extends Exception{
    public RobotNoDisponibleException() {
        super("El robot no está disponible");
    }
}