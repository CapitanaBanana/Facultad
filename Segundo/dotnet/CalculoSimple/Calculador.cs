namespace CalculoSimple;
class Calculador
{
ILogger _logger;
public Calculador(ILogger logger)
{
_logger = logger;
}
public void Calcular(int n)
{
int resul = (n + 5) * (n + 7);
_logger.Log($"Fin de Calculo - (resul={resul})");
}
}