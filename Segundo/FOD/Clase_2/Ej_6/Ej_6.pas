program Ej_6;
const fin=9999;
dimF=10;
type cadena=string[20];
    reg_detalle=record
        cod_loc:integer;
        cod_cepa:integer;
        act:integer;
        nuevos:integer;
        recuperados:integer;
        fallecidos:integer;
    end;
    reg_maestro=record
        cod_loc:integer;
        cod_cepa:integer;
        nom_cepa:cadena;
        act:integer;
        nuevos:integer;
        recuperados:integer;
        fallecidos:integer;
    end;

    detalle= file of reg_detalle;
    maestro= file of reg_maestro;

    vec_detalles=array [1..dimF] of detalle;
    primeros=array[1..dimF] of reg_detalle;

procedure leer(var arch:detalle; var d: reg_detalle);
begin
    if (not(eof(arch))) then
        read(arch,d)
    else
        d.cod_loc:=fin;
end;

procedure minimo(var vec_detalles:vec_detalles; var pri: primeros; var min: reg_detalle);
var i:integer;min_pos:integer;
begin
    for i:=1 to dimF do
    begin
        if ((pri[i].cod_loc< min.cod_loc) or ((pri[i].cod_loc= min.cod_loc) and (pri[i].cod_cepa<min.cod_cepa))) then
        begin
            min_pos:=i;
            min:= pri[i];
        end;
    end;
    if (min.cod_loc<>fin) then
        leer(vec_detalles[min_pos], pri[min_pos]);
end;

procedure abrirArchivos(var vec_detalles:vec_detalles; var pri: primeros; var ma:maestro);
var i:integer;s:string;
begin
    for i:=1 to dimF do
    begin
        Str(i,s);
        assign(vec_detalles[i], 'det'+s);
        reset(vec_detalles[i]);
        leer(vec_detalles[i],pri[i]);
    end;
    assign(ma,'maestro');
    reset(ma);
end;
procedure informar();
var reg:reg_maestro;loc_actual: reg_maestro; total:integer;ma:maestro;
begin   
    assign(ma, 'maestro');
    reset(ma);
    while (not(eof(ma))) do
    begin
        read(ma,reg);
        loc_actual:=reg;
        total:=0;
        while (loc_actual.cod_loc=reg.cod_loc) do
        begin
            total:= total+reg.act;
        end;
        if (total>50) then
            writeln('la localidad: ', loc_actual.cod_loc, ' tiene mas de 50 casos activos.');
    end;
    close(ma);
end;

var vec_det:vec_detalles; pri:primeros; m:maestro; min:reg_detalle;reg_ma:reg_maestro;
var i:integer;
begin 
    abrirArchivos(vec_det, pri, m);
    min.cod_loc:=9999;
    minimo(vec_det,pri,min);
    while (min.cod_loc<>fin) do
    begin
        read(m, reg_ma);
        while ((reg_ma.cod_loc<>min.cod_loc)or (reg_ma.cod_cepa<>min.cod_cepa)) do
            read(m, reg_ma);
        while ((reg_ma.cod_loc=min.cod_loc)and (reg_ma.cod_cepa=min.cod_cepa)) do
        begin
            reg_ma.fallecidos:= reg_ma.fallecidos+min.fallecidos;
            reg_ma.recuperados:= reg_ma.recuperados+ min.recuperados;
            reg_ma.act:= min.act;
            reg_ma.nuevos:= min.nuevos;
            minimo(vec_det,pri,min);
        end;
        seek(m, filepos(m)-1);
        write(m, reg_ma);
    end;
    close(m);
    for i:=1 to dimF do
        close(vec_det[i]);
    Informar();
end.