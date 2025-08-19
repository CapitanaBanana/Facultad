namespace Clase_6;
public class Vendedor:Empleado{
    public int Comision{get;set;}

    public Vendedor(string nombre, int dni, DateTime fecha, double salario):base( nombre, dni, fecha, salario){
    }
    public override double Salario{
        get{
            return _salarioBase+Comision;
        }
    }
    public override void AumentarSalario()
    {
        int aux=0;
        DateTime ahora = new DateTime(2022,04,09);
         if(ahora.Month<=_fechaDeIngreso.Month)
            aux=1;
        int anios=ahora.Year-_fechaDeIngreso.Year-aux;
        if (anios<10)
            _salarioBase= _salarioBase+(_salarioBase* 0.05);
        else
            _salarioBase= _salarioBase+(_salarioBase* 0.10);
    }
    public override string ToString()
    {
        return ("Vendedor: "+ base.ToString());
    }
}