//no anda del todo bien, chat gpt me dio 12 errores(literalmente) si sigo intentando arreglarlo voy a llorar así que así queda :)
program Ej_4;
type
    cadena= String[45];
    flor = record
    nom: cadena;
    cod: integer;
    end;
    archFlores = file of flor;

procedure crearArchivo();
procedure leerFlor(var f:flor);
begin
    write('Codigo de flor: ');
    readln(f.cod);
    if(f.cod<>-1) then 
    begin
        write('nombre: ');
        readln(f.nom);
    end;
end;
var arch:archFlores; f:flor;
begin
    assign(arch,'flores');
    rewrite(arch);
    f.cod:=0;
    f.nom:='null';
    write(arch,f);
    leerFlor(f);
    while(f.cod<>-1) do
    begin
        write(arch, f);
        leerFlor(f);
    end;
    close(arch);
end;
procedure abrirArchivo(var arch:archFlores);
begin
    assign(arch, 'flores');
    reset(arch);
end;
procedure agregarFlor (var arch: archFlores; nombre: string; codigo:integer);
var nueva:flor;aux:flor;aux2:flor;
begin
    nueva.nom:=nombre;
    nueva.cod:=codigo;
    read(arch,aux);//leo el dato de la cabecera
    writeln('aux.cod es', aux.cod);
    if (aux.cod<>0) then
    begin
        seek(arch, -(aux.cod));//voy a la pos donde apunta la cabecera
        read(arch, aux2);//me guardo en un auxiliar lo que habia ahi
        seek(arch, 0);
        write(arch,aux2);//escribo en cabecera lo que habia donde escribi
        seek(arch, -aux.cod);//vuelvo a pos que tenia cabecera
        write(arch, nueva);//pongo el nuevo dato 
    end
    else 
    begin
        seek(arch, filesize(arch)-1);
        write(arch,nueva);
    end;
    close(arch);
end;
procedure eliminarFlor();
var arch:archFlores; f:flor;c:integer; seguir:boolean; lista:flor;
begin
    seguir:=true;
    abrirArchivo(arch);
    write('Ingrese codigo de flor a eliminar: ');
    readln(c);
    if (not(eof(arch))) then
        read(arch,f);
    while ((not(eof(arch))) and (seguir)) do
    begin
        if (f.cod<>c) then
            read(arch,f)
        else
        begin
            f.cod:= -(filepos(arch)-1);
            seguir:=false;
            seek (arch, 0);
            read(arch, lista);//leo cabecera
            seek(arch, -f.cod);//voy a la pos que quiero borrar
            write(arch, lista);//pongo lo que habia en cabecera ahi
            seek(arch, 0);
            write(arch,f);//pongo el dato nuevo en la cabecera
            writeln('Flor eliminada!');
        end;
    end;
    if (seguir) then
            writeln('La flor ingresada no existe');
    close(arch);
end;
procedure exportar();
var arch:archFlores; f:flor; texto:text;
begin
    abrirArchivo(arch);
    writeln('queonda');
    assign(texto, 'flores.txt');
    rewrite(texto);
    while (not(eof(arch))) do
    begin
        read(arch, f);
     if (f.cod > 0) then
            writeln(texto, f.cod, ' | ', f.nom);
    end;
    close(arch);
    close(texto);
end;

var arch:archFlores; nom:cadena;cod:integer;
begin 
    crearArchivo();
    
    write('Codigo de flor: ');
    readln(cod);
    write('nombre: ');
    readln(nom);

    abrirArchivo(arch);
    agregarFlor(arch,nom,cod);
    eliminarFlor();
    exportar();
end.