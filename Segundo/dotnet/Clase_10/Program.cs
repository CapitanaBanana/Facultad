/*
EJERCICIO 1 Utilizando el método Range de la clase System.Linq.Enumerable y los métodos de LINQ que
sean necesarios, obtener:
a) Lista con todos los múltiplos de 5 entre 100 y 200
b) Lista con todos los números primos menores que 100
c) Lista con las potencias de 2, desde 20 a 210
d) La suma y el promedio de los valores de la lista anterior
e) Lista de todos los n2 que terminan con el dígito 6, para n entre 1 y 20
f) Lista con los nombres de los días de la semana en inglés que contengan una letra ‘u’
(tip: utilizar el enumerativo DayOfWeek)
*//*
using System;
var mult = Enumerable.Range(100, 200).Where(n =>n%5==0);
Mostrar(mult);

var primos = Enumerable.Range(0, 200).Where(n =>{
    for (int i=2; i<n;i++){
        if ((n%i)==0)
            return false;
    }
    return true;
});
Console.WriteLine();
Mostrar(primos);

var pot = Enumerable.Range(20,210).Where(n =>(n & (n - 1)) == 0);
Mostrar(pot);

void Mostrar<T>(IEnumerable<T> secuencia)
{
foreach (T elemento in secuencia)
{
Console.Write(elemento + " ");
}
Console.WriteLine();
}
*/
/*
EJERCICIO 2: Listar por consola la cantidad de veces que se repiten los elementos de un vector de enteros.
Ordenar por cantidad de repeticiones. Completar el siguiente código para que la salida por consola
sea la indicada
*/
int[] vector = new int[] { 1, 3, 4, 5, 9, 4, 3, 4, 5, 1, 1, 4, 9, 4, 3, 1 };
vector.GroupBy(n=> n).OrderBy(g=> g.Count).ToList().ForEach(grup =>{
    Console.WriteLine($"{grup.Key} ({grup.Count()})");
    });

