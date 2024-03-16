program Ej_18;
const fin=9999;
type
    cadena=string[20];
    fec=record
        dia:integer;
        mes:integer;
        anio:integer;
    end;
    reporte=record
        cod_loc:integer;
        cod_muni:integer;
        nom_loc:cadena;
        nom_muni:cadena;
        cod_hos:integer;
        nom_hos:cadena;
        fecha:fec;
        casos:integer;
    end;
    archivo=file of reporte;
procedure leer(var arch:archivo; var r:reporte);
begin
    if (not(eof(arch))) then
        read(arch, r)
    else 
    begin
        r.cod_loc:=fin;
        r.cod_muni:=fin;
        r.cod_hos:=fin;
    end;
end;
procedure importarArchivo();
var texto:text; arch:archivo; r:reporte;
begin
    assign(arch, 'archivo');
    assign(texto, 'archivo.txt');
    reset(texto);
    rewrite(arch);
    while (not(eof(texto))) do
    begin
        with r do
        begin
            readln(texto, cod_loc, nom_loc);
            readln(texto, cod_muni, nom_muni);
            readln(texto, cod_hos, nom_hos);
            readln(texto, fecha.anio, fecha.mes, fecha.dia, casos);
        end;
        write(arch,r);
    end;
    close(arch);
    close(texto);
end;

var arch:archivo; r:reporte;cant_hos:integer; cant_loc:integer; cant_muni:integer;cant_prov:integer; ant:reporte;archMuchos:text;
begin 
    importarArchivo();
    assign(arch, 'archivo');
    reset(arch);
    assign(archMuchos, 'archMuchos');
    rewrite(archMuchos);
    leer(arch,r);
    cant_prov:=0;
    while (r.cod_loc<>fin) do
    begin
        ant:=r;
        cant_loc:=0; //inicializo cant x localidad cuando estoy por entrar a una loc nueva.
        writeln('Localidad: ', r.nom_loc);
        while(r.cod_loc=ant.cod_loc) do
        begin
            ant:=r;
            writeln('    Municipalidad: ', r.nom_muni);
            cant_muni:=0;//inicializo cant x muni cuando estoy por entrar a una muni nueva.
            while((r.cod_muni=ant.cod_muni) and(r.cod_loc=ant.cod_loc)) do
            begin
                ant:=r;
                write('        Hospital: ', r.nom_hos);
                cant_hos:=0;
                while((r.cod_hos=ant.cod_hos) and (r.cod_muni=ant.cod_muni) and(r.cod_loc=ant.cod_loc)) do
                begin
                    cant_hos:=cant_hos+r.casos;
                    leer(arch,r)
                end;//cambie de hospital, imprimo cantidad
                writeln('..........Cantidad de casos: ', cant_hos);
                cant_muni:=cant_muni+cant_hos;
                if (cant_muni>1500) then
                begin
                    writeln(archMuchos, ant.nom_loc, ant.nom_muni,' ', cant_muni);
                end;
            end;//cambie de municipalidad
            writeln('    Cantidad de casos ', ant.nom_muni,': ', cant_muni);
            cant_loc:=cant_loc+cant_muni;
        end;
        cant_prov:=cant_prov+cant_loc;
        writeln('Cantidad de casos ', ant.nom_loc,': ', cant_loc);
    end;
    writeln('Cantidad de casos de la Provincia: ', cant_prov);
    close(arch);
    close(archMuchos);
end.