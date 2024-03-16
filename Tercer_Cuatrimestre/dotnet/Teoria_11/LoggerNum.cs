namespace Teoria_11;
class LoggerNum : ILogger
{
private int _n;
public void Log(string mensaje)
{
Console.WriteLine($"{++_n}: {DateTime.Now:hh:mm:ss:fff} {mensaje}");
}
}