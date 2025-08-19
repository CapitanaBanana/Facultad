namespace Teoria_11;

class ProveedorServicios
{
public ILogger GetLogger()
=> new LoggerConsola();
public IServicioX GetServicioX()
=> new ServicioX(this.GetLogger());
}