namespace Aplicacion;
public class Siniestro
{
    public int IDPoliza { get; set; }
    public int ID { get; set; } = -1;
    public string? Direccion { get; set; } = "Direccion no especificada";
    public string? Descripcion { get; set; } = "Descripción no brindada";
    public DateTime? FechaDeIngreso { get; }
    public DateTime? FechaDeOcurrencia { get; set; }
    public Siniestro(int idpoliza, int idsiniestro, DateTime fechaDeOcurrencia, string descripcion, string direccion)
    {
        IDPoliza = idpoliza;
        Direccion = direccion;
        Descripcion = descripcion;
        FechaDeOcurrencia = fechaDeOcurrencia;
        FechaDeIngreso = new DateTime();
    }
    public override string ToString()
    {
        string aux = $" | {Descripcion} | {Direccion} | {FechaDeOcurrencia} | {FechaDeIngreso} | {IDPoliza}";
        return (aux);
    }
}