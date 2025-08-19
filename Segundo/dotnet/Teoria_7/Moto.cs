namespace Teoria_7;

class Moto : Automotor, IImprimible
{
public Moto(string marca)
=> Marca = marca;
public void Imprimir()
=> Console.WriteLine($"Soy una moto {Marca}");
}