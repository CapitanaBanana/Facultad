{Realizar un programa que permita:
a. Crear un archivo binario a partir de la información almacenada en un archivo de texto.
El nombre del archivo de texto es: “novelas.txt”
b. Abrir el archivo binario y permitir la actualización del mismo. Se debe poder agregar
una novela y modificar una existente. Las búsquedas se realizan por código de novela.   
}
program ejercicio_7;
type 
    cadena= string[20];
    novela= record
        nombre: cadena;
        cod:integer;
        genero: cadena;
        precio: real;
    end;
    archivo= file of novela;

procedure crear_archivo();
var nom:cadena; texto:text;arch: archivo;n:novela;
begin
    writeln('Ingrese el nombre que le quiere dar al archivo: ');
    readln(nom);
    assign(arch,nom);
    assign(texto, 'novelas.txt');
    rewrite(arch);
    reset(texto);
    while (not eof(texto)) do
    begin
        with n do
        begin
            readln(texto, n.cod, n.precio, n.genero);
            readln(texto, n.nombre);
        end;
        write(arch, n);
    end;
    close(texto);
    close(arch);
end;
procedure abrir_archivo(var arch:archivo);
var nom: cadena;
begin
    writeln();
    writeln('ingrese el nombre del archivo a abrir: ');
    readln(nom);
    assign(arch, nom);
    reset(arch);
end;

procedure exportar();
var arch:archivo;texto:text;n:novela;
begin
    abrir_archivo(arch);
    assign(texto, 'novelas.txt');
    rewrite(texto);
    while (not eof(arch)) do
    begin
        read(arch, n);
        with n do
        begin
            writeln(texto, n.cod,' ', n.precio:0:2,' ', n.genero);
            writeln(texto, n.nombre);
        end;
    end;
    close(arch);
    close(texto);
end;

procedure leer_novela(var n:novela);
begin
    write('Ingrese codigo de la novela: ');
    readln(n.cod);
    if (n.cod<>0) then
    begin
        write('nombre: ');
        readln(n.nombre);
        write('genero: ');
        readln(n.genero);
        write('precio: ');
        readln(n.precio);
    end;
end;

procedure actualizar();
procedure agregar();
var arch:archivo; n:novela;
begin
    abrir_archivo(arch);
    leer_novela(n);
    while (n.cod<>0) do
    begin
        seek(arch, filesize(arch));
        write(arch, n);
        leer_novela(n);
    end;
    close(arch);
end;
procedure editar();
function buscar_pos(var arch:archivo):integer;
var encontrado: boolean; n:novela; cod:integer;
begin
    write('Ingrese el codigo de la novela a editar: ');
    readln(cod);
    encontrado:=false;
    while (not eof(arch) and (not encontrado)) do
    begin
        read (arch, n);
        if (n.cod=cod)
            then begin
                encontrado:= true;
                buscar_pos:= filepos(arch)-1;
            end;
    end;
    if (encontrado= false) then
        buscar_pos:= -1;
end; 
procedure nombre();
var pos: integer;n: novela;arch:archivo;
begin
    abrir_archivo(arch);
    pos:= buscar_pos(arch);
    if (pos<>-1) then begin
        seek(arch, pos);
        read(arch, n);
        writeln('El nombre actual es: ', n.nombre);
        write('Ingrese un nuevo nombre para la novela: ');
        readln(n.nombre);
        seek(arch, filepos(arch)-1);
        write(arch, n);
    end
    else
        writeln('La novela con el codigo ingresado no esta en el archivo.');
    close(arch);
end;

procedure genero();
var pos: integer;n: novela;arch:archivo;
begin
    abrir_archivo(arch);
    pos:= buscar_pos(arch);
    if (pos<>-1) then begin
        seek(arch, pos);
        read(arch, n);
        writeln('El genero actual es: ', n.genero);
        write('Ingrese un nuevo genero para la novela: ');
        readln(n.genero);
        seek(arch, filepos(arch)-1);
        write(arch, n);
    end
    else
        writeln('La novela con el codigo ingresado no esta en el archivo.');
    close(arch);
end;

procedure precio();
var pos: integer;n: novela; arch:archivo;
begin
    abrir_archivo(arch);
    pos:= buscar_pos(arch);
    if (pos<>-1) then begin
        seek(arch, pos);
        read(arch, n);
        writeln('El precio actual es: ', n.precio:0:2);
        write('Ingrese un nuevo precio para la novela: ');
        readln(n.precio);
        seek(arch, filepos(arch)-1);
        write(arch, n);
    end
    else
        writeln('La novela con el codigo ingresado no esta en el archivo.');
    close(arch);
end;

procedure codigo();
var pos: integer;n: novela;arch:archivo;
begin
    abrir_archivo(arch);
    pos:= buscar_pos(arch);
    if (pos<>-1) then begin
        seek(arch, pos);
        read(arch, n);
        writeln('El codigo actual es: ', n.cod);
        writeln('Ingrese un nuevo codigo para la novela: ');
        readln(n.cod);
        seek(arch, filepos(arch)-1);
        write(arch, n);
    end
    else
        writeln('La novela con el codigo ingresado no esta en el archivo.');
    close(arch);
end;
var opcion:char;
begin
    writeln(' ___________________________________________________________________________________');
	writeln('|                                edicion de novela                                  |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Editar nombre                                                                   |');
	writeln('|b) Editar genero                                                                   |');
    writeln('|c) Editar precio                                                                   |');
    writeln('|d) Editar codigo                                                                   |');
	writeln('|e) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
    writeln();
	readln(opcion);
    if (opcion<> 'e') then 
    begin
        case opcion of
            'a': nombre();
            'b': genero();
            'c': precio();
            'd': codigo();
            else begin
			    writeln('Por favor, ingrese una opcion valida');
			    editar();
		    end;
        editar();
    end;
end;
end;
var opcion:char;
begin
    writeln(' ___________________________________________________________________________________');
	writeln('|                                edicion del archivo                                |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Agregar novela                                                                  |');
	writeln('|b) Editar novela existente                                                         |');
	writeln('|c) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
	writeln();
	readln(opcion);
    if (opcion<> 'c') then 
    begin
        case opcion of
            'a': agregar();
            'b': editar();
            else begin
			writeln('Por favor, ingrese una opcion valida');
			actualizar();
		end;
    end;
    actualizar();
    end;
end;

procedure menu();
var opcion:char;
begin
    writeln(' ___________________________________________________________________________________');
	writeln('|                   Welcome al menu de cositas de novelas                           |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Crear un archivo binario de novelas                                             |');
	writeln('|b) Actualizar archivo                                                              |');
    writeln('|c) Exportar a txt                                                                  |');
	writeln('|d) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
	writeln();
	readln(opcion);
    if (opcion<> 'd') then 
    begin
        case opcion of
            'a': crear_archivo();
            'b': actualizar();
            'c': exportar();
            else begin
			    writeln('Por favor, ingrese una opcion valida');
			    menu();
		    end;
    end;
    menu();
end;
END;
begin
    menu();
end.
