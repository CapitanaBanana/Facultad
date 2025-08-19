/*
EJERCICIO 1: Qué líneas del siguiente código provocan conversiones boxing y unboxing.
char c1 = 'A';
string st1 = "A";
object o1 = c1; //boxing-> char en object 
object o2 = st1; // 
char c2 = (char)o1; // unboxing-> obj(char) en char
string st2 = (string)o2; //string es x ref entonces no pasa nada

EJERCICIO 2: El tipo object es un tipo referencia, por lo tanto luego de la sentencia o2 = o1 ambas
variables están apuntando a la misma dirección. ¿Cómo explica entonces que el resultado en la
consola no sea “Z Z”?
object o1 = "A";
object o2 = o1;
o2 = "Z";
Console.WriteLine(o1 + " " + o2);
PORQUE AL ASIGNARLE Z A O2 SE CREA UNA NUEVA POS DE MEMORIA A LA QUE SE LE ASIGNA DICHA LETRA Y LA OTRA NO ES MODIFICADA

EJERCICIO 3: Analizar la siguiente porción de código para calcular la sumatoria de 1 a 10. ¿Cuál es el error?
¿Qué hace realmente?
*//*
int sum = 0;
int i = 1;
while (i <= 10); //error sintactico
{
 sum += i++;
}
Console.WriteLine(sum);
*/
/*
EJERCICIO 4: ¿Cuál es la salida por consola si no se pasan argumentos por la línea de comandos?
args es un vector y por tanto viene inicializado en 0, por tanto no es null y lo primero imprime false
*//*
Console.WriteLine(args == null);
Console.WriteLine(args.Length);
*/ 
/*
EJERCICIO 5: ¿Qué hace la instrucción
int[]? vector = new int[0];
¿asigna a la variable vector el valor null?
no, crea un vector inservible (de 0 pos) que acepta el valor null
*/
/*
EJERCICIO 6: Determinar qué hace el siguiente programa y explicar qué sucede si no se pasan parámetros cuando se invoca desde la línea de comandos.
TIRA ERROR PORQUE INTENTA ENTRAR A LA POS 0 DEL ARREGLO LA CUAL NO EXISTE PORQUE NO LE PASAMOS NADA
*//*
Console.WriteLine("¡Hola {0}!", args[0]);
*/
/*
EJERCICIO 7: Analizar el siguiente código. ¿Qué líneas producen error de compilación y por qué?
*/ 
/*
char c;
char? c2;
string? st;
//c = ""; //error "" es para string
//c = '';
//c = null; //char no admite null por defecto
c2 = null;
c2 = (65 as char?);
st = "";
//st = ''; // ''
st = null;
//st = (char)65;
//st = (string)65;
st = 47.89.ToString();
*/
/*
EJERCICIO 8: Escribir un programa que reciba una lista de nombres como parámetro e imprima por consola un
saludo personalizado para cada uno de ellos.
a) utilizando la sentencia for
b) utilizando la sentencia foreach
*/
/*
foreach(string s in args)
{
    Console.WriteLine("Hola, "+ s+"!");
}
for (int i=0; i< args.Length; i++)
{
    Console.WriteLine("Hola, "+ args[i]+"!");
}
*/
/*
EJERCICIO 9:Investigar acerca de la clase StringBuilder del espacio de nombre System.Text ¿En qué
circunstancias es preferible utilizar StringBuilder en lugar de utilizar string? Implementar
un caso de ejemplo en el que el rendimiento sea claramente superior utilizando
StringBuilder en lugar de string y otro en el que no.

DateTime actual = DateTime.Now; 
DateTime pasado;
TimeSpan tiempo;
string s= "0";
for(int i=1; i<=99999; i++){
    s= s+i;
}

pasado= DateTime.Now;
tiempo= pasado-actual;


Console.WriteLine(tiempo);
Console.WriteLine();
actual = DateTime.Now; 

System.Text.StringBuilder s1;
s1= new System.Text.StringBuilder("0");
for(int i=1; i<=99999; i++){
    s1.Append(i);
}
pasado= DateTime.Now;
tiempo= pasado-actual;

Console.WriteLine(tiempo);
/*
EJERCICIO 10: Investigar sobre el tipo DateTime y usarlo para medir el tiempo de ejecución de los algoritmos
implementados en el ejercicio anterior.
*/
/*
EJERCICIO 11: ¿Para qué sirve el método Split de la clase string? Usarlo para escribir en la consola todas
las palabras (una por línea) de una frase ingresada por consola por el usuario.

//string[] divididas= args[0].Split(' ');
foreach (string s in args[0].Split(' ')){
    Console.WriteLine(s);
}
*/
/*
EJERCICIO 12: Comprobar el funcionamiento del siguiente programa y dibujar el estado de la pila y la memoria
heap cuando la ejecución alcanza los puntos indicados (comentarios en el código)
se crea una sola string builder en la heap y cada posición del vector apunta a ella. Por tanto, cuando se modifica la pos 5 se modifica
la pos a la que apunta y cambia todo el vector. en cambio abajo se modifica creando una nueva sb,haciendo que sea la única modificada.
*//* 
using System.Text;
object[] v = new object[10];
v[0] = new StringBuilder("Net");
for (int i = 1; i < 10; i++)
{
v[i] = v[i - 1];
}
(v[5] as StringBuilder).Insert(0, "Framework .");
foreach (StringBuilder s in v)
Console.WriteLine(s);
Console.WriteLine();
//dibujar el estado de la pila y la mem. heap
//en este punto de la ejecución
v[5] = new StringBuilder("CSharp");
foreach (StringBuilder s in v)
Console.WriteLine(s);
//dibujar el estado de la pila y la mem. heap
//en este punto de la ejecución
*/
/*
EJERCICIO 13: Definir el tipo de datos enumerativo llamado Meses y utilizarlo para:
a) Imprimir en la consola el nombre de cada uno de los meses en orden inverso (diciembre,
noviembre, octubre ..., enero)
c) Solicitar al usuario que ingrese un texto y responder si el texto tipeado corresponde al
nombre de un mes
Nota: en todos los casos utilizar un for iterando sobre una variable de tipo Meses
*//*
for (meses m= meses.diciembre;m>= meses.enero; m--)
{
    Console.Write(m+", ");
}
Console.WriteLine("Ingrese un mes: ");
meses m1;
bool esta;
m1= Enum.Parse(Console.ReadLine());
//esta= Enum.IsDefined(typeof(meses), m1);
//Console.WriteLine(esta);
for (meses? m= meses.enero;m<= meses.diciembre; m++)
{
    if (m1= )
        esta= true;
}
Console.WriteLine("Ingrese un mes: ");
enum meses{
    enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre
}
*/
/*
EJERCICIO 14: Implementar un programa que muestre todos los números primos entre 1 y un número natural
dado (pasado al programa como argumento por la línea de comandos). Definir el método bool
EsPrimo(int n) que devuelve true sólo si n es primo. Esta función debe comprobar si n es
divisible por algún número entero entre 2 y la raíz cuadrada de n. (Nota: Math.Sqrt(d)
devuelve la raíz cuadrada de d)
*//*
int num= int.Parse(args[0]);
for (int i=1; i<=num; i++)
    if (esPrimo(i))
        Console.WriteLine(i);

bool esPrimo(int n){
    for (int i=2; i<=Math.Sqrt(n); i++){
        if (n%i==0)
            return false;
    }
    return true;
} 
*/
/*
Escribir una función (método int Fib(int n)) que calcule el término n de la serie de
Fibonacci.
Fib(n) = 1, si n <=2
Fib(n) = Fib(n-1) + Fib(n-2), si n > 2
*//*
Console.WriteLine("Ingrese el termino de la sucesion que desea calcular");
int n=int.Parse(Console.ReadLine());
Console.WriteLine(Fib((n)));
int Fib(int n){
    if (n <=2)
        return 1;
    else
    return Fib(n-1) + Fib(n-2);
}
*//*
16. Escribir una función (método int Fac(int n)) que calcule el factorial de un número n
pasado al programa como parámetro por la línea de comando
a) Definiendo una función no recursiva
b) Definiendo una función recursiva
c) idem a b) pero con expression-bodied methods (Tip: utilizar el operador condicional
ternario)
*/

