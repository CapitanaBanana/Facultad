namespace Repositorios;
using Aplicacion;
public class ListarTitularesConVehiculo
{
    private readonly IRepositorioTitular _repo;
    public ListarTitularesConVehiculo(IRepositorioTitular repo)
    {
        _repo = repo;
    }
    public void Ejecutar()
    {
        _repo.ListarTitularesConVehiculo();
    }
}