program Ej_10;
const fin=9999;
type
    cadena= string[20];
    informe=record
        depto:integer;
        divi:integer;
        nro:integer;
        cat:integer;
        horas:integer;
    end;

    archivo=file of informe;

    categorias= array[1..15] of real;
procedure cargarVector(var cat:categorias);
var texto:text; v:real; i:integer;
begin
    assign(texto,'categorias.txt');
    reset(texto);
    while (not(eof(texto))) do
    begin
        read(texto, i, v);
        cat[i]:= v;//cargo en cada pos del vector el valor hora de esa cat
    end;
    close(texto);
end;

procedure leer(var arch:archivo; var i:informe);
begin
    if (not(eof(arch))) then
        read(arch, i)
    else 
    begin
        i.depto:=fin;
        i.divi:=fin;
        i.nro:=fin;
    end;
end;
procedure importarMaestro(var arch:archivo);
var texto:text; i:informe;
begin
    assign(texto, 'informe.txt');
    reset(texto);
    assign(arch, 'archivo');
    rewrite(arch);
    while(not(eof(texto))) do
    begin
        with i do
        begin
            read(texto, depto, divi, nro, cat, horas);
        end;
        write(arch, i);
    end;
    close(arch);
    close(texto);
end;

var cat:categorias;i:informe; act:informe;arch:archivo; hs_depto:integer; m_depto:real;hs_div:integer; m_div:real;hs_e:integer; m_e:real;
begin 
    importarMaestro(arch);
    assign(arch, 'archivo');
    reset(arch);
    cargarVector(cat);
    leer(arch,i);
    while (i.depto<>fin) do//me muevo hasta que no haya llegado al final
    begin
        act:=i;
        hs_depto:=0; //inicializo horas y monto del depto
        m_depto:=0;
        writeln('Departamento ', i.depto);//titulo depto
        writeln('___________________________________________________');
        while(i.depto=act.depto) do //me mantengo en el departamento
        begin
            act:=i;
            hs_div:=0; //inicializo horas y monto de la division
            m_div:=0;
            writeln('Division ', i.divi);//titulo division
            writeln('----------');
            while((i.depto=act.depto) and (i.divi=act.divi)) do
            begin
                act:=i;
                hs_e:=0;
                m_e:=0;//inicializo las cosas del empleado
                write('Empleado ', i.nro,' : ');//titulo del empleado
                while((i.nro=act.nro) and (i.divi=act.divi) and (i.depto=act.depto)) do //mientras me mantenga en el empleado
                begin //necesito los ands por si el nro de empleado es el mismo pero cambie de depto o de division
                    hs_e:=hs_e+i.horas;
                    m_e:= m_e+ i.horas * cat[i.cat];//calculo el valor de las horas a medida que avanzo, podría hacerlo al final
                    leer(arch, i);
                end;
                hs_div:=hs_div+hs_e;
                m_div:= m_div+m_e;//una vez que ya sume todo lo del empleado, lo sumo a lo de la division
                writeln('horas: ', hs_e,'. Importe: ', m_e:0:2);
            end;
                hs_depto:=hs_depto+hs_div;
                m_depto:= m_depto+m_div;//una vez que ya sume todo lo de la division, lo sumo a lo del departamento
                writeln('Total de horas division: ', hs_div,'. Monto total: ', m_div:0:2);
        end;
        writeln('Total de horas departamento: ', hs_depto,'. Monto total departamento: ', m_depto:0:2);
    end;
end.