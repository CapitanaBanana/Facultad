program Ej_16;
const fin=9999;
dimF=100;
type 
    cadena= string[20];
    fecha=record
        dia:integer;
        mes:integer;
        anio:integer;
    end;
    emision=record
        fec:fecha;
        cod:integer;
        nom:cadena;
        desc:cadena;
        precio:real;
        ejemplares:integer;
        ventas:integer;
    end;
    venta=record
        fec:fecha;
        cod:integer;
        ventas:integer;
    end;

    maestro= file of emision;
    ventas= file of venta;
    vec_registro= array [1..dimF] of venta; 
    vec_archivos= array[1..dimF] of ventas;

procedure leer(var arch:ventas; var v:venta);
begin
    if (not(eof(arch)))then
        read(arch, v)
    else
        begin
            v.cod:= fin;
        end;
end;
procedure abrirArchivos(var vec_arch:vec_archivos;var ma:maestro; var vec_reg:vec_registro);
var i:integer;s:string;
begin
    for i:=1 to dimF do
    begin
        Str(i,s);
        assign(vec_arch[i], 'det'+s);
        reset(vec_arch[i]);
        leer(vec_arch[i],vec_reg[i]);
    end;
    assign(ma, 'maestro');
    reset(ma);
end;

procedure minimo(var vec_reg:vec_registro; var min:venta; var vec_arch:vec_archivos);
var i:integer;min_pos:integer;
begin
    for i:=1 to dimF do
    begin
        if (((vec_reg[i].cod<min.cod) or ((vec_reg[i].cod=min.cod) and (vec_reg[i].fec.anio<min.fec.anio)) or ((vec_reg[i].cod=min.cod) and (vec_reg[i].fec.anio=min.fec.anio) and(vec_reg[i].fec.mes<min.fec.mes))) or (((vec_reg[i].cod=min.cod) and (vec_reg[i].fec.anio=min.fec.anio) and(vec_reg[i].fec.mes=min.fec.mes)and(vec_reg[i].fec.dia<min.fec.dia)))) then
        begin
            min:= vec_reg[i];
            min_pos:=i;
        end;
        leer(vec_arch[min_pos],vec_reg[min_pos]);
    end;
end;
procedure minmax(var e:emision; var max:emision; var min:emision);
begin
    if (e.ventas>max.ventas) then
        begin
            max:=e;
        end;
        if (e.ventas<min.ventas) then
        begin
            min:=e;
        end;
end;
var vec_reg:vec_registro; min:venta; vec_arch:vec_archivos; ma:maestro; e:emision; i: integer; max: emision; mine:emision; 
begin 
    abrirArchivos(vec_arch,ma, vec_reg);
    minimo(vec_reg,min,vec_arch);
    max.ventas:=-1;
    min.ventas:=fin;
    read(ma,e);
    while(min.cod<>fin) do
    begin
        while((min.cod<>e.cod) or (min.fec.anio<>e.fec.anio) or (min.fec.mes<>e.fec.mes) or (min.fec.dia<>e.fec.dia)) do //me muevo hasta que encuentro el mismo en el maestro 
        begin
            minmax(e, max,mine);
            read(ma,e);
        end;
        e.ejemplares:= e.ejemplares-min.ventas;
        e.ventas:= e.ventas+ min.ventas;
        seek(ma, filepos(ma)-1);//me posiciono  en el maestro
        write(ma, e);
        minmax(e, max,mine);// me fijo si tengo minimo o maximo
        minimo(vec_reg,min,vec_arch);//leo uno nuevo
    end;
    writeln('El semanario con mas ventas es: ', max.nom);
    writeln('El semanario con menos ventas es: ', mine.nom);
    close(ma);
    for i:=1 to dimF do
    begin
        close(vec_arch[i]);
    end;
end.