namespace Repositorios;
using Aplicacion;
public class AgregarVehiculoUseCase
{
    private readonly IRepositorioVehiculo _repo;
    public AgregarVehiculoUseCase(IRepositorioVehiculo repo)
    {
        _repo = repo;
    }
    public void Ejecutar(Vehiculo v)
    {
        _repo.AgregarVehiculoUseCase(v);
    }
}