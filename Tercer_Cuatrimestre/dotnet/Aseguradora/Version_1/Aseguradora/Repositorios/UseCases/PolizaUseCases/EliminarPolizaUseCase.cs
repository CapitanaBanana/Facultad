namespace Repositorios;
using Aplicacion;
public class EliminarPolizaUseCase
{
    private readonly IRepositorioPoliza _repo;
    public EliminarPolizaUseCase(IRepositorioPoliza repo)
    {
        _repo = repo;
    }
    public void Ejecutar(int id)
    {
        _repo.EliminarPolizaUseCase(id);
    }
}