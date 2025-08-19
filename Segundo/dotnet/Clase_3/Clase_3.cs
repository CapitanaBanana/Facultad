/* OJO, LOS PRIMEROS EJERCICIOES ESTÁN MAL, RECORRÍA ERRÓNEAMENTE LAS MATRICES
EJERCICIO 1: Ejecutar y analizar cuidadosamente el siguiente programa: Comprobar tipeando teclas y modificadores (shift, ctrl, alt) para apreciar de qué manera
se puede acceder a esta información en el código del programa.
*//*
Console.CursorVisible = false;
ConsoleKeyInfo k = Console.ReadKey(true);
while (k.Key != ConsoleKey.esc); 
{
Console.Clear();
Console.Write($"{k.Modifiers}-{k.Key}-{k.KeyChar}");
k = Console.ReadKey(true);
}
*/
/*
EJERCICIO 2: Implementar un método para imprimir por consola todos los elementos de una matriz (arreglo
de dos dimensiones) pasada como parámetro. Debe imprimir todos los elementos de una fila en
la misma línea en la consola.
*//*
double[,] matriz= new double[2,3]
{   {1,2,3},
    {4,5,6},
};
ImprimirMatrizA(matriz);
Console.WriteLine();
ImprimirMatrizB(matriz);


void ImprimirMatrizA(double[,] matriz){
    int i=0;
    int j = matriz.GetLength(1);
    foreach (double d in matriz){
        i++;
        Console.Write(d+ " ");
        if (i==j){
            Console.WriteLine();
            i=0;
        }
    }
}
void ImprimirMatrizB(double[,] matriz){
    int filas= matriz.GetLength(1);
    int columnas= matriz.GetLength(0);
    for (int i=0; i< columnas; i++){
        for (int j=0; j<filas; j++){
            Console.Write(matriz[i,j]+ " ");
        }
        Console.WriteLine();
    }
        
}
*/
/* EJERCICIO 3: Implementar el método ImprimirMatrizConFormato, similar al anterior pero ahora con un
parámetro más que representa la plantilla de formato que debe aplicarse a los números al
imprimirse. La plantilla de formato es un string de acuerdo a las convenciones de formato
compuesto, por ejemplo “0.0” implica escribir los valores reales con un dígito para la parte
decimal.
*//*
double[,] matriz= new double[2,3]
{   {1.321321,2.5654354,3.123213265},
    {4.878768,5.243432,6.75645},
};
Console.WriteLine("Ingrese el formato deseado");
string formato=Console.ReadLine();

ImprimirMatrizConFormato(matriz, formato);
void ImprimirMatrizConFormato(double[,] matriz, string formatString){
    int filas= matriz.GetLength(1);
    int columnas= matriz.GetLength(0);
    for (int i=0; i< columnas; i++){
        for (int j=0; j<filas; j++){
            Console.Write(matriz[i,j].ToString(formatString)+ " ");
        }
        Console.WriteLine();
    }
}
*/
/* EJERCICIO 4: Implementar los métodos GetDiagonalPrincipal y GetDiagonalSecundaria que
devuelven un vector con la diagonal correspondiente de la matriz pasada como parámetro. Si la
matriz no es cuadrada generar una excepción ArgumentException.
*//*
double[,] matriz= new double[4,4]
{   {1,2,3,4},
    {5,6,7,8},
    {9,10,11,12},
    {13,14,15,16}
};
double [] diag= GetDiagonalPrincipal(matriz);
foreach (double d in diag){
    Console.Write(d+ " ");
}
Console.WriteLine();
diag= GetDiagonalSecundaria(matriz);
foreach (double d in diag){
    Console.Write(d+ " ");
}

double[] GetDiagonalPrincipal(double[,] matriz){
    int filas= matriz.GetLength(0);
    int columnas= matriz.GetLength(1);
    double[] diagPrincipal= new double[columnas];
    
    if (filas!=columnas){
        throw new ArgumentException();
    }  
        for (int i=0; i<filas; i++){
        diagPrincipal[i]= matriz[i,i];
    }
    return diagPrincipal;
}
double[] GetDiagonalSecundaria(double[,] matriz){
    int filas= matriz.GetLength(0);
    int columnas= matriz.GetLength(1);
    double[] diagSecundaria= new double[columnas];
    
    if (filas!=columnas){
        throw new ArgumentException();
    }  
        for (int i=0, j= filas-1; i<filas; i++, j--){
            diagSecundaria[i]= matriz[i,j];
    }
    return diagSecundaria;
}
*/
/* EJERCICIO 5: Implementar un método que devuelva un arreglo de arreglos con los mismos elementos que la
matriz pasada como parámetro:
*//*
double[,] matriz= new double[5,4]
{   {1,2,3,4},
    {5,6,7,8},
    {9,10,11,12},
    {9,10,11,12},
    {9,10,11,12},

};
double [][] arreglo= GetArregloDeArreglo(matriz);
int filas= matriz.GetLength(1);
int columnas= matriz.GetLength(0);
    for (int i=0; i< columnas; i++){
        for (int j=0; j<filas; j++){
            Console.Write(arreglo[i][j]+ " ");
        }
        Console.WriteLine();
    }
        
    
double[][] GetArregloDeArreglo(double [,] matriz){
    int filas= matriz.GetLength(0);
    int columnas= matriz.GetLength(1);
    double[][] arreglo= new double[filas][];
    for (int i=0; i<filas; i++){
        arreglo[i]= new double[columnas];
        for (int j=0; j<columnas; j++){
            arreglo[i][j]= matriz[i,j];
        }
    }
    return(arreglo);
}
*/
/* EJERCICIO 6:Implementar los siguientes métodos que devuelvan la suma, resta y multiplicación de matrices
pasadas como parámetros. Para el caso de la suma y la resta, las matrices deben ser del mismo
tamaño, en caso de no serlo devolver null. Para el caso de la multiplicación la cantidad de
columnas de A debe ser igual a la cantidad de filas de B, en caso contrario generar una
excepción ArgumentException.
*/ /*
double[,]? a= new double[3,4]
{
    {1,2,3,4},
    {5,6,7,8},
    {9,10,11,12},
};
double[,]? b= new double[3,4]
{
    {6,2,3,4},
    {5,6,7,8},
    {9,10,11,12},
};
double[,]? c= new double[3,3]
{
    {6,2,3},
    {5,6,7},
    {9,10,11},
};
double[,]? res = Suma(a,b);
Console.WriteLine("Suma A+B=");
imprimir(res);
res = Resta(a,b);
Console.WriteLine("resta A-B=");
i primir(res);

double[,]? Suma(double[,] A, double[,] B){
    int filasA= A.GetLength(0);
    int columnasA= A.GetLength(1);
    int filasB= B.GetLength(0);
    int columnasB= B.GetLength(1);

    if ((filasA!= filasB)|(columnasA!= columnasB)){
        throw new ArgumentException();
    }
    double[,] res= new double[filasA,columnasA];
    for (int i=0; i<filasA;i++){
        for(int j=0; j<columnasA;j++){
            res[i,j]= A[i,j]+B[i,j];
        }
    }
    return(res);
}
double[,]? Resta(double[,] A, double[,] B){
    int filasA= A.GetLength(0);
    int columnasA= A.GetLength(1);
    int filasB= B.GetLength(0);
    int columnasB= B.GetLength(1);

    if ((filasA!= filasB)|(columnasA!= columnasB)){
        throw new ArgumentException();
    }
    double[,] res= new double[filasA,columnasA];
    for (int i=0; i<filasA;i++){
        for(int j=0; j<columnasA;j++){
            res[i,j]= A[i,j]-B[i,j];
        }
    }
    return(res);
}

void imprimir(double[,] res){
    int filas= res.GetLength(0);
    int columnas= res.GetLength(1);
    for (int i=0; i<filas;i++){
        for(int j=0; j<columnas;j++){
            Console.Write(res[i,j]+" ");
        }
        Console.WriteLine();
    }
}

*/
/*EJERCICIO 7: ¿De qué tipo quedan definidas las variables x, y, z en el siguiente código?
*//*
int i = 10;
var x = i * 1.0;//double
var y = 1f;//float
var z = i * y;//float
*/
/* EJERCICIO 8: ¿Qué líneas del siguiente método provocan error de compilación y por qué?
*//*
var a = 3L; //a es long
dynamic b = 32; // b es integer
object obj = 3; //obj es int
//a = a * 2.0; //no compila
b = b * 2.0; // b es double
b = "hola"; // b es string
obj = b; //chilla?
b = b + 11; //CONCATENA LAS STRINGS(HOLA11)
//obj = obj + 11; // MUERE
var c = new { Nombre = "Juan" };
var d = new { Nombre = "María" };
var e = new { Nombre = "Maria", Edad = 20 };
var f = new { Edad = 20, Nombre = "Maria" };
//f.Edad = 22;// NO FUNCA, NO TIENE GETTER Y SETTERS
d = c; //a d le asigna juan
//e = d; //e es juan NO FUNCÓ
//f = e; //f es juan 20 NO FUNA
*/
/* EJERCICIO 9: Señalar errores de compilación y/o ejecución en el siguiente código.
Las operaciones sobre dynamic no se comprueban en compilación, así que las podemos ejecutar bajo nuestro propio riesgo.
Es medio risky, si nos equivocamos cagamos.
*//*
object obj = new int[10];
dynamic dyn = 13;
//Console.WriteLine(obj.Length);
Console.WriteLine(dyn.Length); //ERROR, no se puede hacer .length sobre int
*/
/* EJERCICIO 10: Verificar con un par de ejemplos si la sección opcional [:formatString] de formatos compuestos
redondea o trunca cuando se formatean números reales restringiendo la cantidad de decimales.
Plantear los ejemplos con cadenas de formato compuesto y con cadenas interpoladas.
*//*
double number = 3.141592653589793;
string formatted = string.Format("{0:F2}", number);
Console.WriteLine(formatted); // Imprime "3.14"
double number2 = 1.23456789;
int decimals = 3;
string formatted2 = $"{number2:F(decimals)}";
Console.WriteLine(formatted2); // Imprime "1.235"
*/
/*
EJERCICIO 11: Señalar errores de ejecución en el siguiente código
*//*
List<int> a = new List<int>() { 1, 2, 3, 4 };
a.Remove(5);
//a.RemoveAt(4);
*/
/* EJERCICIO 12: Utilizar la clase Queue<T> para implementar un programa que realice el cifrado de un texto
aplicando la técnica de clave repetitiva. La técnica de clave repetitiva consiste en desplazar un
carácter una cantidad constante de acuerdo a la lista de valores que se encuentra en la clave.
*//*
Console.WriteLine("Ingrese una clave: ");
string clave= Console.ReadLine();
Queue<int> fila= new Queue<int>();
char[] abecedario= new char[28]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z',' '};
ponerEnQueue(fila,clave);

Console.WriteLine("Ingrese 1 para cifrar y 2 para descifrar: ");
int opcion= int.Parse(Console.ReadLine());
if (opcion==1){
    Console.WriteLine("Ingrese el texto a cifrar: ");
    string texto= Console.ReadLine();
    cifrado(fila, texto.ToUpper(),abecedario);
}
else if(opcion==2){
    Console.WriteLine("Ingrese el texto a descifrar: ");
    string texto= Console.ReadLine();
    descifrado(fila, texto.ToUpper(),abecedario);
}


void ponerEnQueue(Queue<int> fila, string clave){
    for (int i=0; i<clave.Length; i++){
        fila.Enqueue(clave[i]-48);//agrego cada char a la fila
    }
}

void cifrado(Queue<int> fila, string texto, char[] abecedario){
    for (int i=0; i<texto.Length; i++){
        int num=fila.Dequeue();//saco el primer numero
        Console.Write(nuevaLetra(num,texto[i],abecedario));
        fila.Enqueue(num);
    }
}
void descifrado(Queue<int> fila, string texto, char[] abecedario){
    for (int i=0; i<texto.Length; i++){
        int num=fila.Dequeue();//saco el primer numero
        Console.Write(nuevaLetra2(num,texto[i],abecedario));
        fila.Enqueue(num);
    }
}
char nuevaLetra(int num,char letra,char[] abecedario){
    int i = Array.IndexOf(abecedario, letra);
    if ((i+num)>27){
        int diferencia= i+num-28;//si al sumarle me paso, calculo por cuanto me pase y me muevo esa cantidad de pos desde la a
        letra= abecedario[diferencia];
    }
    else letra= abecedario[i+num];
    return letra;
}
char nuevaLetra2(int num,char letra,char[] abecedario){
    int i = Array.IndexOf(abecedario, letra);
    if ((i-num)<0){
        int diferencia= 28+(i-num);//si al sumarle me paso, calculo por cuanto me pase y me muevo esa cantidad de pos desde la a
        letra= abecedario[diferencia];
    }
    else letra= abecedario[i-num];
    return letra;
}
*/
/*
Console.WriteLine("Ingrese una clave: ");
//string clave= Console.ReadLine();
string clave= "5397";
Queue<int> fila= new Queue<int>();
ponerEnQueue(fila,clave);
Console.WriteLine("Ingrese el texto a cifrar: ");
//string texto= Console.ReadLine();
string texto= "hola mundo";
cifrado(fila, texto.ToUpper());

void ponerEnQueue(Queue<int> fila, string clave){
    for (int i=0; i<clave.Length; i++){
        fila.Enqueue(clave[i]-48);//agrego cada char a la fila
    }
}

void cifrado(Queue<int> fila, string texto){
    for (int i=0; i<texto.Length; i++){
        int num=fila.Dequeue();//saco el primer numero
        Console.Write(nuevaLetra(num,texto[i]));
        fila.Enqueue(num);
    }
}
char nuevaLetra(int num,char letra){
    if (letra==' '){
        letra=(char)91;//pongo al final de las minusculas el espacio
    }
    if ((letra+num)>91){
        int diferencia= 91-letra-num;//si al sumarle me paso, calculo por cuanto me pase y me muevo esa cantidad de pos desde la a
        letra= (char)(65+diferencia+1);
    }
    else letra= (char)(letra+num);
    if (letra==91){
        letra=' ';
    }
    return letra;
}*/

