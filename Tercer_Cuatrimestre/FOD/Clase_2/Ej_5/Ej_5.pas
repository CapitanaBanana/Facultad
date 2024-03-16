program Ej_5;
const fin=9999;
dimF=50;
type cadena=String[20];
    nacimiento=record
        partida:integer;
        nom:cadena;
        ciudad:cadena;
        matricula:integer;
    end;
    fec=record
        dia:integer;
        mes:integer;
        anio:integer;
        hora:integer;
    end;
    muerte=record
        partida:integer;
        nom:cadena;
        ciudad:cadena;
        matricula:integer;
        fecha:fec;
    end;
    maestro=record
        partida:integer;
        nom:cadena;
        ciudad:cadena;
        matricula:integer;
        m_matricula:integer;
        m_fecha:fec;
        m_ciudad:cadena;
    end;

    nacimientos=file of nacimiento;
    muertes= file of muerte;

    reg_arch_naci= array[1..dimF] of nacimientos; //array de archivos de nacimiento
    reg_arch_muer= array[1..dimF] of muertes;//array de archivos de muerte

    reg_naci= array[1..dimF] of nacimiento;
    reg_muer= array[1..dimF] of muerte;

    arch_maestro=file of maestro;

procedure leerMuerte(var arch:muertes; var m:muerte);
begin
    if (not(eof(arch))) then
        read(arch,m)
    else
        m.partida:=fin;
end;
procedure leerNacimiento(var arch:nacimientos; var n:nacimiento);
begin
    if (not(eof(arch))) then
        read(arch,n)
    else
        n.partida:=fin;
end;

procedure minimoNacimiento(var reg_arch_n: reg_arch_naci;var reg_n:reg_naci; var min: nacimiento);
var i:integer;min_pos:integer;
begin
    for i:=1 to dimF do
    begin
        if (reg_n[i].partida<min.partida) then
        begin
            min_pos:=i;
            min:= reg_n[i];
        end;
    end;
    if (min.partida<>fin) then
        leerNacimiento(reg_arch_n[min_pos],reg_n[min_pos]);
end;

procedure minimoMuerte(var reg_arch_m: reg_arch_muer;var reg_m:reg_muer; var min: muerte);
var i:integer;min_pos:integer;
begin
    for i:=1 to dimF do
    begin
        if (reg_m[i].partida<min.partida) then
        begin
            min_pos:=i;
            min:= reg_m[i];
        end;
    end;
    if (min.partida<>fin) then
        leerMuerte(reg_arch_m[min_pos],reg_m[min_pos]);
end;

//abro todos los archivos y los preparo 
procedure abrirArchivos(var reg_arch_m: reg_arch_muer;var reg_arch_n: reg_arch_naci;var reg_n:reg_naci;var reg_m: reg_muer;var m:arch_maestro);
var i:integer;s:string;
begin
    for i:=1 to dimF do
    begin
        Str(i,s);
        assign(reg_arch_m[i], 'muerte'+s);
        assign(reg_arch_n[i], 'nacimiento'+s);
        reset(reg_arch_m[i]);
        reset(reg_arch_n[i]);
        leerNacimiento(reg_arch_n[i], reg_n[i]);//cargo vector de nacimientos con el primer dato de cada arch
        leerMuerte(reg_arch_m[i], reg_m[i]);//cargo v de muer con primer dato de cada arch
    end;
    assign(m, 'maestro');
    rewrite(m);
end;

procedure exportarMaestro();
var texto:text; m:maestro;ma:arch_maestro;
begin
    assign(ma, 'maestro');
    assign(texto, 'maestro.txt');
    while (not(eof(ma))) do
    begin
        read(ma, m);
        with m do
        begin
            writeln(texto, partida,' ', matricula,' ', nom);
            writeln(texto, ciudad);
            writeln(texto, m_matricula,' ', m_fecha.anio,' ',m_fecha.mes,' ',m_fecha.dia,' ',m_fecha.hora,' ', m_ciudad);
        end;
    end;
    close(ma);
    close(texto);
end;

var reg_arch_n: reg_arch_naci; reg_arch_m: reg_arch_muer; reg_n:reg_naci;reg_m:reg_muer;m:arch_maestro;act:maestro;
min_n:nacimiento;min_m:muerte; i:integer;
begin 
    min_n.partida:=9999;
    min_m.partida:=9999;//inicializo las variables de min en numeros muy altos 
    abrirArchivos(reg_arch_m,reg_arch_n,reg_n,reg_m,m);
    minimoNacimiento(reg_arch_n,reg_n,min_n);
    act.partida:= min_n.partida;
    minimoMuerte(reg_arch_m,reg_m,min_m);
    while (min_n.partida<>fin)do
    begin
        if (min_n.partida=min_m.partida) then //si el cod coincide con uno de muerte, pongo en el reg los datos de la muerte
        begin
            act.m_matricula:= min_m.matricula;
            act.m_ciudad:= min_m.ciudad;
            act.m_fecha.dia:= min_m.fecha.dia;
            act.m_fecha.mes:= min_m.fecha.mes;
            act.m_fecha.anio:= min_m.fecha.anio;
            act.m_fecha.hora:= min_m.fecha.hora;
            minimoMuerte(reg_arch_m,reg_m,min_m);
        end
        else begin //si no se murio, meto valores invalidos
            act.m_matricula:= -1;
            act.m_ciudad:= 'no murio';
            act.m_fecha.dia:= -1;
            act.m_fecha.mes:= -1;
            act.m_fecha.anio:= -1;
            act.m_fecha.hora:= -1;
        end;
        act.nom:= min_n.nom;
        act.ciudad:=min_n.ciudad;
        act.matricula:= min_n.matricula;
        write(m, act); //escribo en el maestro
        minimoMuerte(reg_arch_m,reg_m,min_m); //leo otro nacimiento
    end;
    close(m);
    exportarMaestro();
    for i:=1 to dimF do
    begin
        close(reg_arch_m[i]);
        close(reg_arch_n[i]);
    end;
end.