/*
EJERCICIO 1: Sin borrar ni modificar ninguna línea, completar la definición de las clases B, C y D
*//*
A[] vector = new A[] { new A(3), new B(5), new C(15), new D(41) };
foreach (A a in vector)
{
a.Imprimir();
}
class A
{
protected int _id;
public A(int id) => _id = id;
public virtual void Imprimir() => Console.WriteLine($"A_{_id}");
}
class B : A
{
    public B(int id) : base(id) { }
    public override void Imprimir(){ 
        Console.Write($"B_{_id} "); 
        base.Imprimir();
    }
}
class C : B
{
    public C(int id) : base(id) { }
    public override void Imprimir(){ 
        Console.Write($"C_{_id} "); 
        base.Imprimir();
    }
}
class D : C
{
public D(int id) : base(id) { }
public override void Imprimir(){ 
        Console.Write($"D_{_id} "); 
        base.Imprimir();
    }
}
*/
/*
EJERCICIO 2: Aunque consultar en el código por el tipo de un objeto indica habitualmente un diseño ineficiente,
por motivos didácticos vamos a utilizarlo. Completar el siguiente código, que utiliza las clases
definidas en el ejercicio anterior, para que se produzca la salida indicada:
*/
/*
A[] vector = new A[] { new C(1), new D(2), new B(3), new D(4), new B(5) };
foreach (A a in vector)
{
 //if (a is B && !(a is C) && !(a is D))
 if(a.GetType()== typeof(B))
    a.Imprimir();
}
class A
{
protected int _id;
public A(int id) => _id = id;
public virtual void Imprimir() => Console.WriteLine($"A_{_id}");
}
class B : A
{
    public B(int id) : base(id) { }
    public override void Imprimir(){ 
        Console.Write($"B_{_id} "); 
        base.Imprimir();
    }
}
class C : B
{
    public C(int id) : base(id) { }
    public override void Imprimir(){ 
        Console.Write($"C_{_id} "); 
        base.Imprimir();
    }
}
class D : C
{
public D(int id) : base(id) { }
public override void Imprimir(){ 
        Console.Write($"D_{_id} "); 
        base.Imprimir();
    }
}
*/
/*
EJERCICIO 3: ¿Por qué no funciona el siguiente código? ¿Cómo se puede solucionar fácilmente?
*/
/*
class Auto
{
double velocidad;//la variable no está definida como public o protected y por tanto no se puede acceder desde taxi
public virtual void Acelerar()
=> Console.WriteLine("Velocidad = {0}", velocidad += 10);
}
class Taxi : Auto
{
public override void Acelerar()
=> Console.WriteLine("Velocidad = {0}", velocidad += 5);
}
*/
/*
EJERCICIO 4: Contestar sobre el siguiente programa: ¿Por qué no es necesario agregar :base en el constructor de Taxi? Eliminar el segundo constructor
de la clase Auto y modificar la clase Taxi para el programa siga funcionando
//no es necesario porque el constructor vacio por defecto llama al base.
*//*
Taxi t = new Taxi(3);
Console.WriteLine($"Un {t.Marca} con {t.Pasajeros} pasajeros");
class Auto
{
public string Marca { get; private set; } = "Ford";
//public Auto(string marca) => this.Marca = marca;
public Auto() { }
}
class Taxi : Auto
{
public int Pasajeros { get; private set; }
public Taxi(int pasajeros):base() {
    this.Pasajeros = pasajeros;
}
}
*/
/*
EJERCICIO 5: ¿Qué líneas del siguiente código provocan error de compilación y por qué?
*//*
class Persona
{
public string Nombre { get; set; }
}
public class Auto
{
private Persona _dueño1, _dueño2;
public Persona GetPrimerDueño() => _dueño1; //ERROR, persona es menos público que get primer dueño
protected Persona SegundoDueño//ERROR, persona es menos público que segundo dueño
{
    set => _dueño2 = value;
}
}
*/
/*
EJERCICIO 6: Señalar el error en cada uno de los siguientes casos
6.1:para poder hacer eso, la string de la clase a debería esar declarada como virtual 
6.2: no se puede tener métodos abstractos en clases no abstractas
6.3: un método abstracto no puede estar implementado en la clase abstracta
6.4: el override de la clase a no invalida ningún otro método
6.5: problemas con los modificadores de acceso. protected<public
6.6: no se pueden sobreescribir métodos estáticos(para eso se usa el new)
6.7: el método de la clase a debería ser público porque sino el de la clase b no puede sobreescribir nada
6.8: ??
6.9: B intenta acceder a id y esta es privada
6.10: la int es privada pero el getter publico, incoherente
6.11: no se implementa el setter en la clase b
6.12: prop en la clase a no es abstract, virtual ni override
*/
/*
EJERCICIO 7: Ofrecer una implementación polimórfica para mejorar el siguiente programa:
*/
/*
Imprimidor.Imprimir(new A(), new B(), new C(), new D());

abstract class Letra {
    public abstract void Imprimir();
}
class A:Letra {
public override void Imprimir() => Console.WriteLine("Soy una instancia A");
}
class B:Letra {
public override void Imprimir() => Console.WriteLine("Soy una instancia B");
}
class C:Letra {
public override void Imprimir() => Console.WriteLine("Soy una instancia C");
}
class D:Letra {
public override void Imprimir() => Console.WriteLine("Soy una instancia D");
}
static class Imprimidor {
public static void Imprimir(params object[] vector) {
foreach (Letra l in vector) {
    l.Imprimir();
}
}
}
*/
/*
EJERCICIO 8: Crear un programa para gestionar empleados en una empresa. Los empleados deben tener las
propiedades públicas de sólo lectura Nombre, DNI, FechaDeIngreso, SalarioBase y Salario. Los
valores de estas propiedades (a excepción de Salario que es una propiedad calculada) deben
establecerse por medio de un constructor adecuado.
Existen dos tipos de empleados: Administrativo y Vendedor. No se podrán crear objetos de la
clase padre Empleado, pero sí de sus clases hijas (Administrativo y Vendedor). Aparte de las
propiedades de solo lectura mencionadas, el administrativo tiene otra propiedad pública de
lectura/escritura llamada Premio y el vendedor tiene otra propiedad pública de lectura/escritura
llamada Comision.
La propiedad de solo lectura Salario, se calcula como el salario base más la comisión o el premio
según corresponda.
Las clases tendrán además un método público llamado AumentarSalario() que tendrá una
implementación distinta en cada clase. En el caso del administrativo se incrementará el salario base
en un 1% por cada año de antigüedad que posea en la empresa, en el caso del vendedor se
incrementará el salario base en un 5% si su antigüedad es inferior a 10 años o en un 10% en caso
contrario.
El siguiente código (ejecutado el día 9/4/2022) debería mostrar en la consola el resultado indicado:
*/

using Clase_6;

Empleado[] empleados = new Empleado[] {
new Administrativo("Ana", 20000000, DateTime.Parse("26/4/2018"), 10000) {Premio=1000},
new Vendedor("Diego", 30000000, DateTime.Parse("2/4/2010"), 10000) {Comision=2000},
new Vendedor("Luis", 33333333, DateTime.Parse("30/12/2011"), 10000) {Comision=2000}
};
foreach (Empleado e in empleados)
{
Console.WriteLine(e);
e.AumentarSalario();
Console.WriteLine(e);
}