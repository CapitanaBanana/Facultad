{ Definir un programa que genere un archivo con registros de longitud fija conteniendo
información de asistentes a un congreso a partir de la información obtenida por
teclado. Se deberá almacenar la siguiente información: nro de asistente, apellido y
nombre, email, teléfono y D.N.I. Implementar un procedimiento que, a partir del
archivo de datos generado, elimine de forma lógica todos los asistentes con nro de
asistente inferior a 1000.
}
program ej_2;
type 
    cadena=string[20];

    asistente=record
    nro:integer;
    ape:cadena;
    end;
    archivo=file of asistente;

procedure crear_archivo();
procedure leerAsistente(var a:asistente);
begin
    write('Numero: ');
    readln(a.nro);
    if (a.nro<>0) then
    begin
        write('Apellido: ');
        readln(a.ape);
    end;
end;
var arch:archivo; a:asistente;
begin
    assign(arch, 'asistentes');
    rewrite(arch);
    leerAsistente(a);
    while(a.nro<>0) do
    begin
        write(arch, a);
        leerAsistente(a);
    end;
    close(arch);
end;
procedure eliminar();
var arch:archivo; a:asistente;
begin
    assign(arch, 'asistentes');
    reset(arch);
    while (not(eof(arch))) do
    begin
        read(arch,a);
        if (a.nro<=1000) then
        begin
            a.ape:= concat('@', a.ape);
            seek(arch, filepos(arch)-1);
            write(arch, a);
        end;
    end;
    close(arch);
end;
procedure imprimirArchivo();
var arch:archivo; a:asistente;
begin
    assign(arch, 'asistentes');
    reset(arch);
    while (not(eof(arch))) do
    begin
        read(arch,a);
        writeln('apellido: ', a.ape, ', numero: ',a.nro)
    end;
    close(arch);
end;

begin
    crear_archivo();
    imprimirArchivo();
    eliminar();
    writeln();
    imprimirArchivo();
end.
