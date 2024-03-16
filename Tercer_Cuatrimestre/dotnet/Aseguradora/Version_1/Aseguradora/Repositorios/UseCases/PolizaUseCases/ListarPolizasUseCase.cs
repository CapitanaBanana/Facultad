namespace Repositorios;
using Aplicacion;

public class ListarPolizasUseCase
{
    private readonly IRepositorioPoliza _repo;
    public ListarPolizasUseCase(IRepositorioPoliza repo)
    {
        _repo = repo;
    }
    public List<Poliza> Ejecutar()
    {
        return _repo.ListarPolizasUseCase();
    }
}