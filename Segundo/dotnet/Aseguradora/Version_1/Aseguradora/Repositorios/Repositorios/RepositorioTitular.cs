namespace Repositorios;
using Aplicacion;

public class RepositorioTitular : IRepositorioTitular
{
    private static readonly string s_PathTitulares = "../Repositorios/Persistencia/Titulares.txt";
    private static readonly string s_PathTitularesAux = "../Repositorios/Persistencia/Titulares1.txt";

    //Recibe un titular y lo agrega si no está en el archivo de titulares
    public void AgregarTitularUseCase(Titular t)
    {
        if (Metodos.ExisteTitularDNI(t.DNI)) //Revisamos la existencia del titular
            Console.WriteLine("El dni ingresado ya está en el archivo");
        else
        {
            int[] ID = Metodos.LeerID(); //Leemos el último id persistido
            using (StreamWriter? sw = new StreamWriter(s_PathTitulares, true))
            {
                try
                {
                    ID[0]++; //Incrementamos la cantidad total de titulares
                    sw.WriteLine($"{ID[0]} | {t.Nombre} | {t.Apellido} | {t.DNI} | {t.Telefono} | {t.Correo} | {t.Direccion}"); //Persistimos el titular 
                    Metodos.EscribirID(ID); //Persistimos el id
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al escribir en el archivo de titulares: " + ex.Message);
                }
            }
            Console.WriteLine($"El titular con el dni: {t.DNI} fue agregado con exito!");
        }
    }

