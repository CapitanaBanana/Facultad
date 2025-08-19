namespace Aplicacion;
public interface IRepositorioTitular
{
    void AgregarTitularUseCase(Titular t);
    List<Titular> ListarTitularesUseCase();
    void ModificarTitularUseCase(int id);
    void EliminarTitularUseCase(int id);
    void ListarTitularesConVehiculo();
}
