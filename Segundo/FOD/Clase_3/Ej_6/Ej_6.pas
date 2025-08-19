program ej_6;
type
    cadena=string[20];
    prenda=record
        cod:integer;
        desc:cadena;
        colores:cadena;
        stock:integer;
        precio:integer;
    end;
    venta=record
        cod:integer;
    end;
    maestro=file of prenda;
    detalle=file of venta;

procedure importarMaestro();
var arch:maestro; texto:text;p:prenda;
begin
    assign(arch, 'maestro');
    rewrite(arch);
    assign(texto, 'maestro.txt');
    reset(texto);
    while (not(eof(texto))) do
    begin
        with p do
        begin
            readln(texto, cod, stock, colores);
            readln(texto, precio, desc);
        end;
        write(arch, p);
    end;
    close(arch);
    close(texto);
end;

procedure importarDetalle();
var arch:detalle; texto:text;v:venta;
begin
    assign(arch, 'detalle');
    rewrite(arch);
    assign(texto, 'detalle.txt');
    reset(texto);
    while (not(eof(texto))) do
    begin
        readln(texto, v.cod);
        write(arch, v);
    end;
    close(arch);
    close(texto);
end;
procedure bajas(var ma:maestro; var det:detalle);
var v:venta; p:prenda; seguir:boolean;
begin
    while(not(eof(det))) do
    begin
        read(det,v);
        seek(ma, 0);
        seguir:=true;
        while (not(eof(ma)) and seguir) do
        begin
            read(ma, p);
            if (p.cod=v.cod) then
            begin
                seguir:=false;
                p.stock:= p.stock-(2*p.stock);
                seek(ma, filepos(ma)-1);
                write(ma,p);
            end;
        end;
    end;
    close(ma);
    close(det);
end;
procedure compactar();
var arch:maestro; texto:text;p:prenda;
begin
    assign(arch, 'maestro');
    reset(arch);
    assign(texto, 'actualizado.txt');
    rewrite(texto);
    while (not(eof(arch))) do
    begin
        read(arch,p);
        if(p.stock>0) then 
        begin
            with p do
            begin
                writeln(texto, cod,' ', stock, colores);
                writeln(texto, precio, desc);
            end;
        end;
    end;
    close(arch);
    close(texto);
end;
var ma:maestro; det:detalle;
begin
    importarMaestro();
    importarDetalle();
    writeln('hola');
    assign(ma, 'maestro');
    assign(det,'detalle');
    reset(ma);
    reset(det);
    bajas(ma,det);
    compactar();
end.