namespace Aplicacion;
public class Poliza
{
    public int ID { get; set; } = -1;
    public int IDVehiculo { get; set; }
    public float ValorAsegurado { get; set; }
    public string? Franquicia { get; set; }
    public string? TCobertura { get; set; }
    public DateTime InicioVigencia { get; set; }
    public DateTime FinVigencia { get; set; }

    public Poliza(int idv, float valor, string franquicia, string cobertura, DateTime inivigencia, DateTime finvigencia)
    {
        IDVehiculo = idv;
        ValorAsegurado = valor;
        Franquicia = franquicia;
        TCobertura = cobertura;
        InicioVigencia = inivigencia;
        FinVigencia = finvigencia;

    }
    public override string ToString()
    {
        string aux = $" {IDVehiculo} | {ValorAsegurado}  | {TCobertura}  | {InicioVigencia:dd/MM/yyyy}  | {FinVigencia:dd/MM/yyyy}";
        return (aux);
    }
}