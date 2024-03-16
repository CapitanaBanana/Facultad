program Ej_3;
const fin=9999;
type
    cadena=string[20];
    novela=record
    cod:integer;
    //gen:cadena;
    nom:cadena;
    //duracion:integer;
    //direc:cadena;
    //precio:integer;
    end;
    archivo= file of novela;

procedure crear_Archivo();
procedure leerNovela(var n:novela);
begin
    write('Codigo: ');
    readln(n.cod);
    if (n.cod<>-1) then
    begin
        write('Nombre: ');
        readln(n.nom);
    end;
end;
var arch:archivo; n:novela;nom:cadena;
begin
    write('Ingrese el nombre del archivo a crear: ');
    readln(nom);
    assign(arch, nom);
    rewrite(arch);
    n.cod:=0;
    write(arch, n);
    leerNovela(n);
    while(n.cod<>-1) do
    begin
        write(arch,n);
        leerNovela(n);
    end;
    close(arch);
end;
procedure abrirArchivo(var arch:archivo);
var nom:cadena;
begin
    write('Ingrese el nombre del archivo a abrir: ');
    readln(nom);
    assign(arch, nom);
    reset(arch);
end;
procedure leer(var arch:archivo; var n:novela);
begin
    if (not(eof(arch))) then
        read(arch, n)
    else
        n.cod:=fin;
end;
procedure eliminar_novela();
var arch:archivo; n:novela;elim:integer; seguir:boolean;lista:novela;
begin 
    abrirArchivo(arch);
    seguir:=true;
    write('Ingrese el codigo de la novela a eliminar: ');
    readln(elim);
    leer(arch, n);
    while ((n.cod <>fin) and(seguir)) do
    begin
        while ((n.cod<>elim) and (n.cod <>fin)) do
            leer(arch,n);
        if (n.cod=elim) then
        begin
            seguir:=false;
            n.cod:= -(filepos(arch)-1);
        end;
    end;
    if (seguir=false) then
    begin
        seek(arch, 0);
        read(arch,lista);//leo cabecera
        seek(arch,-(n.cod)); //me posiciono en el que quiero borrar
        write(arch, lista);// escribo lo que esta en cabecera
        seek(arch, 0);
        write(arch, n);
    end
    else writeln('La novela ingresada no existe!');
    close(arch);
end;
procedure agregar_novela();
var arch:archivo; n:novela; lista:novela; aux:novela;
begin
    abrirArchivo(arch);
    write('Ingrese el codigo de la novela a a agregar: ');
    readln(n.cod);
    write('titulo: ');
    readln(n.nom);
    seek(arch, 0);
    read(arch, lista);
    if (lista.cod=0) then// si no tengo nada borrado
    begin
        seek(arch, filesize(arch)-1);//pongo en el final
        write(arch, n);
    end
    else
    begin
        seek(arch, -lista.cod);//sino me posiciono en el ultimo borrado
        read(arch, aux);//agarro el dato que tiene (el antrior borrado)
        seek(arch, 0);
        write(arch, aux);//pongo el siguiente borrado en la cabecera
        seek(arch, -(lista.cod));//voy a la pos del ultimo borrado
        write(arch, n);//escribo ahi el nuevo dato
    end;
    close(arch);
end;
procedure listar_novelas();
var arch:archivo; n:novela;texto:text;
begin
    abrirArchivo(arch);
    assign(texto, 'novelas.txt');
    rewrite(texto);
    while (not(eof(arch))) do
    begin
        read(arch, n);
        writeln(texto, n.cod, ' | ', n.nom);
    end;
    close(arch);
    close(texto);
end;
procedure modificar_novela();
var arch:archivo; n:novela;cod:integer; seguir:boolean;
begin
    abrirArchivo(arch);
    seguir:=true;
    write('Ingrese el codigo de la novela a editar: ');
    readln(cod);
    leer(arch, n);
    while ((n.cod <>fin) and(seguir)) do
    begin
        while ((n.cod<>cod) and (n.cod <>fin)) do
            leer(arch,n);
        if (n.cod=cod) then
            seguir:=false;
    end;
    if (seguir=false) then
    begin
        write('Ingrese un nuevo nombre para la novela: ');
        readln(n.nom);
        seek(arch, filepos(arch)-1);
        write(arch,n);
    end
    else 
        writeln('La novela ingresada no existe!');
    close(arch);
end;
procedure menu();
var opcion:char;
begin
	writeln(' ___________________________________________________________________________________');
	writeln('|          Welcome al menu de novelas                                               |');
	writeln('|Ingrese un caracter de los listados a continuacion para continuar:                 |');
	writeln('|a) Crear un archivo                                                                |');
	writeln('|b) Agregar una novela                                                              |');
	writeln('|c) Eliminar                                                                        |');
	writeln('|d) Modificar                                                                       |');
	writeln('|e) Listar                                                                          |');
	writeln('|f) cerrar                                                                          |');
	writeln('|___________________________________________________________________________________|');
	writeln();
	readln(opcion);
	if (opcion<>'f') then 
	begin
		case opcion of
			'a': crear_archivo();
			'b': agregar_novela();
			'c': eliminar_novela();
			'd': modificar_novela();
			'e': listar_novelas();
		else begin
			writeln('Por favor, ingrese una opcion valida');
			menu;
		end;
		end;
		menu;
	end;
end;

var arch:archivo;
begin
    menu();

end.