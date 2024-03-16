namespace Repositorios;
using Aplicacion;

public class RepositorioVehiculo : IRepositorioVehiculo
{
    private static readonly string s_PathVehiculos = "../Repositorios/Persistencia/Vehiculos.txt";
    private static readonly string s_PathVehiculosAux = "../Repositorios/Persistencia/Vehiculos1.txt";

    //Recibe un vehículo y lo agrega si no existe
    public void AgregarVehiculoUseCase(Vehiculo v)
    {
        if (!Metodos.ExisteTitularID(v.IDTitular))//revisamos que exista un titular con el id dado
            Console.WriteLine("El ID de titular ingresado no se encuentra en la base de datos");
        else
        {
            int[] vec = Metodos.LeerID(); //Traemos los ids persistidos
            using (StreamWriter? sw = new StreamWriter(s_PathVehiculos, true))
            {
                try
                {
                    vec[1]++; //Incrementamos el contador del último id de vehículo
                    sw.WriteLine($"{vec[1]} | {v.Marca} | {v.Dominio} | {v.Fabricacion} | {v.IDTitular}"); //Escribimos el nuevo vechículo
                    Metodos.EscribirID(vec);
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al escribir en el archivo vehículo: " + ex.Message);
                }
            }
            Console.WriteLine($"Vehiculo del titular {v.IDTitular} agregado con exito!");
        }
    }

    //Recibe un id de vehículo y lo elimina si existe
    public void EliminarVehiculoUseCase(int id)
    {
        int pos = 0;
        Vehiculo? aux = Metodos.BuscarVehiculo(id, ref pos);
        if ((aux) != null)
        {
            string? linea;
            try
            {
                using (StreamReader sr = new StreamReader(s_PathVehiculos, true))
                {
                    using (StreamWriter sw = new StreamWriter(s_PathVehiculosAux, true))//Creamos un nuevo archivo para volcar en él todos los datos menos el que se desea eliminar
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
                File.Delete(s_PathVehiculos); //Borramos el archivo viejo
                System.IO.File.Move(s_PathVehiculosAux, s_PathVehiculos); //Renombra el actualizado
                Console.WriteLine("El vehiculo fue eliminado con exito!");
            }
            catch (Exception ex)
            {
                Console.WriteLine("El vehículo no fue eliminado: " + ex.Message);
            }
        }
        else
            Console.WriteLine("El vehículo que se quiere eliminar no existe!!");
    }

    //Recibe un id de vehículo y lo modifica si existe
    public void ModificarVehiculoUseCase(int id)
    {
        int pos = 0;
        Vehiculo? aux = Metodos.BuscarVehiculo(id, ref pos);
        if ((aux) != null)
        {
            string? linea;
            try
            {
                using (StreamReader sr = new StreamReader(s_PathVehiculos, true))
                {
                    menuModificacion(ref aux); //Menu para el usuario
                    using (StreamWriter sw = new StreamWriter(s_PathVehiculosAux, true)) //Creamos un archivo nuevo al que le escribiremos los datos actualizados
                    { 
                        for (int lineaActual = 0; lineaActual < pos; lineaActual++) // Copiar en un nuevo archivo las lineas previas al vehículo a modificar
                        { 
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                        sw.WriteLine($"{aux.ID} | {aux.Marca} | {aux.Dominio} | {aux.Fabricacion} | {aux.IDTitular}"); //Escribimos el titular modificado
                        sr.ReadLine();
                        while (!sr.EndOfStream) //Escribimos titulares posteriores
                        { 
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                    }
                }
                File.Delete(s_PathVehiculos); //Borramos el archivo desactualizo
                System.IO.File.Move(s_PathVehiculosAux, s_PathVehiculos); //Renombramos el archivo actualizado
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al escribir en el archivo de vehículos: " + ex.Message);
            }
        }
        else
            Console.WriteLine("El vehículo que se quiere modificar no existe!!");
    }

    private void menuModificacion(ref Vehiculo v)
    {
        int opcion;
        Console.WriteLine($"Seleccione qué dato desea modificar del vehículo {v.ID}");
        Console.Write("Ingrese '1' para marca, '2' para dominio, '3' para anio de fabricación y '4' para dejar de modificar el vehiculo: ");
        opcion = int.Parse(Console.ReadLine() ?? "");
        if (opcion != 4)
        {
            switch (opcion)
            {
                case 1:
                    {
                        try
                        {
                            Console.Write("Ingrese la nueva marca: ");
                            v.Marca = Console.ReadLine() ?? "";
                        }
                        catch
                        {
                            Console.WriteLine("Valor no permitido!");
                        }
                        break;
                    }
                case 2:
                    {
                        try
                        {
                            Console.Write("Ingrese el nuevo dominio: ");
                            v.Dominio = Console.ReadLine();
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
                            Console.Write("Ingrese la nueva fecha de fabricación: ");
                            v.Fabricacion = int.Parse(Console.ReadLine() ?? " ");
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
            menuModificacion(ref v);
        }
    }

    //Lista todos los vehículos
    public static List<Vehiculo> Listar(int id)
    {
        List<Vehiculo> lista = new List<Vehiculo>();//lista auxiliar
        if (File.Exists(s_PathVehiculos))
        {
            try
            {
                using (StreamReader sr = new StreamReader(s_PathVehiculos, true))
                {
                    while (!sr.EndOfStream) //leemos todo el archivo de vehiculos persistidos
                    { 
                        string s = sr.ReadLine() ?? " "; //se splitea 
                        string[] vec = s.Split(" | ");
                        Vehiculo aux = new Vehiculo(vec[2], vec[1], int.Parse(vec[3]), int.Parse(vec[4])); //Vehiculo que se va a agregar a la lista
                        if (aux.IDTitular == id)  //Si tiene el id del titular se agrega a la lista
                            lista.Add(aux);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error " + e.Message);
            }
        }
        else
        {
            Console.WriteLine("No existen vehiculos persistidos!");
        }
        return lista; //Devolvemos la lista de vehiculos
    }
}