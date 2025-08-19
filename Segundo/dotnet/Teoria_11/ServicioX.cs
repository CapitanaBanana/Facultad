namespace Teoria_11;
public class ServicioX : IServicioX
{
   private readonly ILogger _logger;
   public ServicioX(ILogger logger)
   {
      this._logger = logger;
   }
   public void Ejecutar()
   {
      _logger.Log("ServicioX comenzando su ejecución");
      for (int i = 1; i <= 100_000_000; i++) ; //consumo tiempo simulando ejecución
      _logger.Log("ServicioX ejecución finalizada");
   }
}
