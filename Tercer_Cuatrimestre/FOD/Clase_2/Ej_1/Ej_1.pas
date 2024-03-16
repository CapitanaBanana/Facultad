program Ej_1;
type 
    cadena= string[20];
    empleado= record
        cod:integer;
        monto:real;
        nom:cadena;
    end;
    archivo= file of empleado;
procedure cargarArchivo();
procedure cargarRegistro(var e:empleado);
begin
    write('Ingrese el codigo del empleado a agregar: ');
    readln(e.cod);
    if (e.cod<> 0)then
    begin
        write('Nombre: ');
        readln(e.nom);
        write('Monto: ');
        readln(e.monto);
        writeln('____________________________________________');
    end;
end;
var nom:cadena; arch:archivo; e:empleado;
begin
    writeln('Ingrese el nombre del archivo a crear');
    readln(nom);
    assign(arch, nom);
    rewrite(arch);
    cargarRegistro(e);
    while (e.cod<>0) do
    begin
        write(arch, e);
        cargarRegistro(e);
    end;
    close(arch);
end;
procedure compactar();
const fin=0;
var arch:archivo; texto:text; e:empleado; total:real; actual:integer; i:integer;
procedure abrirArchivo(var arch:archivo; var texto:text);
var nom:cadena;
begin
    writeln('Ingrese el nombre del archivo que desea abrir: ');
    readln(nom);
    assign(arch,nom);
    reset(arch);

    assign(texto,'empleados.txt');
    rewrite(texto);
end;
procedure leer(var arch:archivo; var dato:empleado);
begin
    if (not (eof(arch))) then 
        read(arch, e)
    else
        e.cod:=fin;
end;
begin
    abrirArchivo(arch, texto);
    leer(arch,e);
    while(e.cod<> fin) do
    begin
        writeln('Empleado numero: ', e.cod);
        actual:=e.cod;
        total:=0;
        i:=1;
        writeln(texto, 'codigo: ', e.cod,' nombre: ', e.nom);
        while (e.cod= actual) do
        begin
            writeln('monto ', i,': ', e.monto:0:2);
            total:= total+ e.monto;
            leer(arch,e);
            i:= i+1;
        end;
        writeln('Total: ', total:0:2);
        writeln(texto,' total: ', total:0:2);
    end;
    close(arch);
    close(texto);
end;
var opcion:char;
begin 
    writeln('escriba a para crear un archivo de empleados y b para compactar un archivo existente: ');
    readln(opcion);
    if (opcion='a') then
        cargarArchivo()
    else if(opcion='b') then
        compactar();
end.