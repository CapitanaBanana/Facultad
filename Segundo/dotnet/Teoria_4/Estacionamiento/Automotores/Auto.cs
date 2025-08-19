namespace Automotores;
public class Auto
{
    private string? _marca;
    private int _modelo;
    public Auto(string marca, int modelo){
        _marca=marca;
        _modelo=modelo;
    }
    public Auto(){
        _marca="fiat";
        _modelo= DateTime.Now.Year;
    }
    public string GetDescripcion() =>
    $"Auto {_marca} {_modelo}";
    public Auto(string marca): this(){
        _marca=marca;
    }
}

