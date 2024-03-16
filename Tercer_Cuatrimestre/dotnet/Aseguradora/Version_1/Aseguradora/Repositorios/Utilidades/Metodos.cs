namespace Repositorios;
using Aplicacion;
public static class Metodos
{
    private static int[] vec = new int[5];
    private static readonly string s_pathTitular = "../Repositorios/Persistencia/Titulares.txt";
    private static readonly string s_pathID = "../Repositorios/Persistencia/IDs.txt";
    private static readonly string s_pathVehi = "../Repositorios/Persistencia/Vehiculos.txt";
    private static readonly string s_pathPolizas = "../Repositorios/Persistencia/Polizas.txt";

//Lee los últimos IDs persistidos
    public static int[] LeerID() 
    { 
        if (File.Exists(s_pathID))
        {
            using (StreamReader? sr = new StreamReader(s_pathID, true))
            {
                try
                {
                    string s = sr.ReadLine() ?? "";
                    string[] vecs = s.Split(" "); //Lee la línea con todos los ids
                    for (int i = 0; i < vecs.Length; i++)
                        vec[i] = int.Parse(vecs[i]); //Coloca en un vector los ids
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al leer los ids: " + ex.Message);
                }
            }
        }
        return vec;
    }

    //Actualiza el archivo de IDs
    public static void EscribirID(int[] vec) 
    { 
        using (StreamWriter? sw2 = new StreamWriter(s_pathID))
        {
            try
            {
                sw2.WriteLine(vec[0] + " " + vec[1] + " " + vec[2] + " " + vec[3] + " " + vec[4]);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al escribir los ids: " + ex.Message);
            }
        }
    }

    //Retorna true si hay un titular con el dni dado, false en caso contrario
    public static Boolean ExisteTitularDNI(int DNI) 
    { 
        Boolean esta = false;
        if (File.Exists(s_pathTitular))
        {
            try
            {
                using (StreamReader sr = new StreamReader(s_pathTitular, true))
                {
                    string[] vec;
                    while (!sr.EndOfStream & !esta)
                    {
                        string linea = sr.ReadLine() ?? " ";
                        vec = linea.Split(" | "); //Tomamos la linea y la colocamos en un vector 
                        if (int.Parse(vec[3]) == DNI) //Cuando encontramos el dato buscado, activamos la variable
                            esta = true;
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error al buscar titular: " + e.Message);
            }
        }
        return esta;
    }

    //Retorna true si hay un titular con el dni dado, false en caso contrario
    public static Boolean ExisteTitularID(int ID) 
    { 
        Boolean esta = false;
        if (File.Exists(s_pathTitular))
        {
            try
            {
                using (StreamReader sr = new StreamReader(s_pathTitular, true))
                {
                    string[] vec;
                    while (!sr.EndOfStream & !esta)
                    {
                        string linea = sr.ReadLine() ?? " ";
                        vec = linea.Split(" | "); //Tomamos la linea y la colocamos en un vector 
                        if (int.Parse(vec[0]) == ID) //Cuando encontramos el dato buscado, activamos la variable
                            esta = true;
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error al buscar titular: " + e.Message);
            }
        }
        return esta;
    }

//Devuelve el titular con el id pasado como parámetro y su posición dentro del archivo o null en caso de no encontrarlo. 
    public static Titular? BuscarTitular(int id, ref int pos) 
    {
        Titular? aux = null;
        try
        {
            using (StreamReader sr = new StreamReader(s_pathTitular, true))
            {
                Boolean esta = false;
                string[] vec;
                while (!sr.EndOfStream & !esta)
                {
                    string? linea = sr.ReadLine() ?? " ";
                    vec = linea.Split(" | ");
                    if (int.Parse(vec[0]) == id) 
                    { 
                        aux = new Titular(vec[1], vec[2], int.Parse(vec[3]), long.Parse(vec[4]), vec[5], vec[6]); //Instancio un titular con los datos leidos
                        aux.ID = int.Parse(vec[0]);
                        esta = true;
                        return aux;
                    }
                    pos++; //Devolvemos una int con la posición en la que el dato fue encontrado
                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error al buscar titular: " + e.Message);
        }
        return aux;
    }

    //Devuelve el Vehiculo con el id pasado como parámetro y su posición dentro del archivo o null en caso de no encontrarlo. 
    public static Vehiculo? BuscarVehiculo(int id, ref int pos)
    {
        Vehiculo? aux = null;
        try
        {
            using (StreamReader sr = new StreamReader(s_pathVehi, true))
            {
                Boolean esta = false;
                string[] vec;
                while (!sr.EndOfStream & !esta) //Mientras no se haya terminado el archivo y no hayamos encontrado el dato leemos
                {
                    string? linea = sr.ReadLine() ?? " ";
                    vec = linea.Split(" | ");
                    if (int.Parse(vec[0]) == id) //Comparamos con el ID pasado como parámetro
                    { 
                        aux = new Vehiculo(vec[2], vec[1], int.Parse(vec[3]), int.Parse(vec[4])); //Instaciamos un auto con los datos leidos
                        aux.ID = int.Parse(vec[0]);
                        esta = true;
                        return aux; //Retornamos el vehículo
                    }
                    pos++;
                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error al buscar vehiculo: " + e.Message);
        }
        return aux;
    }

    //Devuelve true si el vehiculo con el id pasado se encuentra en el archivo, false en caso contrario.
    public static Boolean ExisteVehiculoID(int ID)
    { 
        Boolean esta = false;
        if (File.Exists(s_pathVehi))
        {
            try
            {
                using (StreamReader sr = new StreamReader(s_pathVehi, true))
                {
                    string[] vec;
                    while (!sr.EndOfStream & !esta)
                    {
                        string linea = sr.ReadLine() ?? " ";
                        vec = linea.Split(" | "); //Tomamos la linea y la colocamos en un vector 
                        if (int.Parse(vec[0]) == ID) //Cuando encontramos el dato buscado, activamos la variable
                            esta = true;
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error al buscar vehiculo: " + e.Message);
            }
        }
        return esta;
    }

    //Devuelve la poliza con el id pasado como parámetro y su posición dentro del archivo o null en caso de no encontrarla. 
    public static Poliza? BuscarPoliza(int id, ref int pos)
    {
        Poliza? aux = null;
        try
        {
            using (StreamReader sr = new StreamReader(s_pathPolizas, true))
            {
                Boolean esta = false;
                string[] vec;
                while (!sr.EndOfStream & !esta) //Mientras no se haya terminado el archivo y no hayamos encontrado el dato leemos
                {
                    string? linea = sr.ReadLine() ?? " ";
                    vec = linea.Split(" | ");
                    if (int.Parse(vec[0]) == id) //Comparamos con el ID pasado como parámetro
                    { 
                        aux = new Poliza(int.Parse(vec[6]), float.Parse(vec[1]), vec[2], vec[3], DateTime.Parse(vec[4]), DateTime.Parse(vec[5])); //Instancia una poliza con los datos
                        aux.ID = int.Parse(vec[0]);
                        esta = true;
                        return aux; //Retornamos la póliza
                    }
                    pos++; //Devolvemos una int con la posición en la que el dato fue encontrado
                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error al buscar poliza: " + e.Message);
        }
        return aux; //Si se encuentra retorna el dato, en caso contrario null
    }
}