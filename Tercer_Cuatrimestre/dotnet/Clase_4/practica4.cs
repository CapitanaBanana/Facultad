/* 
EJERCICIO 1: Definir una clase Persona con 3 campos públicos: Nombre, Edad y DNI. Escribir un algoritmo que permita al
usuario ingresar en una consola una serie de datos de la forma Nombre,Documento,Edad<ENTER>.
*//*
using Clase_4;
Console.SetIn(new System.IO.StreamReader("ej1.txt"));//con esto básicamente le digo al program que los read y write sean de aca y no terminal
string s="";
int i=0;
List<Persona> personas= new List<Persona>(); 
while ((s= Console.ReadLine())!=null){
    string[] per= s.Split(' ');//separo la string y meto cada parte en un nuevo array
    Persona p=new Persona(int.Parse(per[0]),int.Parse(per[1]),per[2]);//hago parse pq son de tipo string
    personas.Add(p);//añado a la lista
    i++;
}
Persona min= new Persona(9999,0,"a");
for (int j=0; j<i; j++){
    Console.WriteLine(personas.ElementAt(j).GetDescripcion());//i lleva la cuenta de cuantos elementos meti, imprimo cada elem de la lista
    if (!(personas.ElementAt(j).EsMayorQue(min))){ //como saca el minimo, busco el que sea false
        min=personas.ElementAt(j);
    }
}
Console.WriteLine("La persona mas joven es "+ min.GetNombre());
*/
/* 
EJERCICIO 2: Codificar la clase Hora de tal forma que el siguiente código produzca la salida por consola que se observa.
*//*
using Clase_4;
Hora h= new Hora(23,30,15);
h.Imprimir();
Hora h2= new Hora(14.45);
h2.Imprimir();
*/
/* 
EJERCICIO 7: Codificar la clase Nodo de un árbol binario de búsqueda de valores enteros. Un árbol binario de búsqueda no
tiene valores duplicados y el valor de un nodo cualquiera es mayor a todos los valores de su subárbol izquierdo
y es menor a todos los valores de su subárbol derecho.
*//* 
using Clase_4;
Nodo arbol= new Nodo(7);
arbol.Insertar(8);
arbol.Insertar(10);
arbol.Insertar(3);
arbol.Insertar(1);
arbol.Insertar(5);
arbol.Insertar(14);
foreach (int i in arbol.GetInOrden())
{
    Console.Write(i + " ");
}
Console.WriteLine();
Console.WriteLine("Altura: "+arbol.GetAltura());
Console.WriteLine("Cant nodos: "+arbol.GetCantNodos());
Console.WriteLine("Valor maximo: "+ arbol.GetValorMaximo());
Console.WriteLine("Valor minimo: "+arbol.GetValorMinimo());
*/
/*
EJERCICIO 8:Implementar la clase Matriz que se utilizará para trabajar con matrices matemáticas. Implementar los dos
constructores y todos los métodos que se detallan a continuación:
*//*
using Clase_4;
Matriz matriz=new Matriz(3,2);
matriz.SetElemento(0,0,1);
matriz.SetElemento(1,1,1);
matriz.SetElemento(2,2,1);
Console.WriteLine(matriz.GetElemento(0,0));
matriz.imprimir();
*/
/*
EJERCICIO 9: Prestar atención a los siguientes programas (ninguno funciona correctamente):
El primero da error al compilar porque dice que bar no fue asignado, en el segundo da error de ejecucion
*//*
Foo f = new Foo();
f.Imprimir();

class Foo
{
    public void Imprimir()
    {
        string? _bar;
        Console.WriteLine(_bar.Length);
    }
}

class Foo
{
    string? _bar;
    public void Imprimir()
    {
        Console.WriteLine(_bar.Length);
    }
}
*/
/*
EJERCICIO 10:¿Qué se puede decir en relación a la sobrecarga de métodos en este ejemplo?
Se puede sobrecargar metodos siempre y cuando tengas distinta cant de parametros, estén en diferente orden o tengan distintos modificadores(in, out)
*//*
class A
{
    public void Metodo(short n)
    {
        Console.Write("short {0} - ", n);
    }
    public void Metodo(int n)
    {
        Console.Write("int {0} - ", n);
    }
    public int Metodo(int n)
    {
        return n + 10;
    }
}
*/
/*
EJERCICIO 11: Qué salida produce en la consola el siguiente programa?
*//*
object o = 5;
Sobrecarga s = new Sobrecarga();
s.Procesar(o, o);//1 NO, 2
s.Procesar((dynamic)o, o);//1
s.Procesar((dynamic)o, (dynamic)o);//2 NO, 1
s.Procesar(o, (dynamic)o);//2
s.Procesar(5, 5);//1
class Sobrecarga
{
public void Procesar(int i, object o)
{
Console.WriteLine($"entero: {i} objeto:{o}");
}
public void Procesar(dynamic d1, dynamic d2)
{
Console.WriteLine($"dynamic d1: {d1} dynamic d2:{d2}");
}
}
*/
/*
EJERCICIO 12: Completar la clase Cuenta para que el siguiente código produzca la salida indicada:
*//*
Cuenta cuenta = new Cuenta();
cuenta.Imprimir();
cuenta = new Cuenta(30222111);
cuenta.Imprimir();
cuenta = new Cuenta("José Perez");
cuenta.Imprimir();
cuenta = new Cuenta("Maria Diaz", 20287544);
cuenta.Imprimir();
cuenta.Depositar(200);
cuenta.Imprimir();
cuenta.Extraer(150);
cuenta.Imprimir();
cuenta.Extraer(1500);
class Cuenta{
    private double _monto;
    private int _titularDNI;
    private string? _titularNobre;

    public Cuenta(){
        _monto=0;
    }
    public Cuenta(string n, int dni):this(){
        _titularDNI=dni;
        _titularNobre=n;
    }
    public Cuenta(int dni):this(){
        _titularDNI=dni;
    }
    public Cuenta(string n):this(){
        _titularNobre=n;
    }
    public void Depositar(int cant){
        _monto+= cant;
    }
    public void Extraer(int cant){
        if (_monto>=cant)
            _monto=_monto-cant;
        else 
            Console.WriteLine("Operacion cancelada, monto insuficiente");
    }
    public void Imprimir(){
        string cadena="Nombre: ";
        if (_titularNobre==null)
            cadena= cadena+"No especificado";
        else cadena= cadena+_titularNobre;
        if (_titularDNI==00)
            cadena= cadena+", DNI: No especificado";
        else cadena= cadena+", DNI: "+ _titularDNI;
        cadena= cadena+", Monto: "+_monto;
        Console.WriteLine(cadena);
    }
}
*/
/* 
EJERCICIO 13: Reemplazar estas líneas de código por otras equivalentes que utilicen el operador null-coalescing (?? ) y / o
la asignación null-coalescing (??=)
*/
string st=null;
string st1=null;
string st2="b";
string st3="c";
string st4="d";
if (st1 == null)
{
    if (st2 == null)
    {
        st = st3;
        
    }
    else
    {
        st = st2;
    }
}
else
{
    st = st1;
}
if (st4 == null)
{
    st4 = "valor por defecto";
}
Console.WriteLine("st es: ", st);
st = st1??st2??st3;
st4??="Valor por defecto";
Console.WriteLine("st es: ", st);