program Ej_7;
const fin=9999; 
type 
    cadena= string[20];
    producto= record
        cod:integer;
        nom:cadena;
        precio:real;
        actual:integer;
        minimo:integer;
    end;
    ventas=record
        cod:integer;
        cant:integer;
    end;

    arch_detalle= file of ventas;
    arch_maestro=file of producto;
procedure importarMaestro();
var texto:text;p:producto;maestro:arch_maestro;
begin
    assign(texto, 'maestro.txt');
    reset(texto);
    assign(maestro, 'maestro');
    rewrite(maestro);
    while (not(eof(texto)))do
    begin
        with p do
        begin
            readln(texto, cod, precio);
            readln(texto, actual, minimo, nom);
        end;
        write(maestro, p);
    end;
    close(maestro);
    close(texto);
end;

procedure cargarDetalle();
var v:ventas;detalle:arch_detalle;
begin
    assign(detalle, 'detalle');
    rewrite(detalle);
    write('codigo: ');
    readln(v.cod);
    while (v.cod<>0) do
    begin
        write('ventas: ');
        readln(v.cant);
        write(detalle, v);
        write('codigo: ');
        readln(v.cod);
    end;
    close(detalle);
end;

procedure abrirArchivos(var detalle:arch_detalle;var maestro:arch_maestro);
begin
    assign(detalle, 'detalle');
    assign(maestro, 'maestro');
    reset(maestro);
    reset(detalle);
end;
procedure leer(var det:arch_detalle;var v:ventas);
begin
    if (not(eof(det))) then
        read(det,v)
    else
        v.cod:=fin;
end;
procedure listar(var maestro:arch_maestro);
var texto:text;p:producto;
begin
    assign(texto, 'stock_minimo.txt');
    rewrite(texto);
    assign(maestro, 'maestro');
    reset(maestro);
    while (not(eof(maestro)))do
    begin
        with p do
        begin
            read(maestro, p);
            if (actual<minimo) then
            begin
                writeln(texto, cod,' ',precio:0:2);
                writeln(texto, actual,' ',minimo, ' ', nom);
            end;
        end;
    end;
    close(maestro);
    close(texto);
end;
var detalle:arch_detalle; maestro:arch_maestro;v:ventas;p:producto;
begin 
    importarMaestro();
    //cargarDetalle();
    abrirArchivos(detalle,maestro);
    leer(detalle,v);
    read(maestro,p);
    while (v.cod<>fin) do
    begin
        while (p.cod<>v.cod) do
            read(maestro,p);
        while(p.cod=v.cod) do
        begin
            p.actual:=p.actual-v.cant;
            leer(detalle,v);
        end;
        seek(maestro, filepos(maestro)-1);
        write(maestro, p);
    end;
    close(maestro);
    close(detalle);
    listar(maestro);
end.