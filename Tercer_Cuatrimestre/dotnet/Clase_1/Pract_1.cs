/* 
EJ 1: Consultar en la documentación de Microsoft y responder cuál es la diferencia entre los métodos
WriteLine() y Write() de la clase System.Console ¿Cómo funciona el método ReadKey() de
la misma clase? Escribir un programa que imprima en la consola la frase “Hola Mundo”
haciendo una pausa entre palabra y palabra esperando a que el usuario presione una tecla para
continuar Tip: usar los métodos ReadKey() y Write() de la clase System.Console.

Console.Write("Hola");
Console.ReadKey(true);
Console.Write(" mundo!");
*/
/*
EJ 2: Investigar por las secuencias de escape \n, \t , \" y \\. Escribir un programa que las utilice
para imprimir distintos mensajes en la consola.

Console.Write("Hola\n");
Console.Write("Hola\t");
Console.Write("Hola\"");
Console.Write("Hola\\");
*/
/*
EJ 3:El carácter @ delante de un string desactiva los códigos de escape. Probar el siguiente
fragmento de código, eliminar el carácter @ y utilizar las secuencias de escape necesarias para
que el programa siga funcionando de igual manera

string st = "c:\\windows\\system";
Console.WriteLine(st);
*/
/*
EJ 4: Escribir un programa que solicite al usuario ingresar su nombre e imprima en la consola un
saludo personalizado utilizando ese nombre o la frase “Hola mundo” si el usuario ingresó una
línea en blanco. Para ingresar un string desde el teclado utilizar Console.ReadLine()

Console.WriteLine("Ingrese su nombre: ");
string nombre= Console.ReadLine();
if (nombre!="")
    Console.WriteLine("Hola "+ nombre + "!");
else 
    Console.WriteLine("Hola Mundo");
*/
/*
EJ 5: Idem. al ejercicio anterior salvo que se imprimirá un mensaje de saludo diferente según sea el
nombre ingresado por el usuario. Así para “Juan” debe imprimir “¡Hola amigo!”, para “María”
debe imprimir “Buen día señora”, para “Alberto” debe imprimir “Hola Alberto”. En otro caso,
debe imprimir “Buen día ” seguido del nombre ingresado o “¡Buen día mundo!” si se ha
ingresado una línea vacía.
a) utilizando if ... else if
b) utilizando switch
/*
A-
Console.WriteLine("Ingrese su nombre: ");
string nombre= Console.ReadLine();
if(nombre== "Juan") 
    Console.WriteLine("¡Hola amigo!");
else if (nombre == "María")
    Console.WriteLine("Buen día señora");
else if (nombre == "Alberto")
    Console.WriteLine("Hola Alberto");
else if (nombre=="")
    Console.WriteLine("¡Buen día mundo!");
else 
    Console.WriteLine("Buen día "+ nombre);


B-
*/
/*
Console.WriteLine("Ingrese su nombre: ");
string nombre= Console.ReadLine();
switch(nombre)
{
    case "Juan":
        Console.WriteLine("¡Hola amigo!");
        break;
    case "María":
        Console.WriteLine("Buen día señora");
        break;
    case "Alberto":
        Console.WriteLine("Hola Alberto");
        break;
    case "":
        Console.WriteLine("¡Buen día mundo!");
        break;
    default:
        Console.WriteLine("Buen día "+ nombre);
        break;
}
*/
/*
EJ 6: Utilizar Console.ReadLine() para leer líneas de texto (secuencia de caracteres que finaliza al
presionar <ENTER>) por la consola. Por cada línea leída se debe imprimir la cantidad de
caracteres de la misma. El programa termina al ingresar la cadena vacía. (si st es una variable
de tipo string, entonces st.Length devuelve la cantidad de caracteres del string).

string cadena;
Console.WriteLine("Ingrese cadenas seguidas de enter:");
cadena= Console.ReadLine();
while (cadena != ""){
    Console.WriteLine("Caracteres: "+ cadena.Length);
    cadena= Console.ReadLine();
}
*/
/*
EJ 7: Qué hace la instrucción Console.WriteLine("100".Length);

Console.WriteLine("100".Length);
*/
/*
EJ 8: Sea st una variable de tipo string correctamente declarada. ¿Es válida la siguiente instrucción:
Console.WriteLine(st=Console.ReadLine());?

string st;
Console.WriteLine(st=Console.ReadLine());
*/
/*
EJ 9: Escribir un programa que lea dos palabras separadas por un blanco que terminan con <ENTER>,
y determinar si son simétricas (Ej: 'abbccd' y 'dccbba' son simétricas).
Tip: si st es un string, entonces st[0] devuelve el primer carácter de st, y st[st.Length-1]
devuelve el último carácter de st.

String palabra_1;
String palabra_2;
bool iguales= true;
int i=0;
Console.Write("Ingrese la primera palabra: ");
palabra_1= Console.ReadLine();
Console.Write("Ingrese la segunda palabra: ");
palabra_2= Console.ReadLine();
if (palabra_1.Length != palabra_2.Length)
{
    iguales=false;
}
else 
{
    while (iguales && i< palabra_1.Length)
    {
        if (palabra_1[i]!=palabra_2[palabra_2.Length-i-1])
        {
            iguales=false;
        }
        i++;
    }
}
if (iguales==true){
    Console.WriteLine("son simétricas :)");
}
else Console.WriteLine("son asimétricas :p");
*/
/* 
EJ 10: Escribir un programa que imprima en la consola todos los números que sean múltiplos de 17 o
de 29 comprendidos entre 1 y 1000

for (int i=1; i<=1000; i++){
    if (i%17==0 || i%29==0 )
        Console.WriteLine(i);
}
*/
/*
EJ 11:  Comprobar el funcionamiento del siguiente fragmento de código, analizar el resultado y
contestar las preguntas
onsole.WriteLine("10/3 = " + 10 / 3);----------- 3
Console.WriteLine("10.0/3 = " + 10.0 / 3);------------3,33
Console.WriteLine("10/3.0 = " + 10 / 3.0);------------3,33
int a = 10, b = 3;
Console.WriteLine("Si a y b son variables enteras, si a=10 y b=3");
Console.WriteLine("entonces a/b = " + a / b);
double c = 3;
Console.WriteLine("Si c es una variable double, c=3");
Console.WriteLine("entonces a/c = " + a / c);------------3,33
¿Qué se puede concluir respecto del operador de división “/” ?
si hay enteros el resultado es entero, se adapta
¿Cómo funciona el operador + entre un string y un dato numérico?
imprime la string seguida del número
*/
/*
EJ 12: Escribir un programa que imprima todos los divisores de un número entero ingresado desde la
consola. Para obtener el entero desde un string st utilizar int.Parse(st).

String sNum;
int num;
Console.WriteLine("Ingresa un numerito: ");
sNum= Console.ReadLine();
num= int.Parse(sNum);
for (int i= num; i>=0; i--){
    if (num%i==0)
        Console.WriteLine(i);
}
*/
/*
EJ 13: Escribir un programa que calcule la suma de dos números reales introducidos por teclado y
muestre el resultado en la consola (utilizar double.Parse(st) para obtener el valor double a
partir del string st.

String num1;
String num2;
Console.Write("Ingrese el primer número: ");
num1= Console.ReadLine();
Console.Write("Ingrese el segundo número: ");
num2= Console.ReadLine();
Console.WriteLine("Suma: " +(double.Parse(num1)+double.Parse(num2)));
*/
/*
EJ 14: Escribir un programa que multiplique por 365 el número entero ingresado por el usuario desde
la consola. El resultado debe imprimirse en la consola dígito por dígito, separado por un espacio
comenzando por el dígito menos significativo al más significativo.

int res;
Console.WriteLine("Ingrese el número a multiplicar: ");
string Snum= Console.ReadLine();
res = int.Parse(Snum) * 365;
while (res!=0){
    Console.Write(res%10+" ");
    res = res/10;
}
*/
/*
EJ 15: Escribir un programa que solicite un año por pantalla y diga si es bisiesto. Un año es bisiesto si
es divisible por 4 pero no por 100. Si es divisible por 100, para ser bisiesto debe ser divisible
por 400

bool biciestio =false;
Console.WriteLine("Ingrese un año: ");
string Sanio= Console.ReadLine();
int anio = int.Parse(Sanio);
if (anio%100==0)
{
    if (anio%400==0)
        biciestio=true;
}
else if (anio%4==0)
    biciestio=true;
if (biciestio) 
    Console.WriteLine("Biciesto :)");
else 
    Console.WriteLine("Bicieston´t :)");
*/
/*
EJ 16: Si a y b son variables enteras, identificar el problema (y la forma de resolverlo) de la siguiente
expresión (tip: observar qué pasa cuando b = 0):
if ((b != 0) & (a/b > 5)) Console.WriteLine(a/b);
& evalua ambas aunque la 1ra sea falsa, && evalua la primera y si es falsa corta ahí
por eso, al ejecutar da error ya que intenta checkear la segunda división y así dividir por 0.
*/
/*
EJ 17: Utilizar el operador ternario condicional para establecer el contenido de una variable entera con
el menor valor de otras dos variables enteras.

int var1=6;
int var2=3;
int min= var1<var2 ? var1 : var2;
Console.WriteLine(min);
*/
/*
EJ 18:¿Cuál es la salida por consola del siguiente código?

for (int i = 0; i <= 4; i++)
{
string st = i < 3 ? i == 2 ? "dos" : i == 1 ? "uno" : "< 1" : i < 4 ? "tres" : "> 3";
Console.WriteLine(st);
}
*/
/*
EJ 20: ¿Cuál es el problema del código siguiente y cómo se soluciona?
int numero = ;
for (int i = 1; i <= 10;)
{
Console.WriteLine(i++);
}
*/
/*
EJ 21: Analizar el siguiente código. ¿Cuál es la salida por consola?
*//*
int i = 1;
if (--i == 0)
{
Console.WriteLine("cero");
}
if (i++ == 0)
{
Console.WriteLine("cero");
}
Console.WriteLine(i);
*/int sum = 0;
int i = 1;
while (i <= 10) //error sintactico
{
 sum += i++;
 Console.WriteLine(sum);
}
Console.WriteLine();
Console.WriteLine(sum);