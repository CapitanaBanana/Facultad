namespace Teoria_7;

class Empleado : Persona, IImprimible
{
public Empleado(string nombre)
=> Nombre = nombre;
public void Imprimir()
=> Console.WriteLine($"Soy el empleado {Nombre}");
}