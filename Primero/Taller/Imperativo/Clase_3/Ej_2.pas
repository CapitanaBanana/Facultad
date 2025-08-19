{2.	Escribir un programa que:
a. Implemente un módulo que lea información de ventas de un comercio. De cada venta se lee código de producto, fecha y cantidad de
*  unidades vendidas. La lectura finaliza con el código de producto 0. Un producto puede estar en más de una venta. Se pide:
i. Generar y retornar un árbol binario de búsqueda de ventas ordenado por código de producto.
ii. Generar y retornar otro árbol binario de búsqueda de productos vendidos ordenado por código de producto. Cada nodo del árbol
*  debe contener el código de producto y la cantidad total vendida.
               Nota: El módulo debe retornar los dos árboles.
b. Implemente un módulo que reciba el árbol generado en i. y un código de producto y retorne la cantidad total de unidades vendidas de ese producto.
c. Implemente un módulo que reciba el árbol generado en ii. y un código de producto y retorne la cantidad total de unidades vendidas de ese producto.
}
program ejercicio_1;
type 
fec=record
	dia:integer;
	mes:integer;
	anio:integer;
end;
producto=record
	cod:integer;
	fecha:fec;
	cant:integer;
end;
arbolprod=^nodo;
nodo=record
	prod:producto;
	cant:integer;
	hi:arbolprod;
	hd:arbolprod;
end;
lista=^nodo2;
nodo2=record
	prod:producto;
	sig:lista;
end;
arbolventas=^nodo3;
nodo3=record
	venta:lista;
	cod:integer;
	hi:arbolventas;
	hd:arbolventas;
end;


procedure leerproducto(var ap:arbolprod;var av:arbolventas);
procedure leer(var p:producto);
begin
	randomize;
	write('Cod: '); readln(p.cod);
	if (p.cod<>-1) and (p.cod<>0) then
	begin
		write('fecha de venta: '); p.fecha.dia:=random(31)+1; p.fecha.mes:=random(12)+1;p.fecha.anio:=(4)+2018;
		writeln(p.fecha.dia,'/',p.fecha.mes,'/',p.fecha.anio);
		write('Cantidad vendida: '); p.cant:= random(20)+1;
		writeln(p.cant);
	end;
end;
procedure agregaraventa(p:producto;var pri:lista);
var nuevo,act,ant:lista;
begin
	new(nuevo);nuevo^.sig:=nil;nuevo^.prod:=p;
	act:=pri;
	while (act<>nil) and (act^.prod.cod < nuevo^.prod.cod) do
			begin
				ant:=act;
				act:=act^.sig;
			end;
			if act = pri then
			begin
				nuevo^.sig:=pri;
				pri:=nuevo;
			end
			else
			begin
				ant^.sig:=nuevo;
				nuevo^.sig:=act
			
		end;
end;

Procedure crearventas (var a:arbolventas; v:lista;cod:integer);
Begin
  if (a = nil) then
   begin
      new(a);
      A^.venta:= v; a^.cod:=cod; A^.HI:= nil; A^.HD:= nil;
   end
   else
    if (cod < A^.cod) then crearventas(a^.hi,v,cod)
    else crearventas(a^.hd,v,cod)   
End;
Function Buscar ( a:arbolprod; p:producto): arbolprod; 
 begin
  if (a=nil) then 
      Buscar:=nil
  else if (p.cod = a^.prod.cod) then
		Buscar:=a
       else 
        if (p.cod < a^.prod.cod) then 
          Buscar:= Buscar(a^.hi ,p)
        else  
          Buscar:=Buscar(a^.hd ,p)
end;

Procedure crearproductos (var a:arbolprod;p:producto);
Begin
  if (a = nil) then
   begin
      new(a);
      a^.prod:= p; a^.cant:=a^.cant+p.cant; a^.hi:= nil; a^.hd:= nil;
   end
   else
    if (p.cod < a^.prod.cod) then crearproductos(a^.hi,p)
    else crearproductos(A^.HD,p);
End;
var p:producto;venta:lista; cod:integer;
begin
	cod:=0;
	p.cod:=-5;
	while p.cod<>-1 do
	begin
		leer(p);
		venta:=nil;
		cod:=cod+1;
		while (p.cod<>0) and (p.cod<>-1) do
		begin	
			agregaraventa(p,venta);
			if buscar(ap,p) <> nil then 
				crearproductos(ap,p);
			{else buscar(ap,p)^.prod.cant:= buscar(ap,p)^.prod.cant+p.cant;}
			leer(p);
		end;
		crearventas(av,venta,cod);
		writeln('------------------------');
	end;
end;
Procedure imprimirap ( a : arbolprod );
begin
   if ( a<> nil ) then begin
	
    imprimirap (a^.HI);
    writeln ('Cod: ',a^.prod.cod, '| Unidades: ', a^.cant);
    imprimirap (a^.HD);
   end;
end;
Procedure imprimirav ( a : arbolventas);
begin
   if ( a<> nil ) then begin
    imprimirav (a^.HI);
    writeln ('venta: ',a^.cod, ':');
    while a^.venta<>nil do begin
		writeln('producto: ', a^.venta^.prod.cod,'| Unidades: ', a^.venta^.prod.cant);
		a^.venta:=a^.venta^.sig;
    end;
    imprimirav (a^.HD);
   end;
end;

var ap:arbolprod;av:arbolventas;cod:integer;
begin
	leerproducto(ap,av);
	imprimirap(ap);
	imprimirav(av);
	write('producto a buscar: '); readln(cod);
end.
