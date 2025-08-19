{3.- Un supermercado requiere el procesamiento de sus productos. De cada producto se conoce código, rubro (1..10), stock y precio unitario. Se pide:
a)	Generar una estructura adecuada que permita agrupar los productos por rubro. A su vez, para cada rubro, se requiere que la búsqueda 
de un producto por código sea lo más eficiente posible. La lectura finaliza con el código de producto igual a -1..
b)	Implementar un módulo que reciba la estructura generada en a), un rubro y un código de producto y retorne si dicho código 
existe o no para ese rubro.
c)	Implementar un módulo que reciba la estructura generada en a), y retorne, para cada rubro, el código y stock del producto con mayor código.
d)	Implementar un módulo que reciba la estructura generada en a), dos códigos y retorne, para cada rubro, la cantidad 
de productos con códigos entre los dos valores ingresados.
}
program ejercicio_3;
type
producto=record
    cod:integer;
    rubro:integer;
    stock:integer;
    precio:real;
end;
arbol=^nodo;
nodo=record
    hi:arbol;
    hd:arbol;
    dato:producto;
end;
vector=array [1..10] of arbol;

procedure cargarvector(var v:vector);
procedure leerproducto(var p:producto);
begin
    randomize;
    write('Codigo: '); readln(p.cod);
    if p.cod<>-1 then begin
    write('Rubro: '); p.rubro:=random(10)+1; writeln(p.rubro);
    write('Stock: '); p.stock:=random(100)+1; writeln(p.stock);
    write('Precio: '); p.precio:=(random(9999)+1)/random(200)+1; writeln(p.precio:0:2);writeln;
    end;
end;
procedure cargararbol(p:producto; var a:arbol);
begin
    if a=nil then
    begin
        new(a); a^.dato:= p; a^.hi:=nil; a^.hd:=nil;
    end
    else if p.cod< a^.dato.cod then
        cargararbol(p,a^.hi)
    else cargararbol(p,a^.hd);
end;
procedure inicializarvector(var v:vector);
var i:integer;
begin
    for i:=1 to 10 do
        v[i]:=nil;
end;
var p:producto;
begin
    inicializarvector(v);
    leerproducto(p);
    while p.cod<>-1 do
    begin
        cargararbol(p,v[p.rubro]);
        leerproducto(p);
    end;
end;

procedure imprimirvector(v:vector);
procedure imprimirarbol(a:arbol);
begin
    if a<>nil then begin
        imprimirarbol(a^.hi);
        write('Cod: ', a^.dato.cod); write('| Precio: ',a^.dato.precio:0:2); writeln('| Stock: ',a^.dato.stock);
        imprimirarbol(a^.hd);
    end;
end;
var i:integer;
begin
    for i:=1 to 10 do begin
        writeln('--------RUBRO ',I,'--------');
        imprimirarbol(v[i]);
    end;
end;
//Implementar un módulo que reciba la estructura generada en a), un rubro y un código de producto y retorne si dicho código existe o no para ese rubro.
procedure buscarcodigoenrubro(v:vector);
function buscarcod(a:arbol;codigo:integer):boolean;
begin
    if a=nil then   
        buscarcod:=false
    else if a^.dato.cod= codigo then    
        buscarcod:=true
    else if codigo < a^.dato.cod then
        buscarcod:= buscarcod(a^.hi,codigo)
    else 
        buscarcod:= buscarcod(a^.hd,codigo);
end;
var rubro,codigo:integer;
begin
    writeln('---------------------------');
    write('Rubro a consultar:'); readln(rubro);
    write('Codigo buscado:'); readln(codigo);
    if buscarcod(v[rubro],codigo) then writeln('El codigo buscado esta')
    else writeln('No existe un producto con ese codigo en el rubro indicado');
    writeln('---------------------------');
end;
procedure mayorcod(v:vector);
procedure maximo(a:arbol;var maxcod,stock:integer);
begin
    if a<>nil then begin
        maxcod:=a^.dato.cod;
        stock:=a^.dato.stock;
        if a^.hd<>nil then
            maximo(a^.hd,maxcod,stock);
    end;
end;

var i,maxcod,stock:integer;
begin
    for i:=1 to 10 do
    begin
        maxcod:=-1;
        maximo(v[i],maxcod,stock);
        if maxcod<>-1 then
            writeln('El mayor codigo del rubro ', i, ' es ', maxcod, ' tiene ',stock, ' unidades en stock.')
        else writeln('El rubro indicado esta vacio')
    end;
end;
var v:vector;
begin
    cargarvector(v);
    imprimirvector(v);
    //buscarcodigoenrubro(v);
    mayorcod(v);
end.