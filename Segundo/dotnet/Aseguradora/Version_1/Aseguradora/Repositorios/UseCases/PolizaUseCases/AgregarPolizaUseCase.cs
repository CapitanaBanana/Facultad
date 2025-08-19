namespace Repositorios;
using Aplicacion;
public class AgregarPolizaUseCase
{
    private readonly IRepositorioPoliza _repo;
    public AgregarPolizaUseCase(IRepositorioPoliza repo)
    {
        _repo = repo;
    }
    public void Ejecutar(Poliza p)
    {
        _repo.AgregarPolizaUseCase(p);
    }
}