namespace Aplicacion;
public interface IRepositorioPoliza
{
    void AgregarPolizaUseCase(Poliza p);
    void ModificarPolizaUseCase(int id);
    void EliminarPolizaUseCase(int id);
    List<Poliza> ListarPolizasUseCase();
}
