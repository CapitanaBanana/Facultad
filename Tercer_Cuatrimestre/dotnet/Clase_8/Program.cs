/*
EJ 8
using System.Collections;
IEnumerable rango = new Rango(6, 30, 3);
foreach (int i in rango)
{
Console.WriteLine(i.ToString());
}

public class Rango : IEnumerable
{
    private List<int> lista= new List<int>();

    public Rango(int a, int b, int c){
        for (int i=a; i<=b; i=i+c){
            lista.Add(i);
        }
    }

    public IEnumerator GetEnumerator()
        {
        return lista.GetEnumerator();
        }
}
*/
/*
DELEGADOS
EJ 1: 
Del1 d1 = delegate (int x) { Console.WriteLine(x); };
d1(10);
Del2 d2 = x => Console.WriteLine(x.Length);
d2(new int[] { 2, 4, 6, 8 });
Del3 d3 = x =>
{
int sum = 0;
for (int i = 1; i <= x; i++)
{
sum += i;
}
return sum;
};
int resultado = d3(10);
Console.WriteLine(resultado);
Del4 d4 = new Del4(LongitudPar);
Console.WriteLine(d4("hola mundo"));

bool LongitudPar(string st)
{
return st.Length % 2 == 0;
}
delegate void Del1(int n);
delegate void Del2(int[] n);
delegate int Del3(int n);
delegate bool Del4(string n);
*/
/*
EJ 4: 
int[] vector = new int[] { 1, 2, 3, 4, 5 };
vector.Print("Valores iniciales: ");
var vector2 = vector.Seleccionar(n => n * 3);
vector2.Print("Valores triplicados: ");
vector.Seleccionar(n => n * n).Print("Cuadrados: ");

delegate int FuncionEntera(int n);
static class VectorDeEnterosExtension
{
public static void Print(this int[] vector, string leyenda)
{
    string st = leyenda;
    if (vector.Length > 0)
        {
            foreach (int n in vector) st += n + ", ";
            st = st.Substring(0, st.Length - 2);
        }
    Console.WriteLine(st);
}
public static int[] Seleccionar(this int[] vector, FuncionEntera f)
{
    int[] aux= new int[vector.Length];
    for(int i=0; i< vector.Length; i++){
        aux[i]= f(vector[i]);
    }   
    return aux;
}
}
*/
/*
int[] vector = new int[] { 1, 2, 3, 4, 5 };
vector.Print("Valores iniciales: ");
vector.Donde(n => n % 2 == 0).Print("Pares: ");
vector.Donde(n => n % 2 == 1).Seleccionar(n => n * n).Print("Impares al cuadrado: ");

delegate bool Predicado(int n);
delegate int FuncionEntera(int n);
static class VectorDeEnterosExtension
{
    public static void Print(this int[] vector, string leyenda)
    {
        string st = leyenda;
        if (vector.Length > 0)
            {
                foreach (int n in vector) st += n + ", ";
                st = st.Substring(0, st.Length - 2);
            }
        Console.WriteLine(st);
    }
    public static int[] Seleccionar(this int[] vector, FuncionEntera f)
    {
        int[] aux= new int[vector.Length];
        for(int i=0; i< vector.Length; i++){
            aux[i]= f(vector[i]);
        }   
        return aux;
    }
    public static int[] Donde(this int[] vector, Predicado p)
    {
        List<int> aux= new List<int>();
        for(int i=0; i< vector.Length; i++){
            if(p(vector[i])){
                aux.Add(vector[i]);
            }
        }
        int[] vec= aux.ToArray();
        return vec;
    }
}
*/