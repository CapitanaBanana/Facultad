namespace Repositorios;
using Aplicacion;
public class EliminarVehiculoUseCase
{
    private readonly IRepositorioVehiculo _repo;
    public EliminarVehiculoUseCase(IRepositorioVehiculo repo)
    {
        _repo = repo;
    }
    public void Ejecutar(int id)
    {
        _repo.EliminarVehiculoUseCase(id);
    }
}