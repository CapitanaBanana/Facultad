namespace Clase_6;
public class Administrativo:Empleado{
    public double Premio{get;set;}

    public Administrativo(string nombre, int dni, DateTime fecha, double salario):base( nombre, dni, fecha, salario){
    }
    public override double Salario{
        get{
            return _salarioBase+Premio;
        }
    }
    public override void AumentarSalario()
    {
        int aux=0;
        DateTime ahora = new DateTime(2022,04,09);
        if(ahora.Month<=_fechaDeIngreso.Month)
            aux=1;
        int anios=ahora.Year-_fechaDeIngreso.Year-aux;
        _salarioBase= _salarioBase+_salarioBase*(0.01*anios);
    }
    public override string ToString()
    {
        return ("Administrativo: "+ base.ToString());
    }
}