namespace Aplicacion;
public abstract class Persona
{
    public int ID { get; set; } = -1;
    public string Nombre { get; }
    public string Apellido { get; }
    public int DNI { get; }
    public long Telefono { get; set; }

    public Persona(string nombre, string apellido, int dni)
    {
        Nombre = nombre;
        Apellido = apellido;
        DNI = dni;
    }
    public Persona(string nombre, string apellido, int dni, long telefono) : this(nombre, apellido, dni)
    {
        Telefono = telefono;
    }
    public override string ToString()
    {
        string aux = ($" {Nombre} | {Apellido} | {DNI} | {Telefono}");
        return (aux);
    }
    public void setId(int id)
    {
        ID = id;
    }
}