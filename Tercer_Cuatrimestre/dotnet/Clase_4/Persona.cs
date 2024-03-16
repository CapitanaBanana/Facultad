namespace Clase_4;
class Persona
{
    private string? _nombre;
    private int? _documento;
    private int? _edad;

    public Persona(int edad,int dni, string nom){
    _nombre=nom;
    _documento=dni;
    _edad=edad;
}
    public string GetNombre()=> _nombre;
    public string GetDescripcion()=>
        $"Persona: {_nombre} {_documento} {_edad}";   
    public bool EsMayorQue(Persona p){
        if (_edad>p._edad)
            return true;
        else
            return false;
    }
}
