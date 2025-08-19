program Ej_8;
type
    cadena=string[20];
    distro=record
    nombre:cadena;
    cant:integer;
    end;

    archivo= file of distro;

procedure abrirArchivo(var arch:archivo);
begin
    assign(arch, 'archivo');
    reset(arch);
end;

procedure crear_archivo();
procedure leerdistro(var d:distro);
begin
    write('Cantidad: ');
    readln(d.cant);
    if (d.cant<>-1) then
    begin
        writeln('Nombre: ');
        readln(d.nombre);
    end;
end;
var arch:archivo; d:distro;
begin
    assign(arch, 'archivo');
    rewrite(arch);
    d.cant:=0;
    write(arch,d);
    leerdistro(d);
    while (d.cant<>-1) do
    begin
        write(arch, d);
        leerdistro(d);
    end;
    close(arch);
end;
function existe(nom:cadena):boolean;
var arch:archivo; d:distro; encontrado:boolean;
begin
    encontrado:=false;
    abrirArchivo(arch);
    while((not(eof(arch))) and (not encontrado))do
    begin
        read(arch, d);
        if (d.nombre=nom) then begin
            encontrado:=true;
        end;
    end;
    close(arch);
    existe:=encontrado;
end;

procedure listar();
var arch:archivo; d:distro;
begin
    abrirArchivo(arch);
    while(not(eof(arch))) do
    begin
        read(arch, d);
        writeln(d.nombre,' | ', d.cant);
    end;
    close(arch);
end;
procedure alta();
var arch:archivo; d:distro; aux: distro;
begin
    write('ingrese nombre de distribucion: ');
    readln(d.nombre);
    write('cantidad de desarrolladores: ');
    readln(d.cant);
    if (not(existe(d.nombre))) then
    begin
        abrirArchivo(arch);
        read(arch, aux);//leo lo que hay en el 0
        if (aux.cant<>0) then
        begin
            seek(arch, -aux.cant);//voy a pos que apunta el puntero
            read(arch, aux);//leo lo que hay y lo dejo en aux
            seek(arch, filepos(arch)-1);//pongo ahí el dato nuevo
            write(arch, d);
            seek(arch, 0);
            write(arch, aux);
        end
        else
        begin
            seek(arch, filesize(arch));
            write(arch, d);
        end;
        close(arch)
    end
    else
        writeln('No se agrego la distribucion porque ya esta en el archivo');
end;
procedure baja();
var arch:archivo; d:distro; aux:distro; nombre:cadena; 
begin
    write('ingrese nombre de distribucion: ');
    readln(nombre);
    if (existe(nombre)) then
    begin
        abrirArchivo(arch);
        read(arch, aux);
        while (aux.nombre<>nombre) do
            read(arch, aux);
        aux.cant:=filepos(arch)-1;
        aux.cant:= aux.cant-(2*aux.cant);//me guardo la pos del que quiero borrar
        seek(arch, 0);
        read(arch,d);//en d tengo cabecera
        seek(arch, -aux.cant);//voy a pos que quiero borrar
        write(arch, d);//le pongo lo que había en cabecera
        seek(arch, 0);
        write(Arch,aux);//pongo borrado en cabecera
    end
    else
        writeln('No se encuentra dicha distribucion');
    close(arch);
end;

procedure menu();
var opcion:char; nombre:cadena;
begin
    writeln(' ___________________________________________________________________________________');
	writeln('|          Welcome al menu de cositas de empleado                                   |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Crear un archivo                                                                |');
	writeln('|b) Buscar distribucion por nombre                                                  |');
	writeln('|c) Listar dist                                                                     |');
	writeln('|d) Agregar distribuciones                                                          |');
	writeln('|e) Dar de baja una distribucion                                                    |');
	writeln('|f) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
	writeln();
	readln(opcion);
	if (opcion<>'f') then 
	begin
		case opcion of
			'a': crear_archivo();
			'b': 
                begin
                    writeln('Ingrese la distribucion que desea buscar');
                    readln(nombre);
                    if (existe(nombre)) then
                        writeln('La distribucion ingresada esta en el archivo')
                    else
                        writeln('no esta.');
                end;
			'c': listar();
			'd': alta();
			'e': baja();
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