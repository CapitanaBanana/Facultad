program Ej_9;
const fin=9999;
type 
    cadena=string[20];
    mesa= record
        prov:integer;
        loc:integer;
        nro:integer;
        cant:integer;
    end;
    votos= file of mesa;

procedure importarVotos();
var texto:text; m:mesa;arch:votos;
begin
    assign(texto, 'votos.txt');
    reset(texto);
    assign(arch,'votos');
    rewrite(arch);
    while (not(eof(texto))) do
    begin
        with m do
        begin
            readln(texto, prov, loc, nro, cant);
        end;
        write(arch,m);
    end;
    close(arch);
    close(texto);
end;

procedure leer(var arch:votos; var m: mesa);
begin
    if (not(eof(arch))) then 
        read(arch,m)
    else
    begin
        m.prov:=fin;
        m.loc:=fin;
    end;
end;

var arch:votos; m:mesa; act:mesa; t_prov:integer; t_loc:integer;total:integer; ant:integer;
begin 
    importarVotos();
    assign(arch, 'votos');
    reset(arch);
    leer(arch, m);
    total:=0;
    while (m.prov<>fin) do
    begin//la logica esta toda mal :(
        t_prov:=0;
        act:=m;
        writeln('Codigo de provincia: ', act.prov);
        while (m.prov=act.prov) do
        begin
            act:=m;
            t_loc:=0;
            while ((m.prov=act.prov)and (m.loc=act.loc)) do
            begin
                t_loc:=t_loc+m.cant;
                leer(arch,m);
            end;
            writeln('Total localidad ',act.loc,': ', t_loc);
            t_prov:= t_prov+t_loc;
        end;
        writeln('Total provincia ',act.prov,': ', t_prov);
        total:= total+t_prov;
    end;
    writeln('Total general: ', total);
    close(arch);
end.