namespace Repositorios;
using Aplicacion;
public class ModificarPolizaUseCase
{
    private readonly IRepositorioPoliza _repo;
    public ModificarPolizaUseCase(IRepositorioPoliza repo)
    {
        _repo = repo;
    }
    public void Ejecutar(int id)
    {
        _repo.ModificarPolizaUseCase(id);
    }
}