/*List<string> lista = new List<string>() { "uno","dos","tres" };
IEnumerable<string> secuencia = lista.Select(st => "(" + st.ToUpper() + ")");
Mostrar(secuencia);
IEnumerable<int> secuencia2 = secuencia.Select(st => st.Length); //devuelve entero
Mostrar(secuencia2);
IEnumerable<double> secuencia3 = secuencia.Select(st => (st.Length / 2.0));
Mostrar(secuencia3);

void Mostrar<T>(IEnumerable<T> secuencia)
{
    foreach (T elemento in secuencia)
    {
        Console.Write(elemento + " ");
    }
    Console.WriteLine();
}
*//*
var personas = Persona.GetLista();
personas.ForEach(p => Console.WriteLine(p)); // lista todas las personas
Console.WriteLine();
personas.Where(p => p.Edad>18).ToList().ForEach(p=> Console.WriteLine(p)); //devuelve entero
var agrupadas = personas.GroupBy(p => p.Nombre[0]).OrderBy(g=>g.Key);
foreach(var grupo in agrupadas)
{
Console.WriteLine($"Inicial: {grupo.Key}");
grupo.ToList().ForEach(p => Console.WriteLine(" - " + p));
}
class Persona
{
    public string Nombre { get; private set; }
    public int Edad { get; private set; }
    public string Pais { get; private set; }
    public Persona(string nombre, int edad, string pais)
    {
        Nombre = nombre;
        Edad = edad;
        Pais = pais;
    }
    public override string ToString()
    {
        return $"{Nombre} ({Edad} años) {Pais.Substring(0, 3)}.";
    }

    // vamos a hardcodear una lista de personas
    // que usaremos en los siguientes ejemplos
    // para ello definimos el siguiente método estático
    public static List<Persona> GetLista()
    {
        return new List<Persona>() {
            new Persona("Pablo",15,"Argentina"),
            new Persona("Juan", 55,"Argentina"),
            new Persona("José",9,"Uruguay"),
            new Persona("María",33,"Uruguay"),
            new Persona("Lucía",16,"Perú"),
        };
    }
}
*/
using (var context = new EscuelaContext())
{
context.Database.EnsureCreated();
}
using (var db = new EscuelaContext())
{
Console.WriteLine("-- Tabla Alumnos --");
foreach (var a in db.Alumnos)
{
Console.WriteLine($"{a.Id} {a.Nombre}");
}

Console.WriteLine("-- Tabla Exámenes --");
foreach (var ex in db.Examenes)
{
Console.WriteLine($"{ex.Id} {ex.Materia} {ex.Nota}");
}
}
public class EscuelaContext : DbContext
{
#nullable disable
public DbSet<Alumno> Alumnos { get; set; }
public DbSet<Examen> Examenes { get; set; }
#nullable restore

protected override void OnConfiguring(DbContextOptionsBuilder
optionsBuilder)
{
optionsBuilder.UseSqlite("data source=Escuela.sqlite");
}
}

public class Alumno
{
public int Id { get; set; }
public string Nombre { get; set; } = "";
public string? Email{ get; set; }
}

public class Examen
{
public int Id { get; set; }
public int AlumnoId { get; set; }
public string Materia { get; set; } = "";
public double Nota { get; set; }
public DateTime Fecha { get; set; }
}

