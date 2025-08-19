using Teoria_11;
using Microsoft.Extensions.DependencyInjection;

var servicios = new ServiceCollection();
servicios.AddTransient<IServicioX, ServicioX>();
servicios.AddSingleton<ILogger, LoggerNum>();

var proveedor = servicios.BuildServiceProvider();

IServicioX? servicio = proveedor?.GetService<IServicioX>();
servicio?.Ejecutar();
proveedor?.GetService<ILogger>()?.Log("Fin del programa");