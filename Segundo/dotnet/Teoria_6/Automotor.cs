namespace Teoria_6;

class Automotor {
public string Marca = "";
public int Modelo;
public Automotor(string marca, int modelo)
{
Marca = marca;
Modelo = modelo;
}
public virtual void Imprimir()
=> Console.WriteLine($"{Marca} {Modelo}");
}