program Ej_2;
type
    cadena=string[20];
    alumno= record
        cod:integer;
        ape:cadena;
        nom: cadena;
        materias:integer;
        finales:integer;
    end;
    materia= record
        cod: integer;
        materias:integer;
        finales:integer;
    end;
    detalle= file of materia;
    maestro= file of alumno;


procedure hacerMaestro();
var a:alumno; var mae:maestro; var tmae: text;
begin
    assign(tmae, 'alumnos.txt');
    reset (tmae);
    assign(mae, 'maestro');
    rewrite (mae);
    while (not(eof(tmae)))do
    begin
        with a do
        begin
            readln(tmae, cod, ape);
            readln(tmae, materias, finales, nom);
        end;
        write(mae, a);
    end;
    close(tmae);
    close(mae);
end;
procedure hacerDetalle();
var m:materia; tdet: text; var det:detalle;
begin
    assign(tdet, 'detalle.txt');
    reset (tdet);
    assign(det, 'detalle');
    rewrite (det);
    while (not(eof(tdet)))do
    begin
        with m do
        begin
            readln(tdet, cod, materias, finales);
        end;
        write(det, m);
    end;
    close(tdet);
    close(det);
end;
procedure abrirArchivos(var mae:maestro; var det: detalle);
begin
    assign(mae, 'maestro');
    assign(det, 'detalle');
    reset(mae);
    reset(det);
end;
procedure procesar();
const fin=0;
procedure leer(var det:detalle; var m:materia);
begin
    if (not(eof(det))) then
        read(det, m)
    else 
        m.cod:=fin;
end;

var mae:maestro; det: detalle; m:materia;a:alumno;
begin
    abrirArchivos(mae,det);
    leer(det,m);
    while (m.cod<>fin) do
    begin
        read(mae,a);
        while(a.cod<>m.cod) do
            read(mae,a);
        while (a.cod=m.cod)do
        begin
            a.materias:=a.materias+m.materias;
            a.finales:=a.finales+m.finales;
            leer(det,m);
        end;
        seek (mae, filepos(mae)-1);
        write(mae,a);
    end;
    close(mae);
    close(det);
end;
procedure actualizarTextoMaestro();
var texto: text; mae:maestro;a:alumno;
begin
    assign(texto, 'alumnos.txt');
    assign(mae, 'maestro');
    reset(mae);
    rewrite(texto);
    while (not(eof(mae)))do
    begin
        read(mae, a);
        with a do
        begin
            writeln(texto, cod, ape);
            writeln(texto, materias,' ', finales, nom);
        end;
    end;
    close(mae);
    close(texto);
end;
procedure cuatroMaterias();
var texto: text; mae:maestro;a:alumno;
begin
    assign(texto, 'cuatroMaterias.txt');
    assign(mae, 'maestro');
    reset(mae);
    rewrite(texto);
    while (not(eof(mae)))do
    begin
        read(mae, a);
        if (a.materias>4) then
        begin
            with a do
            begin
                writeln(texto, cod, ape);
                writeln(texto, materias,' ', finales, nom);
            end;
        end;
    end;
    close(mae);
    close(texto);
end;
begin 
    hacerMaestro();
    hacerDetalle();
    procesar();
    actualizarTextoMaestro();
    cuatroMaterias();
end.