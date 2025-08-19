{Realizar un programa que presente un menú con opciones para:
a. Crear un archivo de registros no ordenados de empleados y completarlo con
datos ingresados desde teclado. De cada empleado se registra: número de
empleado, apellido, nombre, edad y DNI. Algunos empleados se ingresan con
DNI 00. La carga finaliza cuando se ingresa el String ‘fin’ como apellido.
b. Abrir el archivo anteriormente generado y
i. Listar en pantalla los datos de empleados que tengan un nombre o apellido
determinado.
ii. Listar en pantalla los empleados de a uno por línea.
iii. Listar en pantalla empleados mayores de 70 años, próximos a jubilarse.}
program ejercicio_3;
type
	cadena=string[20];
	empleado= record
		num:integer;
		ape:cadena;
		nom:cadena;
		edad:integer;
		dni:integer;
	end;
	archivo= file of empleado;
	
var nom:cadena; e:empleado;
function leer_empleado():empleado;
begin
	write('Apellido: ');
	readln(e.ape);
	if (e.ape<>'fin') then
	begin
		write('nombre: ');
		readln(e.nom);
		write('edad: ');
		readln(e.edad);
		write('dni: ');
		readln(e.dni);
		write('numero de empleado: ');
		readln(e.num);
		writeln('____________________________________________________________________________________')
	end;
	leer_empleado:=e;
end;
procedure crear_archivo();
var arch:archivo;
procedure cargar_datos();
begin 
	e:=leer_empleado();
	while (e.ape<> 'fin') do
	begin
		write(arch, e);
		e:=leer_empleado();
	end;
	close(arch);
end;
begin
	writeln('Ingrese un nombre para el archivo: ');
	readln(nom);
	assign(arch,nom);
	rewrite(arch);
	cargar_datos();
end;

procedure abrir_archivo(var arch:archivo);
var nom:cadena;
begin
	writeln();
	writeln('Ingrese el nombre del archivo a abrir: ');
	readln(nom);
	assign(arch,nom);
	reset(arch);
end;
procedure buscar_empleado();
procedure buscar_nombre(var arch:archivo);
var nombre: cadena; e:empleado;
begin
	writeln();
	write('Ingrese el nombre a buscar: ');
	readln(nombre);
	while not(EOF(arch)) do
	begin
		read(arch,e);
		if (e.nom=nombre) then
			writeln(e.nom,' ', e.ape);
	end;
	writeln();
end;
procedure buscar_apellido(var arch:archivo);
var apellido: cadena; e:empleado;
begin
	writeln();
	write('Ingrese el apellido a buscar: ');
	readln(apellido);
	while not(EOF(arch)) do
	begin
		read(arch,e);
		if (e.ape=apellido) then
			writeln(e.nom,' ', e.ape);
	end;
	writeln();
end;
var arch: archivo;opcion: char;
begin
	abrir_archivo(arch);
	write('Ingrese "a" para buscar por nombre y "b" para buscar por apellido: ');
	readln(opcion);
	while ((opcion <> 'a') and (opcion <> 'b')) do
	begin
		writeln('Ingrese una opcion valida: ');
		readln(opcion);
	end;
	case opcion of
	'a': buscar_nombre(arch);
	'b': buscar_apellido(arch);
	end;
	close(arch);
end;	
procedure listar_empleados();
var arch:archivo; e:empleado;
begin
	abrir_archivo(arch);
	while not(EOF(arch)) do//recorro mientras no eof
	begin
		read(arch,e);
		writeln('-', e.num, '| ',e.nom, ' ', e.ape,' | ', e.edad, ' anios.');
	end;
	writeln();             
	close(arch);//cierro archivo
end;

procedure mayores();
var arch:archivo;e:empleado;
begin
	abrir_archivo(arch);
	while not(EOF(arch)) do //recorro mientras no eof
	begin
		read(arch,e);
		if (e.edad>60) then
			writeln(e.nom, ' ', e.ape,' | ', e.edad, ' anios.');
	end;
	writeln();
	close(arch);//cierro archivo
end;
procedure agregar();
var arch:archivo; e, e_arch:empleado; repetido:boolean;
begin
	abrir_archivo(arch);
	repetido:=false;
	writeln('Ingrese empleados. Use "fin" como apellido para finalizar la carga');
	e:=leer_empleado();
	while (e.ape<>'fin') do
	begin
		seek(arch,0);
		while (not(EOF(arch)) and (repetido= false))do //recorro mientras no eof
		begin
			read(arch,e_arch);
			if (e_arch.num = e.num) then 
			begin
				writeln('Error. Ya existe un empleado con ese codigo');
				repetido:=true;
			end;
		end;
		if (repetido=false) then 
			write(arch, e);
		repetido:=false;
		e:=leer_empleado();
	end;
	close(arch);
