namespace Repositorios;
using Aplicacion;
public class ModificarVehiculoUseCase
{
    private readonly IRepositorioVehiculo _repo;
    public ModificarVehiculoUseCase(IRepositorioVehiculo repo)
    {
        _repo = repo;
    }
    public void Ejecutar(int id)
    {
        _repo.ModificarVehiculoUseCase(id);
    }
}