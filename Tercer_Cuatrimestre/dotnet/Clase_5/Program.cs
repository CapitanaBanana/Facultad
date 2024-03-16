/*
EJERCICIO 1: Codificar la clase Cuenta de tal forma que el siguiente código produzca la salida por consola que
se indica.
*//*
Cuenta c1 = new Cuenta();
c1.Depositar(100).Depositar(50).Extraer(120).Extraer(50);
Cuenta c2 = new Cuenta();
c2.Depositar(200).Depositar(800);
new Cuenta().Depositar(20).Extraer(20);
c2.Extraer(1000).Extraer(1);
Console.WriteLine("\nDETALLE");
Cuenta.Imprimir();
*/
/*
EJERCICIO 2: Agregar a la clase Cuenta del ejercicio anterior un método estático GetCuentas() que devuelva
un List<Cuenta> con todas las cuentas creadas. Controlar que la modificación de la lista devuelta,
por ejemplo borrando algún elemento, no afecte el listado que internamente mantiene la clase
Cuenta. Sin embargo debe ser posible interactuar efectivamente con los objetos Cuenta de la lista
devuelta. Verificar que el siguiente código produzca la salida por consola que se indica
*//*
new Cuenta();
new Cuenta();

List<Cuenta> cuentas = Cuenta.GetCuentas();
// se recuperó la lista de cuentas creadas
cuentas[0].Depositar(50);
// se depositó 50 en la primera cuenta de la lista devuelta
cuentas.RemoveAt(0);
Console.WriteLine(cuentas.Count);
// se borró un elemento de la lista devuelta
// pero la clase Cuenta sigue manteniendo todos
// los datos "La cuenta id: 1 tiene 50 de saldo"
cuentas = Cuenta.GetCuentas();
Console.WriteLine(cuentas.Count);
// se recupera nuevamente la lista de cuentas
cuentas[0].Extraer(30);
//se extrajo 30 de la cuenta id: 1 que tenía 50 de saldo
*/
/*
EJERCICIO 3: Reemplazar el método estático GetCuentas() del ejercicio anterior por una propiedad estática de
sólo lectura.*/
// con readonly no puedo hacer s_lista_cuentas=new List<Cuenta>(); desde otro lado que no sea cons estatico o declaracion, no pueo borrar lista sin querer
/*
EJERCICIO 4: Indicar en cada caso si la definición de la clase A es correcta, en caso de no serlo detallar cuál es el
error.
*//*
class A {
static int s_a=0;
static A() {
s_a++;
}
public A() {
s_a++;
}
}
//4_A: se puede llamar a una vaiable estatica desde un metodo dinamico o uno estatico. al reves no.
*//*
class A {
static int s_a = 0;
public static A() {
s_a++;
}
A() {
s_a++;
}
}
//4_B: no se pede asignar modificadores (public, private) a un static. el constructor sin nada no anda porque por defecto es privado
*//*
class A {
static int s_a = 0;
static A(int a) {
s_a=a;
}
A(int a) {
s_a = a;
}
}
//4_C: no se puede hacer lo primero, un constructor estatico no puede recibir cosas
*//*
class A {
static int s_a = 0;
int a = 0;
static A() {
s_a = 30;
}
A(int a) {
s_a = a;
this.a = a;
}
}
// 4_D: todo joya maestro
*//*
class A {
static int s_a = 0;
int a = 0;
static A() {
a = 30;
}
A(int a) {
a = s_a;
}
}
//4_E: no se puede modificar desde un metodo estatico a una variable dinamica(a la var de que instancia modifico?)
*//*
class A
{
static int s_a = 1;
int a = 0;
static A() => s_a += s_a;
public static A GetInstancia()
=> new A(1);
A(int a) => this.a = a + s_a;
}
//4_F: anda todo, getinstancia devuelve una nueva instanca creada con el valor 1
*//*
class A
{
const double PI = 3.1416;
static double DoblePI = 2 * PI;
}
//4_G: funciona bien, las var pueden acceder a constantes
*//*
class A
{
static double PI = 3.1416;
const double DoblePI = 2*PI;
}
//4_H: las constantes no pueden acceder a variables pq se resuelven en compilacion
*//*
class A {
static readonly List<int> _lista;
static A() {
_lista = new List<int>();
}
public static void P(int i) {
_lista.Add(i);
}
}
//4_I: se puede porque no se esta modificando la lista en si. (Por que se puede hacer un public static aca?)
*//*
class A {
static readonly List<int> _lista;
static A() {
_lista = new List<int>();
}
public static void P(List<int> li) {
_lista = li;
}
}
//4_J: no se puede modificar la var de la lista desde algo que no sea const. estatico(o en la declaracion en si)
*//*
class A
{
static int[] vector = { 1, 2, 3 };
public int this[int i]
{
get { return vector[i]; }
}
}
//4_K: anda, pero ????????????????
*//*
class A
{
static int[] vector = { 1, 2, 3 };
public static int this[int i]
{
get { return vector[i]; }
}
}
//4_L: no anda, no se que hace
*/
/*
EJERCICIO 5: Qué líneas del código siguiente provocan error de compilación? Analizar cuándo es posible
acceder a miembros estáticos y de instancia.
*//*
class A
{
char c;
static string st;
void metodo1()
{
st = "string";
c = 'A';
}
static void metodo2()
{
new A().c = 'a';// se puede, crea una clase y le pone a
st = "st2";
c = 'B'; //no se puede cambiar var dinamica desde un estatico
A B=new A().st = "otro string"; //no se puede modificar una varriable estatica desde una instanica
}
}
*/
/*
EJERCICIO 6: Modificar la definición de la clase Matriz realizada en la práctica 4. Eliminar los métodos
SetElemento(...) y GetElemento(...). Definir un indizador adecuado para leer y escribir
elementos de la matriz. Eliminar los métodos GetDiagonalPrincipal() y
GetDiagonalSecundaria() reemplazándolos por las propiedades de sólo lectura
DiagonalPrincipal y DiagonalSecundaria.
*//*
using Clase_5;
Matriz m=new Matriz(3,3);
m[0,0] = 5.5;
m[0,1]=6.6;
m[0,2]=7.7;
m[1,0]=8.8;
m.imprimir();
double[] v=m.DiagonalPrincipal;
foreach (double d in v)
{
  Console.WriteLine(d);
}
*/
/*
EJERCICIO 7: Definir la clase Persona con las siguientes propiedades de lectura y escritura: Nombre de tipo
string, Sexo de tipo char, DNI de tipo int, y FechaNacimiento de tipo DateTime. Además
definir una propiedad de sólo lectura (calculada) Edad de tipo int. Definir un indizador de
lectura/escritura que permita acceder a las propiedades a través de un índice entero. Así, si p es un
objeto Persona, con p[0] se accede al nombre, p[1] al sexo p[2] al DNI, p[3] a la fecha de
nacimiento y p[4] a la edad. En caso de asignar p[4] simplemente el valor es descartado. Observar
que el tipo del indizador debe ser capaz almacenar valores de tipo int, char, DateTime y string.
*//*
using Clase_5;
Persona p= new Persona();
p[0]="Capi";
p[1]='M';
p[2]=44590363;
p[3]= new DateTime(2003,08,24);
Console.WriteLine("Nombre: {0}", p[0]);
Console.WriteLine("Sexo: {0}", p[1]);
Console.WriteLine("DNI: {0}", p[2]);
Console.WriteLine("Fecha de nacimiento: {0:dd/MM/yyyy}", p[3]);
Console.WriteLine("Edad: {0}", p[4]);
*/
/*
EJERCICIO 8: Completarla y agregar dos indizadores de sólo lectura
Un índice entero que permite acceder a las personas de la lista por número de documento. Por
ejemplo p=lista[30456345] devuelve el objeto Persona que tiene DNI=30456345 o null en caso
que no exista en la lista.
Un índice de tipo char que devuelve un List<string> con todos los nombres de las personas de la
lista que comienzan con el carácter pasado como índice.
*//*
using Clase_5;
Persona? p = new Persona();
p[0]="Pedro";
p[1]='M';
p[2]=22222;
p[3]=new DateTime(2002,06,28);
Persona? c = new Persona();
c[0]="Capi";
c[1]='M';
c[2]=44590363;
c[3]= new DateTime(2003,08,24);
Persona? pe = new Persona();
pe[0]="Pepe";
pe[1]='F';
pe[2]=43905995;
pe[3]= new DateTime(2002,06,28);

ListaDePersonas lp = new ListaDePersonas();

Persona? per = new Persona();
lp.Agregar(p);
lp.Agregar(c);
lp.Agregar(pe);
per=lp[44590363];
Console.WriteLine(per[0]);
List<String> listaIniciales= new List<String>();
listaIniciales= lp['P'];
foreach( string s in listaIniciales)
    Console.WriteLine(s);
*/
/*
EJERCICIO 9: ¿Cuál es el error en el siguiente programa?
*//*
Auto a = new Auto();
a.Marca = "Ford";
Console.WriteLine(a.Marca);
class Auto
{
private string marca;
public string Marca
{
set
{
Marca = value;
}
get
{
return marca;
}
}
}
// Está declarada con minuscula la variable y se le intenta asignar cosas con mayuscula
*/
/*
EJERCICIO 10: Identificar todos los miembros en la siguiente declaración de clase. Indicar si se trata de un
constructor, método, campo, propiedad o indizador, si es estático o de instancia, y en caso que
corresponda si es de sólo lectura, sólo escritura o lectura y escritura. En el caso de las propiedades
indicar también si se trata de una propiedad auto-implementada.
Nota: La clase compila perfectamente. Sólo prestar atención a la sintaxis, la semántica es irrelevante.
*//*
class A
{
private static int a;//campo estatico 
private static readonly int b; //campo estatico solo lectura
A() { } //constructor vacio
public A(int i) : this() { }//constructor que recibe una int y llama al const vacio
static A() => b = 2;//constructor estatico vacio asigna 2 a b
int c; //campo privado
public static void A1() => a = 1; // método estatico
public int A1(int a) => A.a + a; //metodo de instancia
public static int A2; //propiedad estatica
static int A3 => 3; //propiedad
private int A4() => 4;//método de instancia
public int A5 { get => 5; } //propiedad de solo lectura
int A6 { set => c = value; }//propiedad de solo escritura
public int A7 { get; set; }//propiedad autoimplementada
public int A8 { get; } = 8;//propiedad de solo lectura
public int this[int i] => i; //indizador
}
*/
/*
EJERCICIO 11: ¿ Qué diferencia hay entre estas dos declaraciones?
a) public int X = 3; y b) public int X => 3;
La primera es la declaración de una variable que se crea con el valor 3. La segunda es una propiedad que devuelve la constante 3 cada vez que se la llama
*/
