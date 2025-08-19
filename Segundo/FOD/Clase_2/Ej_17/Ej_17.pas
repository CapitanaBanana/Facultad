program Ej_17;
const fin=9999;
dimF=10;
type
    cadena= string[20];
    moto=record
        cod:integer;
        nom:cadena;
        desc:cadena;
        modelo:integer;
        marca:cadena;
        stock:integer;
    end;
    fec=record
        dia:integer;
        mes:integer;
        anio:integer;
    end;
    venta= record
        cod:integer;
        precio:real;
        fecha:fec;
    end;
    ventas= file of venta;
    vec_detalles= array [1..dimF] of ventas;
    vec_registros= array[1..dimF] of venta;
    maestro= file of moto;
procedure leer(var arch: ventas; var v:venta);
begin
    if (not(eof(arch))) then
        read(arch,v)
    else
        v.cod:=fin;
end;
procedure abrirArchivos(var vec_det: vec_detalles; var vec_reg:vec_registros);
var i:integer;s:string;
begin
    for i:=1 to dimF do
    begin
        Str(i,s);
        assign(vec_det[i], 'det'+s);
        reset(vec_det[i]);
        leer(vec_det[i], vec_reg[i]);
    end;
end;
procedure minimo(var vec_det: vec_detalles; var vec_reg:vec_registros; var min:venta);
var i:integer;min_pos:integer;
begin
    for i:=1 to dimF do
    begin
        if (vec_reg[i].cod<min.cod) then
        begin
            min:= vec_reg[i];
            min_pos:=i;
        end;
    end;
    leer(vec_det[min_pos], vec_reg[min_pos]);
end;

var ma:maestro; vec_det: vec_detalles;vec_reg:vec_registros; m:moto;i:integer;min:venta; max:integer; max_moto:moto; vendidas:integer;
begin 
    assign(ma, 'maestro');
    reset(ma);
    max:=-1;
    abrirArchivos(vec_det,vec_reg);
    minimo(vec_det,vec_reg,min);
    read(ma, m);
    while (min.cod<>fin) do
    begin
        while (min.cod<>m.cod) do
            read(ma,m);
        vendidas:=0;
        while(min.cod=m.cod) do
        begin
            vendidas:= vendidas+1;;
            minimo(vec_det,vec_reg,min);
        end;
        if (vendidas>max) then
        begin
            max:=vendidas;
            max_moto:= m;
        end;
        m.stock:=m.stock-vendidas;
        seek(ma, filepos(ma)-1);
        write(ma, m);
    end;
    close(ma);
    for i:=1 to dimF do
        close(vec_det[i]);
    writeln('La moto mas vendida fue: ', max_moto.cod, '(', max,' ventas)');
end.