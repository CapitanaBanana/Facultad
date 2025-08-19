namespace Aplicacion;
public interface IRepositorioVehiculo
{
    void AgregarVehiculoUseCase(Vehiculo v);
    void ModificarVehiculoUseCase(int id);
    void EliminarVehiculoUseCase(int id);
}