program Ej_8;
const fin=9999;
type 
    cadena=string[20];
    venta= record
        cod:integer;
        nom:cadena;
        ape:cadena;
        anio:integer;
        mes:integer;
        dia:integer;
        monto:real;
    end;
    archivo= file of venta;
procedure leer(var arch:archivo;var v:venta);
begin
    if (not(eof(arch))) then
        read(arch,v)
    else 
    begin
        v.cod:=fin;
        v.anio:=fin;
    end;
end;

procedure importarMaestro(var arch:archivo);
var texto:text;v:venta;
begin
    assign(texto, 'maestro.txt');
    reset(texto);
    assign(arch, 'maestro');
    rewrite(arch);
    while (not(eof(texto))) do
    begin
        with v do
        begin
            readln(texto, cod, anio, mes, dia, ape);
            readln(texto, monto, nom);
        end;
        write(arch, v);
    end;
    close(texto);
    close(arch);
end;

var arch:archivo;v:venta;act:venta;total:real;
begin
    importarMaestro(arch);
    assign(arch,'maestro');
    reset(arch);
    leer(arch,v);
    total:=0;
    while (v.cod<>fin) do
    begin
        act:=v;
        while (act.cod=v.cod) do
        begin
            act:=v;
            act.monto:=0;
            while(((act.anio=v.anio)and(act.mes=v.mes))) do
            begin
                total:=total+v.monto;
                act.monto:= act.monto+v.monto;
                leer(arch,v);
            end;
            with act do
            begin
                writeln('cliente: ', cod,'. ', nom,' ', ape);
                writeln(mes,'/', anio,'. Monto: ', monto:0:2);
            end;
        end;
    end;
    writeln('Total de la empresa: ', total:0:2);
    close(arch);
end.
