program Ej_5;
const fin='zzz';
type
    cadena=string[20];
    reg_maestro=record
        nom:cadena;
        cant:integer;
        enc:integer;
    end;
    reg_detalle=record
        nom:cadena;
        loc:integer;
        cant:integer;
        enc:integer;
    end;
    maestro=file of reg_maestro;
    detalle=file of reg_detalle;


procedure importDetalle1();
var texto:text; r:reg_detalle;d:detalle;
begin
    assign(texto, 'censo1.txt');
    assign(d,'censo1');
    reset(texto);
    rewrite(d);
    while (not(eof(texto))) do
    begin
        with r do
        begin
            readln(texto, nom);
            readln(texto, loc, cant, enc);
        end;
            write(d, r);
    end;
    close(texto);
    close(d);
end;

procedure importDetalle2();
var texto:text; r:reg_detalle; d:detalle;
begin
    assign(texto, 'censo2.txt');
    assign(d,'censo2');
    reset(texto);
    rewrite(d);
    while (not(eof(texto))) do
    begin
        with r do
        begin
            readln(texto, nom);
            readln(texto, loc, cant, enc);
        end;
            write(d, r);
    end;
    close(texto);
    close(d);
end;

procedure abrirArchivos(var d1:detalle;var d2:detalle; var ma:maestro);
begin
    assign(ma, 'maestro');
    assign(d1, 'censo1');
    assign(d2, 'censo2');
    reset(d1);
    reset(d2);
    reset(ma);
end;

procedure cargarMaestro();
var r:reg_maestro;m:maestro;
begin
    assign(m, 'maestro');
    rewrite(m);
    write('Provincia: ');
    readln(r.nom);
    while (r.nom<>fin) do
    begin
        write('Alfabetizados: ');
        readln(r.cant);
        write('Encuestados: ');
        readln(r.enc);
        write(m, r);
        write('Provincia: ');
        readln(r.nom);
    end;
    close(m);
end;
procedure leer(var d:detalle; var r:reg_detalle);
begin
    if (not(eof(d))) then
        read(d,r)
    else
        r.nom:=fin;
end;
procedure minimo(var r1: reg_detalle; var r2: reg_detalle; var min: reg_detalle; var d1:detalle; var d2:detalle);
begin
    if (r1.nom<r2.nom) then
    begin
        min:=r1;
        leer(d1, r1);
    end
    else
    begin
        min:=r2;
        leer(d2, r2);
    end;

end;
procedure exportarMaestro();
var texto:text;reg:reg_maestro; ma:maestro;
begin
    assign(ma, 'maestro');
    reset(ma);
    assign(texto,'maestro.txt');
    rewrite(texto);
    while (not(eof(ma))) do
    begin
        read(ma,reg);
        with reg do
        begin
            writeln(texto, 'Provincia: ',nom,'| cant: ', cant,'| enc: ', enc);
        end;
    end;
    close(ma);
    close(texto);
end;
var ma:maestro; d1:detalle; d2:detalle; r1:reg_detalle; r2:reg_detalle; min:reg_detalle;act:reg_maestro;rm:reg_maestro; t_cant:integer; t_enc:integer;
begin 
    cargarMaestro();
    importDetalle1();
    importDetalle2();
    abrirArchivos(d1,d2,ma);
    leer(d1,r1);
    leer(d2,r2);
    minimo(r1,r2,min,d1,d2);
    while (min.nom <>fin) do
    begin
        read(ma, rm);
        while (rm.nom<>min.nom) do
            read(ma,rm);
        t_cant:=0;
        t_enc:=0;
        while (min.nom=rm.nom) do
        begin
            t_cant:=t_cant+ min.cant;
            t_enc:=t_enc+min.enc;
            minimo(r1,r2,min,d1,d2);
        end;
        rm.cant:= rm.cant+t_cant;
        rm.enc:= rm.enc+ t_enc;
        seek(ma, filepos(ma)-1);
        write(ma,rm);
    end;
    close(ma);
    close(d1);
    close(d2);
    exportarMaestro();
end.