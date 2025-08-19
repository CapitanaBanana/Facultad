namespace Aplicacion;
public class Vehiculo
{
    public int IDTitular { get; set; }
    public int ID { get; set; } = -1;
    public string? Dominio { get; set; } = "Dominio no especificado";
    public string? Marca { get; set; } = "Marca no especificada";
    public int? Fabricacion { get; set; }
    public Vehiculo(string dominio, string marca, int fabricacion, int idtitular)
    {
        Dominio = dominio;
        Marca = marca;
        Fabricacion = fabricacion;
        IDTitular = idtitular;
    }

    public override string ToString()
    {
        string aux = $" {Dominio} | {Marca} | {Fabricacion}";
        return (aux);
    }
}