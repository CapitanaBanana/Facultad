/*Cuadrado c = new Cuadrado();
c.SetLado(2.5);
Console.WriteLine("Lado: {0} área: {1}", c.GetLado(), c.GetArea());
class Cuadrado
{
private double _lado;
public double _area;
public double Lado {
get {
return _lado;
}
set {
_lado = value;
}
}
public double Area {
get {
return _lado * _lado;
}
}
}*/
Familia f = new Familia();
f.Padre = new Persona("Juan", 50);
f.Madre = new Persona("María", 40);
f.Hijo = new Persona("José", 15);
for (int i = 0; i < 3; i++)
{
f[i]?.Imprimir();
}
class Persona
{
public int Edad { get; }
public string Nombre { get; }
public Persona(string nombre, int edad)
{
Nombre = nombre;
Edad = edad;
}
public void Imprimir() =>
Console.WriteLine($"{Nombre} ({Edad})");
}
class Familia
{
public Persona? Padre { get; set; }
public Persona? Madre {get; set; }
public Persona? Hijo { get; set; }
public Persona? this[int i]
{
get
{
if (i == 0) return Padre;
else if (i == 1) return Madre;
else if (i == 2) return Hijo;
else return null;
}
set {
if (
i ==
0) Padre = value
;

else if (
i ==
1) Madre = value
;

else if (
i ==
2) Hijo = value; }
}
}