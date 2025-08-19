{Realizar un algoritmo, que utilizando el archivo de números enteros no ordenados
creados en el ejercicio 1, informe por pantalla cantidad de números menores a 1500 y el
promedio de los números ingresados. El nombre del archivo a procesar debe ser
proporcionado por el usuario una única vez. Además, el algoritmo deberá listar el
contenido del archivo en pantalla.}
program ejercicio_2;
type 
	archivo= file of integer;
procedure abrir_archivo(var arch:archivo);
var nom: string[20];
begin
	writeln('Ingrese el nombre del archivo a abrir: ');
	readln(nom);
	assign(arch,nom);//nom fisico->nom logico
	reset(arch);//abro lecto-escritura
end;

procedure procesar_archivo(var arch:archivo);
var nro,cant,suma,i:integer;
begin
	i:=1;
	suma:=0;
	cant:=0;
	while not(EOF(arch)) do//recorro mientras no eof
	begin
		read(arch,nro);
		if (nro<1500) then
			cant:=cant+1;
		writeln(i,': ',nro);
		suma:=suma+nro;
		i:=i+1;
	end;
	writeln('Cantidad de numeros menores a 1500: ', cant);
	writeln('Promedio de los numeros ingresados: ', (suma/(filesize(arch))):0:2);
	close(arch);//cierro archivo
end;
var arch:archivo;
begin
	abrir_archivo(arch);
	procesar_archivo(arch);
end.

