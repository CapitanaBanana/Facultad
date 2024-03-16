class Program
{
    static void Main(string[] args)
    {
    int a = 17;
    int b = 23;
    Swap<int>(ref a, ref b);
    Console.WriteLine($"a={a} y b={b}");
    string st1 = "hola";
    string st2 = "mundo";
    Swap<string>(ref st1, ref st2);
    Console.WriteLine($"st1={st1} y st2={st2}");
    
    int i = (int)Maximo(100, 55);
    Console.WriteLine(i);
    string st = (string)Maximo("hola", "mundo");
    Console.WriteLine(st);
    Console.WriteLine(Maximo('A','B'));
    }
    static IComparable Maximo(IComparable a, IComparable b)
{
if (a.CompareTo(b) > 0)
{
return a;
}
return b;
}

    static void Swap<T>(ref T i, ref T j)
    {
    T auxi = i;
    i = j;
    j = auxi;
    }
}