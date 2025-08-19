using Clase_5;
class ListaDePersonas{
    private List<Persona> _lista= new List<Persona>();

    public void Agregar(Persona p)
    {
        _lista.Add(p);
    }
    public Persona this[int dni]{
        get{
            Persona encontrada = _lista.FirstOrDefault(p => p.DNI == dni);
            if (encontrada == null)
            {
                encontrada= null;
            }
            return encontrada;
        }
    }
    public List<String> this[char c]{
        get{
            List<String> lista= new List<String>();
            foreach(Persona p in _lista){
                if(p.Nombre[0]==c)
                    lista.Add(p.Nombre);
            }
            return lista;
            }
    }

}