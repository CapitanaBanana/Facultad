namespace Teoria_6;

class Colectivo : Automotor
{
public int CantPasajeros;

public override void Imprimir()
=> Console.WriteLine($"{Marca} {Modelo} ({CantPasajeros} pasajeros)");
}