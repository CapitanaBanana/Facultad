namespace Repositorios;
using Aplicacion;
public class ModificarTitularUseCase
{
    private readonly IRepositorioTitular _repo;
    public ModificarTitularUseCase(IRepositorioTitular repo)
    {
        _repo = repo;
    }
    public void Ejecutar(int id)
    {
        _repo.ModificarTitularUseCase(id);
    }
}