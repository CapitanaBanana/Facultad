{2.	Escribir un programa que:
a. Implemente un módulo que lea información de ventas de un comercio. De cada venta se lee código de producto, 
fecha y cantidad de unidades vendidas. La lectura finaliza con el código de producto 0. Un producto puede estar en más de una venta. Se pide:
i. Generar y retornar un árbol binario de búsqueda de ventas ordenado por código de producto.
ii. Generar y retornar otro árbol binario de búsqueda de productos vendidos ordenado por código de producto. Cada nodo del árbol debe
contener el código de producto y la cantidad total vendida.
    Nota: El módulo debe retornar los dos árboles.
b. Implemente un módulo que reciba el árbol generado en i. y un código de producto y retorne la cantidad total de unidades vendidas de ese producto.
c. Implemente un módulo que reciba el árbol generado en ii. y un código de producto y retorne la cantidad total de unidades vendidas de ese producto.
}
program examen;
type
dias=1..31;
meses=1..12;
anios=2000..2022;
fecha=record
    dia:dias;
    mes:meses;
    anio:anios;
end;
venta=record
    cod:integer;
    fec:fecha;
    cant:integer;
end;
producto=record
    cod:integer;
    cant:integer;
end;
arbolv=^nodo;
nodo=record
    hi:arbolv;
    hd:arbolv;
    dato:venta;
end;
arbolp=^nodop;
nodop=record
    hi:arbolp;
    hd:arbolp;
    dato:producto;
end;

procedure cargararboles(var ap:arbolp;var av:arbolv);
procedure leerventa(var v:venta);
begin
    randomize;
	write('Cod: '); readln(v.cod);
	if (v.cod<>0) then
	begin
		write('fecha de venta: '); v.fec.dia:=random(31)+1; v.fec.mes:=random(12)+1;v.fec.anio:=(22)+2000;
		writeln(v.fec.dia,'/',v.fec.mes,'/',v.fec.anio);
		write('Cantidad vendida: '); v.cant:= random(20)+1;
		writeln(v.cant); writeln;
	end;
end;
procedure cargarventas(var a:arbolv;v:venta);
begin
    if a=nil then begin
        new(a); a^.hd:=nil; a^.hi:=nil;a^.dato:=v;
    end
    else begin
        if a^.dato.cod>v.cod then
            cargarventas(a^.hi,v)
        else cargarventas(a^.hd,v);
    end;
end;
procedure cargarproductos(var a:arbolp; v:venta);
begin
    if a<>nil then begin
        if a^.dato.cod<v.cod then begin
            cargarproductos(a^.hd,v);
        end
        else if a^.dato.cod>v.cod then
            cargarproductos(a^.hi,v)
        else begin  a^.dato.cant:= a^.dato.cant+v.cant; end;
    end
    else begin
        new(a); a^.hd:=nil; a^.hi:=nil; a^.dato.cod:=v.cod; a^.dato.cant:=v.cant;
    end;
end;
var v:venta;
begin
    ap:=nil;
    av:=nil;
    leerventa(v);
    while v.cod<>0 do
    begin
        cargarproductos(ap,v);
        cargarventas(av,v);
        leerventa(v);
    end;
end;

procedure imprimirp(a:arbolp);
begin
    if a<>nil then begin
        imprimirp(a^.hi);
        writeln ('Cod: ',a^.dato.cod, '| Unidades: ', a^.dato.cant);
        imprimirp(a^.hd);
    end;
end;
procedure imprimirv(a:arbolv);
begin
    if a<>nil then begin
        imprimirv(a^.hi);
        writeln ('Cod: ',a^.dato.cod, '| Unidades: ', a^.dato.cant);
        imprimirv(a^.hd);
    end;
end;

procedure buscarcod(a:arbolv);
function buscar(a:arbolv; cod:integer): integer;
begin
    if a=nil then 
        buscar:=-1
    else if a^.dato.cod < cod then
        buscar:= buscar(a^.hd,cod)
    else if a^.dato.cod > cod then
        buscar:= buscar(a^.hi,cod)
    else buscar:= a^.dato.cant;
end;
var cod,cant:integer;
begin
    write('Ingrese el codigo a buscar: '); readln(cod);
    cant:=buscar(a,cod);
    if cod<>-1 then writeln('Se vendieron ', cant, ' unidades codigo ', cod) else writeln('No existe un producto con dicho codigo');
end;
procedure buscarcod2(a:arbolp);
procedure buscar(a:arbolp; cod:integer; var suma:integer);
begin
    suma:=0;
    if a<>nil then 
    begin  
        if a^.dato.cod < cod then
            buscar(a^.hd,cod,suma)
        else if a^.dato.cod > cod then begin
            buscar(a^.hi,cod,suma);
            buscar(a^.hd,cod,suma);
        end
        else suma:= suma+ a^.dato.cant;
    end;
end;
var cod,suma:integer;
begin
    write('Ingrese el codigo a buscar: '); readln(cod);
    buscar(a,cod,suma);
    if suma<>0 then writeln('Se vendieron ', suma, ' unidades codigo ', cod) else writeln('No existe un producto con dicho codigo');
end;
var ap:arbolp;av:arbolv;
begin
    cargararboles(ap,av);
    writeln('-------------ARBOL PRODUCTOS-------------');
    imprimirp(ap);
    writeln('-------------ARBOL VENTAS-------------');
    imprimirv(av);
    //buscarcod(av);
    buscarcod2(ap);
end.
