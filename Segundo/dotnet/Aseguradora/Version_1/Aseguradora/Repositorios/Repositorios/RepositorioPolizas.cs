namespace Repositorios;
using Aplicacion;

public class RepositorioPoliza : IRepositorioPoliza
{
    public static readonly string s_PathPolizas = "../Repositorios/Persistencia/Polizas.txt";
    public static readonly string s_PathPolizasAux = "../Repositorios/Persistencia/Polizas1.txt";

    //Recibe una póliza e intenta agregarla al archivo de pólizas
    public void AgregarPolizaUseCase(Poliza p)
    {
        if (!Metodos.ExisteVehiculoID(p.IDVehiculo))//revisamos que exista un vehiculo con el id al que se le quiere asignar la poliza
            Console.WriteLine("El ID ingresado no se encuentra en la base de datos");
        else
        {
            int[] vec = Metodos.LeerID(); //Traemos los ids persistidos
            using (StreamWriter? sw = new StreamWriter(s_PathPolizas, true))
            {
                try
                {
                    if (new[] { "responsabilidad civil", "todo riesgo", "tr", "rc" }.Contains(p.TCobertura, StringComparer.OrdinalIgnoreCase))
                    {
                        vec[2]++; //Incrementamos el contador de polizas
                        sw.WriteLine($"{vec[2]} | {p.ValorAsegurado} | {p.Franquicia} | {p.TCobertura} | {p.InicioVigencia:dd/MM/yyyy} | {p.FinVigencia:dd/MM/yyyy} | {p.IDVehiculo}"); //Escribimos la nueva poliza
                        Metodos.EscribirID(vec);
                    }
                    else
                        throw new Exception();
                }
                catch
                {
                    throw new Exception("No se pudo escribir en el archivo de pólizas!!");
                }
            }
            Console.WriteLine($"Poliza del vehiculo {p.IDVehiculo} agregada con exito!");
        }
    }

    //Recibe un id de póliza y la elimina si existe
    public void EliminarPolizaUseCase(int id)
    {
        int pos = 0;
        Poliza? aux = Metodos.BuscarPoliza(id, ref pos);
        if ((aux) != null)
        {
            string? linea;
            try
            {
                using (StreamReader sr = new StreamReader(s_PathPolizas, true))
                {
                    using (StreamWriter sw = new StreamWriter(s_PathPolizasAux, true)) //Creamos un nuevo archivo para volcar en él todos los datos menos el que se desea eliminar
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
                File.Delete(s_PathPolizas); //Borramos el archivo viejo
                System.IO.File.Move(s_PathPolizasAux, s_PathPolizas); //Renombra el actualizado
                Console.WriteLine("Poliza eliminada con exito!");
            }
            catch (Exception ex)
            {
                Console.WriteLine("La póliza no fue eliminada: " + ex.Message);
            }
        }
        else
            Console.WriteLine("La póliza que se quiere eliminar no existe!");
    }

    //Recibe un id de póliza y abre un menú de modificación si existe
    public void ModificarPolizaUseCase(int id)
    {
        int pos = 0;
        Poliza? aux = Metodos.BuscarPoliza(id, ref pos);
        if ((aux) != null)
        {
            string? linea;
            try
            {
                using (StreamReader sr = new StreamReader(s_PathPolizas, true))
                {
                    menuModificacion(ref aux); //Menu para el usuario
                    using (StreamWriter sw = new StreamWriter(s_PathPolizasAux, true))//Creamos un archivo nuevo al que le escribiremos los datos actualizados
                    {
                        for (int lineaActual = 0; lineaActual < pos; lineaActual++)// Copiar en un nuevo archivo las lineas previas a la poliza a modificar
                        {
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                        sw.WriteLine($"{aux.ID} | {aux.ValorAsegurado} | {aux.Franquicia} | {aux.TCobertura} | {aux.InicioVigencia:dd/MM/yyyy} | {aux.FinVigencia:dd/MM/yyyy} | {aux.IDVehiculo}"); //Escribimos el titular modificado
                        sr.ReadLine();
                        while (!sr.EndOfStream) //Escribimos polizas posteriores
                        {
                            linea = sr.ReadLine();
                            sw.WriteLine(linea);
                        }
                    }
                }
                File.Delete(s_PathPolizas); //Borramos el archivo desactualizo
                System.IO.File.Move(s_PathPolizasAux, s_PathPolizas); //Renombramos el archivo actualizado
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al escribir en el archivo de pólizas: " + ex.Message);
            }
        }
        else
            Console.WriteLine("La póliza que se quiere modificar no existe!");
    }

    private void menuModificacion(ref Poliza p)
    {
        int opcion;
        Console.WriteLine($"Seleccione qué dato desea modificar de la póliza {p.ID}:");
        Console.Write("Ingrese '1' para valor asegurado, '2' para tipo de cobertura, '3' para franquicia y '4' para dejar de modificar la póliza: ");
        opcion = int.Parse(Console.ReadLine() ?? "");
        if (opcion != 4)
        {
            switch (opcion)
            {
                case 1:
                    {
                        try
                        {
                            Console.Write("Ingrese el nuevo valor asegurado: ");
                            p.ValorAsegurado = float.Parse(Console.ReadLine() ?? "");
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
                            Console.Write("Ingrese la nueva cobertura: ");
                            String aux = (Console.ReadLine() ?? " ");
                            if (new[] { "responsabilidad civil", "todo riesgo", "tr", "rc" }.Contains(aux, StringComparer.OrdinalIgnoreCase))
                                p.TCobertura = aux;
                            else
                            {
                                throw new Exception();
                            }
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
                            Console.Write("Ingrese la nueva franquicia: ");
                            p.Franquicia = Console.ReadLine();
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
            menuModificacion(ref p);
        }
    }
    public List<Poliza> ListarPolizasUseCase()
    {
        List<Poliza> lista = new List<Poliza>();
        if (File.Exists(s_PathPolizas))
        {
            using (StreamReader sr = new StreamReader(s_PathPolizas, true))
            {
                try
                {
                    Poliza aux;
                    String[] vec;
                    while (!sr.EndOfStream)
                    {
                        string linea = sr.ReadLine() ?? " "; //Lectura de la línea comprobando que no sea null
                        vec = linea.Split(" | "); //Separamos la string con barras
                        string a = vec[4];
                        String[] avec = a.Split("/");
                        DateTime adate = new DateTime(int.Parse(avec[2]), int.Parse(avec[1]), int.Parse(avec[0])); //inicio
                        string b = vec[5];
                        String[] bvec = b.Split("/");
                        DateTime bdate = new DateTime(int.Parse(avec[2]), int.Parse(avec[1]), int.Parse(avec[0])); //fin
                        aux = new Poliza(int.Parse(vec[0]), float.Parse(vec[1]), vec[2], vec[3], adate, bdate); //Guardamos los datos del  leido
                        lista.Add(aux);
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al escribir en el archivo de Polizas: " + ex.Message);
                }
            }
        }
        else
        {
            Console.WriteLine("No hay polizas persisistidos!");
        }
        return lista;
    }
}