end;
procedure modificar_edad();
var arch:archivo; e:empleado;cod:integer; modificado:boolean;
begin
	abrir_archivo(arch);
	modificado:=false;
	write('Ingrese el numero de empleado cuya edad desea editar (cod 999 para terminar)');
	readln(cod);
	while (cod<>999) do
	begin
		seek(arch,0);
		while (not(eof(arch)) and (modificado=false)) do
		begin
			read(arch,e);
			if (e.num=cod) then
			begin
				writeln(e.nom, ' ', e.ape,' | ', e.edad, ' anios.');
				write('Ingrese la nueva edad del empleado: ');
				readln(e.edad);
				seek(arch, (filepos(arch)-1));
				write(arch,e);
				modificado:=true;
			end;
		end;
		if (modificado=false) then
			writeln('El numero de empleado ingresado no esta en el archivo')
		else 
			modificado:=false;
		write('Ingrese el numero de empleado cuya edad desea editar (cod 999 para terminar)');
		readln(cod);
	end;
	close(arch);
end;

procedure exportar_todo();
var arch:archivo; texto:text; e: empleado;
begin
	abrir_archivo(arch);
	assign(texto,'todos_empleados.txt');
	rewrite(texto);
	while (not eof(arch))do 
	begin
		read(arch, e);
		with e do
			writeln(texto,' | numero: ',num,' | apellido: ',ape,' | nombre: ',nom,' | edad: ',edad,' | dni:',dni)
	end;
	close(arch);
	close(texto);
end;

procedure exportar_00();
var arch:archivo; texto:text; e:empleado;
begin
	abrir_archivo(arch);
	assign(texto,'faltaDNIEmpleado.txt');
	rewrite(texto);
	while (not eof(arch)) do
	begin
		read(arch, e);
		if (e.dni=00) then
		begin
			writeln('entro');
			with e do
				writeln(texto,' | numero: ',num,' | apellido: ',ape,' | nombre: ',nom,' | edad: ',edad,' | dni:',dni);
		end;
	end;
	close(arch);
	close(texto);
end;
procedure baja();
var arch:archivo; e:empleado; eliminar:integer; ult:empleado;
begin
	write('Ingrese el codigo de empleado a eliminar: ');
	readln(eliminar);
	abrir_archivo(arch);
	seek(arch, filesize(arch)-1);
	read(arch, ult);//guardo el último dato
	seek(arch, 0);//me pongo en el primero
	if (not(eof(arch))) then
		read(arch,e);//leo el 1ro
	while (not(eof(arch)) and (e.num<>eliminar)) do
		read(arch,e);//avanzo
	if (e.num=eliminar) then
	begin
		seek(arch, filepos(arch)-1);//escribo el último en el que quiero borrar
		write(arch, ult);
		seek(arch, filesize(arch)-1);//me paro en el último, trunco
		truncate(arch);
	end
	else writeln('El empelado no existe');
	close(arch);
end;

procedure menu();
var opcion:char;
begin
	writeln(' ___________________________________________________________________________________');
	writeln('|          Welcome al menu de cositas de empleado                                   |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Crear un archivo                                                                |');
	writeln('|b) Buscar empleados por nombre                                                     |');
	writeln('|c) Listar empleados                                                                |');
	writeln('|d) Listar empleados mayores a 60 anios                                             |');
	writeln('|e) Agregar empleados al final del archivo                                          |');
	writeln('|f) Dar de baja un empleado                                                         |');
	writeln('|g) Modificar edad a empleados                                                      |');
	writeln('|h) Exportar archivo a un txt                                                       |');
	writeln('|i) Exportar empleados con dni 00 a un txt                                          |');
	writeln('|j) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
	writeln();
	readln(opcion);
	if (opcion<>'j') then 
	begin
		case opcion of
			'a': crear_archivo();
			'b': buscar_empleado();
			'c': listar_empleados();
			'd': mayores();
			'e': agregar();
			'f': baja();
			'g': modificar_edad();
			'h': exportar_todo();
			'i': exportar_00();
		else begin
			writeln('Por favor, ingrese una opcion valida');
			menu;
		end;
		end;
		menu;
	end;
end;

begin
	menu();
end.
