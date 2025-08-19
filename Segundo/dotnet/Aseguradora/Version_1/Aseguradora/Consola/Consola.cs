using Aplicacion;
using Repositorios;

//El método a continuación elimina todos los archivos de persistencia del programa
//ReiniciarPrograma();

//Creamos los casos de uso de titulares y les insertamos dependencias
RepositorioTitular repoTitular = new RepositorioTitular();
AgregarTitularUseCase agregarTitular = new AgregarTitularUseCase(repoTitular);
ListarTitularesUseCase listarTitulares = new ListarTitularesUseCase(repoTitular);
ModificarTitularUseCase modificarTitular = new ModificarTitularUseCase(repoTitular);
EliminarTitularUseCase eliminarTitular = new EliminarTitularUseCase(repoTitular);
ListarTitularesConVehiculo listarTconV = new ListarTitularesConVehiculo(repoTitular);

//Instanciamos titulares
Titular t1 = new Titular("Ignacio", "Melo", 43905995, 2216909158, "meloignacionicolas@gmail.com", "65 9 y 10");
Titular t2 = new Titular("Josefina", "Martinez", 44590363, 2216909158, "josefinamartinez@gmail.com", "2 nro 1150");
Titular t3 = new Titular("Fabrizio", "Perri", 92306578, 2213456789, "fabri@gmail.com", "44 y 7");
Titular t4 = new Titular("Pedro", "Gonzales", 96627857, 2212109876, "pedrogonzales@gmail.com", "1 77 y 78");
Titular t5 = new Titular("Lucian", "Alma", 51585889, 2214567890, "lucianalma@gmail.com", "calle 4 número 103");
Titular t6 = new Titular("Mario", "Olmeda", 44316584, 2212345678, "marioolmeda@gmail.com", "32 nro 421");
Titular t7 = new Titular("Susana", "Natan", 60144607, 2216543210, "susananatan@gmail.com", "7 y 69");
Titular dniRepetido = new Titular("Esteban", "Gomez", 44316584, 2217848912, "estebangomez@gmail.com", "9 y 21");

//Persistimos los titulares. PersistirTitular persiste un titular pasado como parámetro.
Console.WriteLine("Persistiendo 7 titulares ");
PersistirTitular(t1);
PersistirTitular(t2);
PersistirTitular(t3);
PersistirTitular(t4);
PersistirTitular(t5);
PersistirTitular(t6);
PersistirTitular(t7);
Console.WriteLine();

//Intentamos persistir un titular cuyo DNI coincide con el de uno ya persistido. Esto da error
Console.WriteLine("Intentando instanciar un titular con DNI repetido");
PersistirTitular(dniRepetido);
Console.WriteLine();

//Listamos los titulares ya persistidos
Console.WriteLine("Listando titulares");
ListarTitulares();
Console.WriteLine();

//Creamos los casos de uso de los veículos y les insertamos dependencias
RepositorioVehiculo repoVehiculo = new RepositorioVehiculo();
AgregarVehiculoUseCase agregarVehiculo = new AgregarVehiculoUseCase(repoVehiculo);
EliminarVehiculoUseCase eliminarVehiculo = new EliminarVehiculoUseCase(repoVehiculo);
ModificarVehiculoUseCase modificarVehiculo = new ModificarVehiculoUseCase(repoVehiculo);

//PersistirVehiculo persiste un vehículo pasado como parámetro. 
Console.WriteLine("Persistiendo 3 vehículos");
PersistirVehiculo(new Vehiculo("dominioa", "BMW", 2012, 1));
PersistirVehiculo(new Vehiculo("dominiob", "Ford", 2020, 4));
PersistirVehiculo(new Vehiculo("dominioe", "Toyota", 2015, 7));
//Consideramos que un mismo titular puede tener más de un vehículo por lo que lo siguiente no debería dar error
Console.WriteLine("Persistiendo 2 vehículos del mismo titular");
PersistirVehiculo(new Vehiculo("dominioc", "Lexus", 2015, 3));
PersistirVehiculo(new Vehiculo("dominiod", "Ford", 2013, 3));
Console.WriteLine();

//Una vez persistidos algunos vehículos, listamos los titulares y sus vehículos. Aquel titular que no tenga vehículo no será listado
Console.WriteLine("Listando titulares con vehículos: ");
ListarTitularesConVehiculos();
Console.WriteLine();

//Creamos los casos de uso de las pólizas y les insertamos dependencias
RepositorioPoliza repoPoliza = new RepositorioPoliza();
AgregarPolizaUseCase agregarPoliza = new AgregarPolizaUseCase(repoPoliza);
EliminarPolizaUseCase eliminarPoliza = new EliminarPolizaUseCase(repoPoliza);
ModificarPolizaUseCase modificarPoliza = new ModificarPolizaUseCase(repoPoliza);
ListarPolizasUseCase listarPolizas =new ListarPolizasUseCase(repoPoliza);

