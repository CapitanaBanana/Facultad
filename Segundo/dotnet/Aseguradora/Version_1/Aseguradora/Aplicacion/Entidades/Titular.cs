namespace Aplicacion;
public class Titular : Persona
{
    public string? Correo { get; set; } = "correo no especificado";
    public string? Direccion { get; set; } = "Dirección no especificada";

    public Titular(string nombre, string apellido, int dni) : base(nombre, apellido, dni) { }
    public Titular(string nombre, string apellido, int dni, long telefono, string correo, string direccion) : base(nombre, apellido, dni, telefono)
    {
        Correo = correo;
        Direccion = direccion;
    }
    public override string ToString()
    {
        string aux = base.ToString() + $" | {Correo} | {Direccion}";
        return (aux);
    }
}