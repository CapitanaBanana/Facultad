program Ej_7;
type 
    cadena=string[20];
    ave=record
    cod:integer;
    nombre:cadena;
    end;
    archivo=file of ave;

procedure crearArchivo();
procedure leerAve(var a:ave);
begin
    write('codigo: ');
    readln(a.cod);
    if (a.cod<>-1) then
    begin
        write('nombre: ');
        readln(a.nombre);
    end;
end;
var arch:archivo; a:ave;
begin
    assign(arch,'aves');
    rewrite(arch);
    leerAve(a);
    while(a.cod<>-1) do
    begin
        write(arch,a);
        leerAve(a);
    end;
    close(arch);
end;
procedure abrirArchivo(var a:archivo);
begin
    assign(a, 'aves');
    reset(a);
end;
procedure realizarBajas();
var arch:archivo;a:ave; cod:integer; seguir:boolean;
begin
    abrirArchivo(arch);
    write('ave a borrar: ');
    readln(cod);
    while (cod<>-1) do
    begin
        seguir:=true;
        seek(arch,0);
        while (not(eof(arch)) and seguir) do
        begin
            read(arch, a);
            if (a.cod=cod) then
            begin
                seguir:=false;
                a.cod:= a.cod-(2*a.cod);
                seek(arch, filepos(arch)-1);
                write(arch, a);
            end;
        end;
        if (seguir) then
            writeln('El codigo ingresada no esta en el archivo');
        write('ave a borrar: ');
        readln(cod);
    end;
    close(arch);
end;
procedure listarDatos();
var arch:archivo; a:ave;
begin
    abrirArchivo(arch);
    while (not(eof(arch))) do
    begin
        read(arch, a);
        writeln(a.cod, ' | ', a.nombre);
    end;
    close(arch);
end;
procedure compactar();
var arch:archivo; a:ave; ult:ave; pos:integer;
begin
    abrirArchivo(arch);
    while (not(eof(arch))) do
    begin
        read(arch,a);
        if (a.cod<0) then
        begin
            if (filepos(arch)-1<>filesize(arch)) then
            begin
                pos:= filepos(arch)-1; //me guardo pos en la que estoy
                seek(arch, filesize(arch)-1);//voy al ultimo
                read(arch, ult);//leo ultima ave 
                seek(arch, pos);//voy a la pos en la que estaba
                write(arch, ult);//escribo el que estaba último
            end;
            seek(arch, filesize(arch)-1);
            truncate(arch);//trunco últiam pos
            seek(arch,pos-1);//vuelvo uno para atras por si puse un numero negativo
        end;
    end;
    close(arch);
end;
begin 
    crearArchivo();
    listarDatos();
    realizarBajas();
    compactar();
    listarDatos();
end.