//PersistirPoliza persiste una póliza pasada como parámetro. 
Console.WriteLine("Persistiendo 2 pólizas");
PersistirPoliza(new Poliza(5, 20000, "franquiciaa", "RC", new DateTime(2003, 12, 21), new DateTime(2023, 12, 21)));
PersistirPoliza(new Poliza(1, 3200, "franquiciab", "TR", new DateTime(2001, 06, 12), new DateTime(2021, 06, 12)));
//Consideramos que un mismo vehículo puede tener más de una póliza, por lo cual lo siguiente no da error
Console.WriteLine("Agregando dos pólizas al mismo vehículo");
PersistirPoliza(new Poliza(3, 2400, "franquiciac", "TR", new DateTime(2004, 07, 11), new DateTime(2021, 06, 12)));
PersistirPoliza(new Poliza(3, 1800, "franquiciad", "RC", new DateTime(2006, 09, 10), new DateTime(2026, 09, 10)));
//No debería ser posible persistir una póliza con tipo de póliza que no sea TR o RC, por lo que la siguiente póliza no se agrega
Console.WriteLine("Intentando persistir una póliza de tipo inválido");
PersistirPoliza(new Poliza(3, 1000, "franquiciae", "tipo incorrecto", new DateTime(2000, 05, 20), new DateTime(2020, 05, 20)));
Console.WriteLine();

//Listamos todas las polizas
Console.WriteLine("Listando pólizas: ");
ListarPolizas();
Console.WriteLine();

//Modificamos una póliza. ModificarPoliza recibe un id de póliza y abre un menú de modificación
ModificarPoliza(2);
//No se puede modificar una póliza que no exista
Console.WriteLine("Intentando modificar una póliza que no existe");
ModificarPoliza(100);
Console.WriteLine();

//Eliminamos una póliza. EliminarPoliza recibe un id de póliza y la elimina si existe
Console.WriteLine("Eliminando póliza 3");
EliminarPoliza(3);
//No se puede eliminar una póliza que no existe
Console.WriteLine("Intentando eliminar una póliza que no existe");
EliminarPoliza(100);
Console.WriteLine();

//Modificamos un vehículo. ModificarVehiculo recibe un id de vehículo y abre un menú de modificación
ModificarVehiculo(3);
//No se puede modificar un vehículo que no existe
Console.WriteLine("Intentando modificar un vehículo que no existe");
ModificarVehiculo(100);
Console.WriteLine();

//Eliminamos un vehículo. EliminarVehiculo recibe un id de vehículo y lo elimina si existe
Console.WriteLine("Eliminando vehículo 4");
EliminarVehiculo(4);
//No se puede eliminar un vehículo que no existe
Console.WriteLine("Intentando eliminar un vehículo que no existe");
EliminarVehiculo(100);
Console.WriteLine();

//Modificamos un Titular. ModificarTitular recibe un id de titular y abre un menú de modificación
ModificarTitular(5);
//No se puede modificar un vehículo que no existe
Console.WriteLine("Intentando modificar un titular que no existe");
ModificarTitular(10);
Console.WriteLine();

//Eliminamos un titular. EliminarTitular recibe un id de titular y lo elimina si existe
Console.WriteLine("Eliminando titular 3");
EliminarTitular(3);
//No se puede eliminar un titular que no existe
Console.WriteLine("Intentando eliminar un titular que no existe");
EliminarTitular(11);
Console.WriteLine();


//titular
void ListarTitulares()
{
    List<Titular> lista = listarTitulares.Ejecutar();
    foreach (Titular t in lista)
    {
        Console.WriteLine(t);
    }
}
void ListarTitularesConVehiculos()
{
    listarTconV.Ejecutar();
}

void PersistirTitular(Titular t)
{
    try
    {
        agregarTitular.Ejecutar(t);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}

void EliminarTitular(int id)
{
    try
    {
        eliminarTitular.Ejecutar(id);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}

void ModificarTitular(int id)
{
    try
    {
        modificarTitular.Ejecutar(id);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
//Vehiculo
void PersistirVehiculo(Vehiculo v)
{
    try
    {
        agregarVehiculo.Ejecutar(v);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
void EliminarVehiculo(int id)
{
    try
    {
        eliminarVehiculo.Ejecutar(id);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
void ModificarVehiculo(int id)
{
    try
    {
        modificarVehiculo.Ejecutar(id);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
void PersistirPoliza(Poliza p)
{
    try
    {
        agregarPoliza.Ejecutar(p);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
void ModificarPoliza(int id)
{
    try
    {
        modificarPoliza.Ejecutar(id);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
void EliminarPoliza(int id)
{
    try
    {
        eliminarPoliza.Ejecutar(id);
    }
    catch (Exception e)
    {
        Console.WriteLine(e.Message);
    }
}
void ListarPolizas()
{
    List<Poliza> lista = listarPolizas.Ejecutar();
    foreach (Poliza p in lista)
    {
        Console.WriteLine(p.ToString());
    }
}
void ReiniciarPrograma()
{
    Console.WriteLine("Ingrese 'y' para borrar todos los archivos de persistencias y 'n' para cancelar: ");
    string opcion= Console.ReadLine()?? " ";
    while (opcion.ToLower() != "n")
    {
        if (opcion=="y"){
            File.Delete("../Repositorios/Persistencia/Titulares.txt");
            File.Delete("../Repositorios/Persistencia/Vehiculos.txt");
            File.Delete("../Repositorios/Persistencia/IDs.txt");
            File.Delete("../Repositorios/Persistencia/Polizas.txt");
            break;
        }
    }
}