/*EJERCICIO 13: Realizar un análisis sintáctico para determinar si los paréntesis en una expresión aritmética están
bien balanceados. Verificar que por cada paréntesis de apertura exista uno de cierre en la cadena
de entrada. Utilizar una pila para resolverlo. Los paréntesis de apertura de la entrada se
almacenan en una pila hasta encontrar uno de cierre, realizándose entonces la extracción del
último paréntesis de apertura almacenado. Si durante el proceso se lee un paréntesis de cierre y
la pila está vacía, entonces la cadena es incorrecta. Al finalizar el análisis, la pila debe quedar
vacía para que la cadena leída sea aceptada, de lo contrario la misma no es válida.
*//*
Console.WriteLine("Ingrese la expresión a analizar:");
String cadena= Console.ReadLine();
Stack<char> pila= new Stack<char>();
checkeo(pila, cadena);

void checkeo(Stack<char> pila, string cadena){
        bool seguir= true;
        int i=0;
        while ((i< cadena.Length)&&(seguir)){
            char c= cadena[i];
            if (c.Equals('(')){
                pila.Push(c);
            }
            else if (c.Equals(')')){
                if (pila.Count==0){
                    throw new Exception("No maestro, hiciste todo mal");
                }
                char p= pila.Pop();
                if (!(p.Equals('('))){
                    seguir=false;
                }
            }
            i++;
    }
    if (seguir&& pila.Count==0){
        Console.WriteLine("Estas joya ;)");
    }
    else 
        Console.WriteLine(("No maestro, hiciste todo mal"));
}
*/
/* EJERCICIO 14: Utilizar la clase Stack<T> (pila) para implementar un programa que pase un número en base
10 a otra base realizando divisiones sucesivas. Por ejemplo para pasar 35 en base 10 a binario
dividimos sucesivamente por dos hasta encontrar un cociente menor que el divisor, luego el
resultado se obtiene leyendo de abajo hacia arriba el cociente de la última división seguida por
todos los restos.
*//*
Console.WriteLine("Ingrese el decimal que quiera convertir: ");
int num= int.Parse(Console.ReadLine());
Console.WriteLine("Ingrese la base a la que lo quiere pasar: ");
int bas= int.Parse(Console.ReadLine());
Stack<int> pila = new Stack<int>();
pila= conversion(pila, num, bas);
foreach (int i in pila){
    Console.Write(i);
}
Stack<int> conversion(Stack<int> pila, int num, int bas){
    pila.Push(num%bas);
    while (num>=bas){
        num= num/bas;
        pila.Push(num%bas);
    }
    return pila;
}
*/
/* EJERCICIO 15: ¿Qué salida por la consola produce el siguiente código?
*//*
int x = 0;
try
{
    Console.WriteLine(1.0 / x); //8 pq tiende a infinito. Si son float, toma la tendencia
    //Console.WriteLine(1 / x);
    Console.WriteLine("todo OK");
    }
    catch (Exception e)
{
    Console.WriteLine(e.Message);
}
*/
/* EJERCICIO 16. Implementar un programa que permita al usuario ingresar números por la consola. Debe
ingresarse un número por línea finalizado el proceso cuando el usuario ingresa una línea vacía.
A medida que se van ingresando los valores el sistema debe mostrar por la consola la suma
acumulada de los mismos. Utilizar la instrucción try/catch para validar que la entrada
ingresada sea un número válido, en caso contrario advertir con un mensaje al usuario y
proseguir con el ingreso de datos.
*//*
Console.WriteLine("Ingrese numeros: ");
int suma=0;
string entrada="a";
do {
    try{
        entrada=Console.ReadLine();
        if (entrada != ""){
            int num= int.Parse(entrada);
            suma+=num;
            Console.WriteLine("suma: "+ suma);
        }
    }
    catch (Exception FormatException){
        Console.WriteLine("error");
    }
} while (entrada != "");
*/