    //Lista todos los titulares del archivo
    public List<Titular> ListarTitularesUseCase()
    {
        List<Titular> lista = new List<Titular>();
        if (File.Exists(s_PathTitulares))
        {
            using (StreamReader sr = new StreamReader(s_PathTitulares, true))
            {
                try
                {
                    Titular aux;
                    String[] vec;
                    while (!sr.EndOfStream)
                    {
                        string linea = sr.ReadLine() ?? " "; //Lectura de la línea comprobando que no sea null
                        vec = linea.Split(" | "); //Separamos la string con barras
                        aux = new Titular(vec[1], vec[2], int.Parse(vec[3]), long.Parse(vec[4]), vec[5], vec[6]); //Guardamos los datos del titular leido
                        aux.setId(int.Parse(vec[0])); //Le asignamos el id leído (debemos hacerlo aparte ya que el constructor no recibe id)
                        lista.Add(aux);
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al escribir en el archivo de titulares: " + ex.Message);
                }
            }
        }
        else
        {
            Console.WriteLine("No hay titulares persisistidos!");
        }
        return lista;
    }

    //Recibe un id de titular y lo modifica si existe
    public void ModificarTitularUseCase(int id)
    {
        int pos = 0;
        Titular? aux = Metodos.BuscarTitular(id, ref pos);
        if ((aux) != null)
        {
            string? linea;
            try
            {
                using (StreamReader sr = new StreamReader(s_PathTitulares, true))
                {
                    menuModificacion(ref aux); //Menu para el usuario
                    using (StreamWriter sw = new StreamWriter(s_PathTitularesAux, true)) //Creamos un archivo nuevo al que le escribiremos los datos actualizados
                    { 
                        for (int lineaActual = 0; lineaActual < pos; lineaActual++) // Copiar en un nuevo archivo las lineas previas al titular a modificar
                        {
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                        sw.WriteLine($"{aux.ID} | {aux.Nombre} | {aux.Apellido} | {aux.DNI} | {aux.Telefono} | {aux.Correo} | {aux.Direccion}"); //Escribimos el titular modificado
                        sr.ReadLine();
                        while (!sr.EndOfStream) //Escribimos titulares posteriores
                        { 
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                    }
                }
                File.Delete(s_PathTitulares); //Borramos el archivo desactualizo
                System.IO.File.Move(s_PathTitularesAux, s_PathTitulares); //Renombramos el archivo actualizado
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al escribir en el archivo de titulares: " + ex.Message);
            }
        }
        else
            Console.WriteLine("El titular que se quiere modificar no existe!");
    }
    private void menuModificacion(ref Titular t)
    {
        int opcion;
        Console.WriteLine($"Seleccione qué dato desea modificar del titular {t.ID}:");
        Console.Write("Ingrese '1' para modificar teléfono, '2' para correo, '3' para dirección y '4' para dejar de modificar el titular: ");
        opcion = int.Parse(Console.ReadLine() ?? "");
        if (opcion != 4)
        {
            switch (opcion)
            {
                case 1:
                    {
                        try
                        {
                            Console.Write("Ingrese el nuevo teléfono: ");
                            t.Telefono = long.Parse(Console.ReadLine() ?? "");
                        }
                        catch
                        {
                            Console.WriteLine("Valor no permitido");
                        }
                        break;
                    }
                case 2:
                    {
                        try
                        {
                            Console.Write("Ingrese el nuevo correo: ");
                            t.Correo = Console.ReadLine();
                        }
                        catch
                        {
                            Console.WriteLine("Valor no permitido!");
                        }
                        break;
                    }
                case 3:
                    {
                        try
                        {
                            Console.Write("Ingrese la nueva dirección: ");
                            t.Direccion = Console.ReadLine();
                        }
                        catch
                        {
                            Console.WriteLine("Valor no permitido!");
                        }
                        break;
                    }
                default:
                    Console.WriteLine("La opción ingresada es inválida!");
                    break;
            }
            menuModificacion(ref t);
        }
    }

    //Recibe un id de titular y lo elimina si existe
    public void EliminarTitularUseCase(int id)
    {
        int pos = 0;
        Titular? aux = Metodos.BuscarTitular(id, ref pos);
        if ((aux) != null)
        {
            string? linea;
            try
            {
                using (StreamReader sr = new StreamReader(s_PathTitulares, true))
                {
                    using (StreamWriter sw = new StreamWriter(s_PathTitularesAux, true)) //Creamos un nuevo archivo para volcar en él todos los datos menos el que se desea eliminar
                    { 
                        for (int lineaActual = 0; lineaActual < pos; lineaActual++) //Copia en un archivo las líneas hasta llegar a la que hay que borrar
                        { 
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                        sr.ReadLine(); //Lee pero no escribe la que se desea eliminar
                        while (!sr.EndOfStream) //Se copía el resto del archivo hasta el final
                        { 
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                    }
                }
                File.Delete(s_PathTitulares); //Borramos el archivo viejo
                System.IO.File.Move(s_PathTitularesAux, s_PathTitulares); //Renombra el actualizado
                Console.WriteLine("El titular fue eliminado con exito!");
            }
            catch (Exception ex)
            {
                Console.WriteLine("El titular no fue eliminado: " + ex.Message);
            }
        }
        else
            Console.WriteLine("El titular que se quiere eliminar no existe!");
    }

    //Lista todos los titulares del archivo que tienen vehículos
    public void ListarTitularesConVehiculo()
    {
        if (File.Exists(s_PathTitulares))
        {
            try
            {
                using (StreamReader sr = new StreamReader(s_PathTitulares, true))
                {
                    while (!sr.EndOfStream)
                    {//leemos todos los titulares
                        string s = sr.ReadLine() ?? " "; //cargamos el titutal persistido
                        string[] vec = s.Split(" | "); //lo spliteamos
                        Titular aux = new Titular(vec[1], vec[2], int.Parse(vec[3]), long.Parse(vec[4]), vec[5], vec[6]);//lo ponemos en un aux
                        aux.ID = int.Parse(vec[0]); //le ponemos su id
                        List<Vehiculo> l = RepositorioVehiculo.Listar(int.Parse(vec[0])); //llamamos a la funcion que busca si tiene autos
                        if (l.Count != 0) //si tiene printea los datos correspondientes
                        {
                            Console.WriteLine(aux.ToString());
                            foreach (var v in l)
                            {
                                Console.WriteLine(v.ToString());
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }
        else
            Console.WriteLine("No hay titulares persistidos!");
    }
}
