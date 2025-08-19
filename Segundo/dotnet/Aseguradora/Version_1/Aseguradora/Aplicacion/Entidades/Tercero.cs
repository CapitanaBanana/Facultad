namespace Aplicacion;
class Tercero : Persona
{
    public string? Aseguradora { get; set; } = "Aseguradora no especificada";
    public int? Siniestro { get; } = -1;

    public Tercero(string nombre, string apellido, int dni) : base(nombre, apellido, dni) { }
    public Tercero(string nombre, string apellido, int dni, long telefono, string aseguradora, int siniestro) : base(nombre, apellido, dni, telefono)
    {
        Siniestro = siniestro;
        Aseguradora = aseguradora;
    }
    public override string ToString()
    {
        string aux = base.ToString() + $" | {Siniestro} | {Aseguradora}";
        return (aux);
    }
}