//Console.WriteLine(fac(int.Parse(args[0])));
//Console.WriteLine(fac(4));
/* int fac(int n){
    int mul=1;
    for (int i=1; i<=n; i++){
        mul=mul*i;
    }
    return mul;
}
*//*
int fac(int n){
if(n<=1)
    return 1;
else
    return fac(n-1)*n;
}
*/
//int fac(int n)=> n<=1? 1: fac(n-1)*n;
/* EJERCICIO 17: Ídem. al ejercicio 16.a) y 16.b) pero devolviendo el resultado en un parámetro de salida
void Fac(int n, out int f)
int f;
void Fac(int n,out int f){
    int m=1;
    for(int i=1;i<=n;i++){
        m=i;
    }
    f=m;
}
Fac(int.Parse((args[0])),out f);
Console.WriteLine(f);

int f;
void Fac(int n,out int f){
    if(n<=1)
        f=1;
    else
    Fac(n-1n,out f);
}
Fac(int.Parse((args[0])),out f);
Console.WriteLine(f);
/
/ EJERCICIO 18: Codificar el método Swap que recibe 2 parámetros enteros e intercambia sus valores. El cambio
debe apreciarse en el método invocador.
void Swap(ref int a,ref int b){
    int c=a;
    a=b;
    b=c;
}
int a=5;
int b=10;
Swap(ref a,ref b);
Console.WriteLine(a+" " +b);

EJERCICIO 19: Codificar el método Imprimir para que el siguiente código produzca la salida por consola que
se observa. Considerar que el usuario del método Imprimir podría querer más adelante
imprimir otros datos, posiblemente de otros tipos pasando una cantidad distinta de parámetros
cada vez que invoque el metodo

void Imprimir(params object []list){
    for (int i=0;i<list.Length;i++){
        Console.Write(list[i]+ " ");
    }
}
Imprimir(1,2,3,4,"hola",5);
*/