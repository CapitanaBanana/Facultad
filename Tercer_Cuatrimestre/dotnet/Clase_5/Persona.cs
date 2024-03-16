namespace Clase_5;
class Persona{
    public string Nombre{get;set;}="No existe";
    public char Sexo {get; set;}='Z';
    public int DNI {get;set;}=0;
    public DateTime FechaNacimiento {get;set;}= new DateTime(1111,11,11);
    public int Edad {
        get
        {
            int aux=0;
            DateTime ahora = DateTime.Now;
            if(ahora.Month<=FechaNacimiento.Month)
                aux=1;
            return (ahora.Year- FechaNacimiento.Year-aux);
        }
    }
    public object this[int i]{
        get{
            if (i==0) return Nombre;
            else if (i==1) return Sexo;
            else if (i==2) return DNI;
            else if (i==3) return FechaNacimiento;
            else if (i==4) return Edad;
            else return null;
        }
        set{
            if (i==0) Nombre=(string)value;
            else if (i==1) Sexo=(char)value;
            else if (i==2) DNI=(int)value;
            else if (i==3) FechaNacimiento=(DateTime)value;
        }
    }
}