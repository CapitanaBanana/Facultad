program Ej_4;
const dimF=3; fin=9999;
type
    log=record
        cod_usuario:integer;
        fecha:integer;
        tiempo_sesion:real;
    end;
    reg_maestro=record
        cod_usuario:integer;
        fecha:integer;
        tiempo_total:real;
    end;
    maestro=file of reg_maestro;
    detalle= file of log;
    registro_archivos= array [0..dimF] of file of log;
    reg_detalles= array [0..dimF] of log;
    

procedure leer(var arch:detalle;var dato:log);
begin
    if (not(eof(arch))) then
        read(arch, dato)
    else 
        dato.cod_usuario:=fin;
end;
procedure minimo(var reg_deta: reg_detalles;var reg_archivos:registro_archivos;var min:log);
var i:integer;min_pos:integer;
begin
    for i:=0 to dimF do
    begin
        if ((reg_deta[i].cod_usuario<min.cod_usuario)or (((reg_deta[i].cod_usuario=min.cod_usuario))and (reg_deta[i].fecha<min.fecha))) then
        begin
            min:=reg_deta[i];
            min_pos:=i;
        end;
    end;
    if (min.cod_usuario<>fin)
        leer(reg_archivos[min_pos],reg_deta[min_pos]);
end;
procedure abrirArchivos(var reg_deta: reg_detalles;var reg_archivos:registro_archivos; var ma:maestro);
var i:integer; s:String;
begin
    for i:=0 to dimF do
    begin
        Str(i,s);
        assign(reg_archivos[i], 'detalle'+s);
        reset(reg_archivos[i]);
        leer(reg_archivos[i], reg_deta[i])
    end;
    writeln('hola');
    assign(ma, 'maestro');
    rewrite(ma);
end;
procedure exportarMaestro();
var texto:text;reg:reg_maestro;ma:maestro;
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
            writeln(texto, 'Cod: ',cod_usuario,'| fec: ', fecha,'| tiempo: ', tiempo_total);
        end;
    end;
    close(ma);
    close(texto);
end;
var reg_deta:reg_detalles; reg_archivos:registro_archivos; ma:maestro;min:log;reg:reg_maestro;i:integer;
begin 
    min.cod_usuario:=-1;
    abrirArchivos(reg_deta,reg_archivos,ma);
    minimo(reg_deta,reg_archivos,min);
    reg.fecha:=min.fecha;
    reg.cod_usuario:=min.cod_usuario;
    read (ma,reg);
    while (min.cod_usuario<>fin)do 
    begin
        while(min.cod_usuario<>reg.cod_usuario) do
            read (ma,reg);
        while(min.cod_usuario=reg.cod_usuario) do
        begin
            reg.tiempo_total:=0;
            reg.fecha:= min.fecha;
            while(reg.fecha=min.fecha) do
            begin
                reg.tiempo_total:= reg.tiempo_total+min.tiempo_sesion;
                minimo(reg_deta,reg_archivos,min);
            end;
            write(ma, reg);
        end;
    end;
    close(ma);
    exportarMaestro();
    for i:=0 to dimF do
    begin
        close(reg_archivos[i]);
    end;
end.