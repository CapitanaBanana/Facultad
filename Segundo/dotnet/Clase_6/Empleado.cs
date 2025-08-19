namespace Clase_6;
public abstract class Empleado{
    public string _nombre{get;}
    public int _DNI{get;}
    public DateTime _fechaDeIngreso{get;}
    public double _salarioBase{get; protected set;}
    public abstract double Salario{get;}

    public abstract void AumentarSalario();
    public Empleado(string nombre, int dni, DateTime fecha, double salario){
        _nombre=nombre;
        _DNI=dni;
        _fechaDeIngreso=fecha;
        _salarioBase=salario;
    }
    public override string ToString()
    {
        int aux=0;
        DateTime ahora= new DateTime(2022,04,09);
        if(ahora.Month<=_fechaDeIngreso.Month)
            aux=1;
        int ant=  ahora.Year-_fechaDeIngreso.Year-aux;
        string s=($"Nombre: {_nombre}, DNI: {_DNI}, Antiguedad: {ant} \n salario base: {_salarioBase}, Salario: {Salario} \n -----------------------------");
        return s;
    }
}