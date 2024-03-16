program Ej_12;
const fin=9999;
type
    cadena=string[20];
    acceso=record
        anio:integer;
        mes:integer;
        dia:integer;
        id:integer;
        tiempo:real;
    end;
    archivo=file of acceso;
procedure leer(var arch:archivo; var a:acceso);
begin
    if(not(eof(arch))) then
        read(arch,a)
    else
    begin
        a.anio:=fin;
        a.mes:=fin;
        a.dia:=fin;
        a.id:=fin;
    end;
end;
procedure importarArchivo();
var arch:archivo; a:acceso; texto:text;
begin
    assign(arch, 'accesos');
    rewrite(arch);
    assign(texto, 'accesos.txt');
    reset(texto);
    while(not(eof(texto))) do
    begin
        with a do
        begin
            readln(texto, anio, mes, dia, id,tiempo);
        end;
        write(arch, a);
    end;
    close(arch);
    close(texto);
end;

//ant es el viejo, a el leido
var arch:archivo; ant:acceso;a:acceso;t_anio:real;t_mes:real; t_dia:real; t_user:real; ingreso:integer;ok:boolean;
begin 
    importarArchivo();
    assign(arch, 'accesos');
    ok:=true;
    reset(arch);
    leer(arch,a);
    write('ingrese el anio a informar: ');
    readln(ingreso);
    while((a.anio<>fin) and (ok))do
    begin
        while ((a.anio<>ingreso) and (a.anio<>fin)) do
            leer(arch,a);
        if(a.anio= ingreso) then
        begin 
            ant:=a;
            t_anio:=0;
            writeln('Anio: ', ant.anio);
            while(a.anio= ingreso) do
            begin
                ant:=a;
                writeln('    Mes: ', ant.mes);
                t_mes:=0;
                while((a.mes= ant.mes)and (a.anio=ingreso)) do
                begin
                    ant:=a;
                    writeln('        dia: ', ant.dia);
                    t_dia:=0;
                    while((a.mes= ant.mes)and (a.anio=ingreso)and (a.dia=ant.dia)) do
                    begin
                        ant:=a;
                        write('            usuario: ', ant.id);
                        t_user:=0;
                        while((a.mes= ant.mes)and (a.anio=ingreso)and (a.dia=ant.dia)and (a.id=ant.id)) do
                        begin
                            t_user:=t_user+ant.tiempo;
                            leer(arch, a);
                            if (a.anio<>ingreso) then
                                ok:=false;
                        end;
                            writeln('  Tiempo Total el ', ant.dia, '/', ant.mes, ': ', t_user:0:2);//cambie el user, imprimo lo de ant. user y sumo a tot dia
                            t_dia:= t_dia+t_user;
                    end;
                        writeln('        Tiempo Total (todos) el ', ant.dia, '/', ant.mes, ': ', t_dia:0:2);//cambie el dia, imprimo lo de ant. dia y sumo a tot mes
                        t_mes:= t_mes+t_dia;
                end;
                    writeln('    Tiempo Total en ', ant.mes, ': ', t_mes:0:2);//cambie el mes, imprimo lo de ant. mes y sumo a tot anio
                    t_anio:= t_anio+t_mes;
            end;
                writeln('    Tiempo Total en ', ant.anio, ': ', t_anio:0:2);
        end
        else
            writeln('El anio ingresado no se encuentra en el archivo.');
    end;
    close(arch);
end.