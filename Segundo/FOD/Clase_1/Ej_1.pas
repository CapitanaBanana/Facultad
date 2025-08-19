{Realizar un algoritmo que cree un archivo de números enteros no ordenados y permita
incorporar datos al archivo. Los números son ingresados desde teclado. El nombre del
archivo debe ser proporcionado por el usuario desde teclado. La carga finaliza cuando
se ingrese el número 30000, que no debe incorporarse al archivo.}
program ejercicio_1;
type archivo= file of integer;
cadena= string[20];

procedure cargar_datos(var arch:archivo);
var num:integer;
begin
	writeln('Ingrese numeros (0 para terminar):');
	readln(num);
	while(num<>0) do
	begin
		write(arch,num);//escribo en el archivo
		readln(num);
	end;
	close(arch);//cierro el archivo
end;
procedure crear_archivo(var arch:archivo);
var nom:cadena;
begin
	writeln('Ingrese el nombre del archivo:');
	readln(nom);
	assign(arch,nom);//asigno nom fisico a arch logico
	reset(arch);//abro archivo como solo escritura
end;

var arch: archivo; 
begin
	crear_archivo(arch);
	cargar_datos(arch);
end.
