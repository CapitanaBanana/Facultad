{3.	Implementar un programa que procese la información de las ventas de productos de una librería que tiene 4 sucursales. De cada venta se
*  lee fecha de venta, código del producto vendido, código de sucursal y cantidad vendida. El ingreso de las ventas finaliza cuando se lee el código de sucursal 0. 
* Implementar un programa que:
a.	Almacene las ventas ordenadas por código de producto y agrupados por sucursal, en una estructura de datos adecuada.
b.	Contenga un módulo que reciba la estructura generada en el punto a y retorne una estructura donde esté acumulada 
* la cantidad total vendida para cada código de producto.
}
program ejercicio_3;
type
fecha=record
	dia:integer;
	mes:integer;
	anio:integer;
end;
venta=record
	cod:integer;
	suc:integer;
	cant:integer;
end;
lista=^nodo;
nodo=record
	sig:lista;
	dato:venta;
end;
lista2=^nodo2;
nodo2=record
	sig:lista2;
	cod:integer;
	cant:integer;
end;
vector=array [1..4] of lista;



procedure cargarvector(var v:vector);
procedure cargarventas(ven:venta);
var nuevo,ant,act:lista;
begin
	new(nuevo);nuevo^.sig:=nil; nuevo^.dato:=ven;
	act:=v[ven.suc];
	while (act<>nil) and (act^.dato.cod<nuevo^.dato.cod) do 
	begin
			ant:=act;
			act:=act^.sig;
	end;
	if act=v[ven.suc] then
	begin
		nuevo^.sig:=v[ven.suc];
		v[ven.suc]:=nuevo;
	end
	else begin
		ant^.sig:=nuevo;
		nuevo^.sig:=act;
	end;
end;


procedure leerventa(var ven:venta);
begin
	randomize;
	write('Codigo: '); readln(ven.cod);
	if ven.cod<> 0 then begin
		write('Sucursal: '); ven.suc:=random(4)+1; writeln(ven.suc);
		write('Cantidad: '); ven.cant:=random(100)+1; writeln(ven.cant);writeln;
	end;
end;
procedure inicializarpunteros;
var i:integer;
begin
	for i:=1 to 4 do
		v[i]:=nil;
end;
var ven:venta;
begin
	inicializarpunteros;
	leerventa(ven);
	while ven.cod<>0 do
	begin
		cargarventas(ven);
		leerventa(ven);
	end;
end;
procedure crearlistacantidades(v:vector;var pri:lista2);
procedure minimo(var v:vector; var min:integer;var cant:integer);
var minsuc,i:integer;
begin
	min:=9999;
	for i:=1 to 4 do
begin
	if v[i]<>nil then
		if (v[i]^.dato.cod<=min) then 
		begin
			minsuc:=i;
			min:=v[i]^.dato.cod;
		end;
end;
		if min<> 9999 then
		begin
			min:= v[minsuc]^.dato.cod;
			cant:=v[minsuc]^.dato.cant;
			v[minsuc]:=v[minsuc]^.sig;
		end;
end;
procedure agregaratras(var pri,ult:lista2; cod:integer; cant:integer);
var nuevo:lista2;
begin
	new (nuevo); nuevo^.sig:=nil; nuevo^.cant:=cant; nuevo^.cod:=cod;
	if pri=nil then begin
		pri:=nuevo;
		ult:=nuevo;
	end
	else begin
		ult^.sig:=nuevo;
		ult:=nuevo;
	end;
end;
var ult:lista2;actual:venta;min, cant,canttotal:integer;
begin
	pri:=nil;
	minimo(v,min,cant);
	while min<>9999 do 
	begin
		actual.cod:=min;
		canttotal:=0;
		while (min<>9999) and (actual.cod=min) do begin
			canttotal:=canttotal+cant;
			minimo(v,min,cant);
		end;
		agregaratras(pri,ult,actual.cod,canttotal);	
	end;
end;
procedure imprimirlista(l:lista2);
begin
	writeln('lista con merge:');
	while l<>nil do begin
		write('cod: ', l^.cod); writeln('| cant: ', l^.cant);
		l:=l^.sig;
	end;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:= 1 to 4 do begin
		writeln('----------SUCURSAL ', i,'----------');
		while v[i]<>nil do 
		begin
			write('cod: ', v[i]^.dato.cod); writeLN('| cant: ', v[i]^.dato.cant);
			v[i]:=v[i]^.sig;
		end;
	end;
end;


var v:vector;pri:lista2;
begin
	cargarvector(v);
	imprimirvector(v);
	crearlistacantidades(v,pri);
	imprimirlista(pri);
	
end.
