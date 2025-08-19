{Una agencia dedicada a la venta de autos ha organizado su stock y, dispone en papel de la información de los autos en venta.
Implementar un programa que:
a)	Genere un árbol binario de búsqueda ordenado por patente identificatoria del auto en venta. 
Cada nodo del árbol debe contener patente, año de fabricación (2010..2018), la marca y el modelo.
b)	Invoque a un módulo que reciba el árbol generado en a) y una marca y retorne la cantidad de autos
 de dicha marca que posee la agencia. Mostrar el resultado. 
c)	Invoque a un módulo que reciba el árbol generado en a) y retorne una estructura con la información de los autos
 agrupados por año de fabricación.
d)	Contenga un módulo que reciba el árbol generado en a) y una patente y devuelva el año de fabricación del auto con
 dicha patente. Mostrar el resultado. 
}
program ejercicio_2;
type
str20=string[20];
auto=record
    pat:integer;
    anio:integer;
    marca:str20;
end;

arbol=^nodo;
nodo=record
    hd:arbol;
    hi:arbol;
    dato:auto;
end;
lista=^nodo2;
nodo2=record
    sig:lista;
    dato:auto;
end;
vector=array [2010..2018] of lista;
procedure cargardatos(var a:arbol);
procedure cargararbol(var a: arbol;au:auto);
begin
    if a=nil then begin
        new(a); a^.dato:=au; a^.hd:=nil; a^.hi:=nil;
    end
    else
    if au.pat< a^.dato.pat then
        cargararbol(a^.hi,au)
    else cargararbol(a^.hd,au)
end;
procedure leerauto(var au:auto);
begin
    randomize;
    write('marca: '); readln(au.marca);
    if au.marca<>'zzz' then begin
        write('Anio: '); au.anio:=random(8)+2010; writeln(au.anio);
        write('Patente: '); au.pat:= random(9000)+999; writeln(au.pat);
    end;
end;
var au:auto;
begin
    leerauto(au);
    while au.marca<>'zzz' do
    begin
        cargararbol(a,au);
        leerauto(au);
    end;
end;

procedure imprimirarbol(a:arbol);
begin
    if a<>nil then begin
    imprimirarbol(a^.hi);
        writeln('Patente: ', a^.dato.pat,'| Marca: ', a^.dato.marca,' | anio: ', a^.dato.anio);
    imprimirarbol(a^.hd);
    end;
end;

procedure buscar(a:arbol);
procedure buscarmarca(a:arbol;marca:str20;var cant:integer);
begin
    if a<>nil then begin
        buscarmarca(a^.hi,marca,cant);
        if a^.dato.marca = marca then
            cant:= cant+1;
        buscarmarca(a^.hd,marca,cant);
    end;
end;
var marca:str20; cant:integer;
begin
    writeln('Ingrese la marca a buscar: '); readln(marca);
    cant:=0;
    buscarmarca(a,marca,cant);
    writeln('Hay ', cant,' autos marca ', marca);
end;
procedure crearvector(a:arbol; var v:vector);
procedure agregaratras(var pri:lista;a:auto);
Var nuevo,aux:lista;
Begin
    new (nuevo); nuevo^.dato:= a; nuevo^.sig:=nil; 
    if (pri = nil) then pri:= nuevo
    else begin
        aux:= pri;
        while (aux^.sig <> nil) do
        aux:= aux^.sig;
        aux^.sig:=nuevo;       
    end;
End;

begin
    if a<>nil then begin
        crearvector(a^.hi, v);
        agregaratras(v[a^.dato.anio],a^.dato);
        crearvector(a^.hd,v)
    end;
end;
procedure inicializarvector(var v:vector);
var i:integer;
begin
    for i:=2010 to 2018 do
        v[i]:=nil;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
    for i:=2010 to 2018 do 
    begin
        writeln('-------ANIO', i,'-------');
        while v[i]<>nil do begin
        writeln('Patente: ', v[i]^.dato.pat,'| Marca: ', v[i]^.dato.marca);
        v[i]:=v[i]^.sig;
        end;
    end;
end;
procedure buscarpatente(a:arbol);
function buscar(a:arbol; pat:integer):integer;
begin
    if a=nil then 
        buscar:=-1
    else if pat=a^.dato.pat then 
        buscar:=a^.dato.anio
    else 
    if pat<a^.dato.pat then 
            buscar:= buscar(a^.hi,pat)
    else buscar:= buscar(a^.hd,pat);
end;
var pat, anio :integer;
begin
    write('ingrese la patente a buscar: '); readln(pat);
    anio:= buscar(a,pat);
    if anio<> -1 then
        writeln('Anio del auto de patente ', pat, ': ', anio)
    else
        writeln('No existe un auto con esa patente.');
end;
var a:arbol; v:vector;
begin
    inicializarvector(v);
    cargardatos(a);
    imprimirarbol(a);
    //buscar(a);
    crearvector(a,v);
    imprimirvector(v);
    buscarpatente(a);
end.