program Ej_13;
const fin=9999;
type 
    cadena=string[20];
    log=record
        nro:integer;
        nom_usuario:cadena;
        ape:cadena;
        cant:integer;
    end;
    mensaje=record
        nro:integer;
        destino:integer;
        cuerpo:cadena;
    end;
    logs= file of log;
    dia= file of mensaje;

procedure importarMaestro();
var texto:text; m:logs; l:log;
begin
    assign(texto, 'logs.txt');
    assign(m, 'logs');
    reset(texto);
    rewrite(m);
    while(not(eof(texto))) do
    begin
        with l do
        begin
            readln(texto, nro,nom_usuario);
            readln(texto, cant, ape);
        end;
        write(m, l);
    end;
    close(m);
end;
procedure importarDetalle();
var texto:text; d:dia; m:mensaje;
begin
    assign(texto, 'mensajes.txt');
    assign(d, 'mensajes');
    reset(texto);
    rewrite(d);
    while(not(eof(texto))) do
    begin
        with m do
        begin
            readln(texto, nro,destino, cuerpo);
        end;
        write(d, m);
    end;
    close(d);
end;
procedure abrirArchivos(var m:dia; var l:logs; var texto:text);
begin
    assign(m, 'mensajes');
    assign(l, 'logs');
    assign(texto, 'Resumen.txt');
    reset(m);
    reset(l);
    rewrite(texto);
end;
procedure leer(var arch:dia; var m:mensaje);
begin
    if (not(eof(arch))) then
        read(arch,m)
    else 
        m.nro:=fin;
end;
procedure exportarMaestro();
var texto:text;l:log; ma:logs;
begin
    assign(texto,'logsUpdate.txt');
    rewrite(texto);
    writeln('hola');
    assign(ma, 'logs');
    reset(ma);
    while (not(eof(ma))) do
    begin
        read(ma,l);
        with l do
        begin
            writeln(texto, nro, nom_usuario);
            writeln(texto, cant,ape);
        end;
    end;
    close(ma);
    close(texto);
end;
var d:dia;ma:logs;texto:text; ant:dia; l:log;m:mensaje;suma:integer;
begin 
    importarMaestro();
    importarDetalle();
    abrirArchivos(d,ma,texto);
    leer(d,m);
    while (m.nro<> fin) do
    begin
        suma:=0;
        read(ma,l);
        while (m.nro<>l.nro) do
            read(ma,l);
        while(m.nro=l.nro) do 
        begin
            l.cant:= l.cant+1;
            suma:=suma+1;
            leer(d,m);
        end;
        seek(ma, filepos(ma)-1);
        write(ma, l);
        writeln(texto, l.nro,' envio ', suma,' mensajes.');
    end;
    
    close(ma);
    close(d);
    close(texto);
    exportarMaestro();
end.