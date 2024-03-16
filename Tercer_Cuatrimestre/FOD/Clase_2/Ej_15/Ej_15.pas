program Ej_15;
const fin=9999;
dimF=10;
type
    cadena= string[20];
    reg_maestro= record
        nom_prov:cadena;
        nom_loc:integer;
        prov:integer;
        loc:integer;
        luz:integer;
        gas:integer;
        chapa:integer;
        agua:integer;
        sanitarios:integer;
    end;
    reg_detalle=record
        loc:integer;
        prov:integer;
        construidas:integer;
        luz:integer;
        agua:integeR;
        gas:integer;
        sanitarios:integer;
    end;

    maestro= file of reg_maestro;
    detalle= file of reg_detalle;
    vec_detalles= array [1..dimF] of detalle;
    vec_registros= array[1..dimF] of reg_detalle;
//procedure
procedure leer(var arch:detalle; var r: reg_detalle);
begin
    if (not(eof(arch))) then
        read(arch, r)
    else
    begin
        r.prov:=fin;
        r.loc:=fin;
    end;
end;
procedure minimo(var vec_det:vec_detalles;var vec_reg:vec_registros; var min: reg_detalle);
var i:integer;min_pos:integer;
begin
    for i:=1 to dimF do
    begin
        if ((vec_reg[i].prov<min.prov) or ((vec_reg[i].prov=min.prov) and (vec_reg[i].loc<min.prov))) then
        begin
            min:= vec_reg[i];
            min_pos:=i;
        end;
    end; 
    leer(vec_det[min_pos], vec_reg[min_pos])
end;
procedure abrirArchivos();
var vec_det:vec_detalles; ma:maestro; i:integer; s:string;vec_reg:vec_registros;
begin
    for i:=0 to dimF do
    begin
        Str(i,s);
        assign(vec_det[i], 'det'+s);
        reset(vec_det[i]);
        leer(vec_det[i], vec_registros[i]);
    end;
    assign(ma, 'maestro');
    reset(ma);
end;

var ma:maestro; vec_det: vec_detalles; vec_reg: vec_registros;min: reg_detalle; reg_ma:reg_maestro; cant:integer; i:integer;
begin 
    min.prov:=fin;
    abrirArchivos();
    cant:=0;
    minimo(vec_det,vec_reg,min);
    read(ma, reg_ma);
    while ((reg_ma.prov<>min.prov) and (reg_ma.loc<>min.loc)) do
        read(ma, reg_ma);
    while(min.prov<>fin) do
    begin
        while ((reg_ma.prov<>min.prov) or (reg_ma.loc<>min.loc))do
        begin
            if (reg_ma.chapa=0) then
                cant:= cant+1;
            read(ma,reg_ma);
        end;
        reg_ma.luz :=reg_ma.luz- min.luz;
        reg_ma.gas:= reg_ma.gas-min.gas;
        reg_ma.agua:= reg_ma.agua-min.agua;
        reg_ma.sanitarios:= reg_ma.sanitarios-min.construidas;
        if (reg_ma.sanitarios=0) then
            cant:= cant+1;
        minimo(vec_det,vec_reg,min);
        seek(ma,filepos(ma)-1);
        write(ma, reg_ma);
    end;
    close(ma);
    for i:=1 to dimF do
        close(vec_det[i]);
    writeln('La cantidad de localidades sin viviendas de chapa es: ',cant);