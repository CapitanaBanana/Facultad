{Realizar un programa para una tienda de celulares, que presente un menú con
opciones para:
a. Crear un archivo de registros no ordenados de celulares y cargarlo con datos
ingresados desde un archivo de texto denominado “celulares.txt”. Los registros
correspondientes a los celulares, deben contener: código de celular, el nombre,
descripción, marca, precio, stock mínimo y el stock disponible.
b. Listar en pantalla los datos de aquellos celulares que tengan un stock menor al
stock mínimo.
c. Listar en pantalla los celulares del archivo cuya descripción contenga una
cadena de caracteres proporcionada por el usuario.
d. Exportar el archivo creado en el inciso a) a un archivo de texto denominado
“celulares.txt” con todos los celulares del mismo. El archivo de texto generado
podría ser utilizado en un futuro como archivo de carga (ver inciso a), por lo que
debería respetar el formato dado para este tipo de archivos en la NOTA 2.}
program ejercicio_5;
type
    cadena=string[20];
    celular= record
        cod:integer;
        nom: cadena;
        des: cadena;
        marca: cadena;
        precio: real;
        min_stock: integer;
        stock:integer;
    end;
    archivo= file of celular;

procedure crear_archivo();
var arch:archivo; texto:text; nom:cadena;c:celular;
begin
    writeln('Nombre del archivo a crear');
    readln(nom);
    assign(texto,'celulares.txt');
    assign(arch, nom);
    reset(texto);
    rewrite(arch);
    while (not eof(texto)) do
    begin
        with c do 
        begin
            readln(texto, cod, precio, marca);
            readln(texto, stock, min_stock, des);
            readln(texto, nom);
        end;
        write(arch, c);
    end;
    close(arch);
    close(texto);
end;
procedure abrir_archivo(var arch:archivo);
var nom:cadena;
begin
    writeln();
    writeln('Escriba el nombre del binario a abrir: ');
    readln(nom);
    assign(arch, nom);
    reset(arch);
end;
procedure listar_celulares();
var arch:archivo; c:celular;
begin
    abrir_archivo(arch);
    while (not eof(arch)) do
    begin
        read(arch, c);
        writeln('| cod: ', c.cod,' | precio: ', c.precio:0:2,' | marca: ', c.marca,' |');
        writeln('| disponibles: ', c.stock, ' | stock minimo: ', c.min_stock, ' | descripcion: ', c.des,' |');
        writeln('| nombre: ', c.nom, ' |');
        writeln();
        end;
    writeln();
    close(arch);
end;
procedure listar_poco_stock();
var arch:archivo; c:celular;
begin
    abrir_archivo(arch);
    while (not eof(arch)) do
    begin
        read(arch, c);
        if (c.stock <= c.min_stock) then
        begin
            writeln('| disponibles: ', c.stock, ' | stock minimo: ', c.min_stock,' |');
            writeln('| nombre: ', c.nom, ' |');
            writeln();
        end;
        end;
    writeln();
    close(arch);
end;

procedure listar_descripciones();
var arch:archivo; c:celular;
begin
    abrir_archivo(arch);
    while (not eof(arch)) do
    begin
        read(arch, c);
        if (c.des<>'') then
        begin
            writeln('| cod: ', c.cod,' | precio: ', c.precio:0:2,' | marca: ', c.marca,' |');
            writeln( ' | descripcion: ', c.des,' |');
            writeln('| nombre: ', c.nom, ' |');
            writeln();
        end;
        end;
    writeln();
    close(arch);
end;

procedure exportar_txt();
var arch:archivo; texto:text;c:celular;
begin
    abrir_archivo(arch);
    assign(texto, 'celulares.txt');
    rewrite(texto);
    while (not eof(arch))do 
    begin
        read(arch, c);
        with c do
        begin
            writeln(texto, cod,' ', precio:0:2,' ', marca);
            writeln(texto, stock,' ', min_stock,' ', des);
            writeln(texto, nom);
        end;
    end;
    close(arch);
    close(texto);
end;

procedure agregar();
procedure leer_celular(var c:celular);
begin
    write('Codigo del telefono: ');
    readln(c.cod);
    if (c.cod<> 0) then
    begin
        write('Nombre: ');
        readln(c.nom);
        write('Descripcion: ');
        readln(c.des);
        write('Marca: ');
        readln(c.marca);
        write('Precio: ');
        readln(c.precio);
        write('Stock minimo: ');
        readln(c.min_stock);
        write('Stock actual: ');
        readln(c.stock);
    end;
end;
var arch: archivo; c:celular;
begin
    abrir_archivo(arch);
    leer_celular(c);
    while (c.cod<>0) do
    begin
        seek(arch, filesize(arch));
        write(arch, c);
        leer_celular(c);
    end;
    close(arch);
end;

procedure modificar_stock();
var arch:archivo;nom: cadena; encontrado: boolean; c:celular;
begin
    encontrado:=false;
    abrir_archivo(arch);
    writeln('Ingrese el nombre del celular cuyo stock quiere editar: ');
    readln(nom);
    while (not eof(arch) and not(encontrado)) do
    begin
        read(arch,c);
        if (c.nom=nom) then
        begin
            writeln('Stock anterior: ', c.stock);
            writeln('Ingrese nuevo stock: ');
            read(c.stock);
            seek(arch, filepos(arch)-1);
            write(arch, c);
            encontrado:=true;
        end;
    end;
    if (encontrado=false) then
        writeln('El telefono ingresado no se encuentra en el archivo.');
    close(arch);
end;

procedure exportar_stock_0();
var arch:archivo; c:celular; texto:text;
begin
    abrir_archivo(arch);
    assign(texto, 'SinStock.txt');
    rewrite(texto);
    while(not eof(arch)) do
    begin
        read(arch, c);
        if (c.stock=0) then
        begin
            with c do
            begin
                writeln(texto, cod,' ', precio:0:2,' ', marca);
                writeln(texto, stock,' ', min_stock,' ', des);
                writeln(texto, nom);
            end;
        end;
    end;
    close(arch);
    close(texto);
end;

procedure menu();
var opcion:char;
begin
	writeln(' ___________________________________________________________________________________');
	writeln('|          Welcome al menu de cositas de celulares                                  |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Crear un archivo binario de celulares                                           |');
	writeln('|b) Listar celulares con poco stock                                                 |');
	writeln('|c) Listar celulares con descripcion                                                |');
	writeln('|d) Exportar a txt                                                                  |');
	writeln('|e) Agregar celulares al final del archivo                                          |');
	writeln('|f) Modificar stock a un celular                                                    |');
	writeln('|g) Exportar a txt celulares sin stock                                              |');
    writeln('|h) Listar todo                                                                     |');
	writeln('|i) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
	writeln();
	readln(opcion);
	if (opcion<>'i') then 
	begin
		case opcion of
			'a': crear_archivo();
			'b': listar_poco_stock();
			'c': listar_descripciones();
			'd': exportar_txt();
			'e': agregar();
			'f': modificar_stock();
			'g': exportar_stock_0();
            'h': listar_celulares();
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
