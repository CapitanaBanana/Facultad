namespace Repositorios;
using Aplicacion;

public class ListarTitularesUseCase
{
    private readonly IRepositorioTitular _repo;
    public ListarTitularesUseCase(IRepositorioTitular repo)
    {
        _repo = repo;
    }
    public List<Titular> Ejecutar()
    {
        return _repo.ListarTitularesUseCase();
    }
}