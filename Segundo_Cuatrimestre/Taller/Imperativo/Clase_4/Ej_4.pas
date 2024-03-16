{4.	Un teatro tiene funciones los 7 días de la semana. Para cada día se tiene una lista con las entradas vendidas. Se desea procesar la información de 
* una semana. Implementar un programa que:
a.	Genere 7 listas con las entradas vendidas para cada día. De cada entrada se lee día (de 1 a 7), código de la obra, asiento y monto. La lectura 
* finaliza con el código de obra igual a 0. Las listas deben estar ordenadas por código de obra de forma ascendente. 
b.	Genere una nueva lista que totalice la cantidad de entradas vendidas por obra. Esta lista debe estar ordenada por código de obra de forma ascendente.
}
program ejercicio_4;
type
entrada= record
	dia:integer;
	cod:integer;
	asiento:integer;
	monto:integer;
end;

lista=^nodo;
nodo=record
	sig:lista;
	dato:entrada;
end;
listaob=^nodo2;
nodo2=record
	sig:listaob;
	cod:integer;
	cant:integer;
end;
vector= array [1..7] of lista;
procedure cargarvector(var v:vector);
procedure cargarentrada(e:entrada);
var nuevo,ant,act:lista;
begin
	new(nuevo);nuevo^.sig:=nil; nuevo^.dato:=e;
	act:=v[e.dia];
	while (act<>nil) and (act^.dato.cod<nuevo^.dato.cod) do 
	begin
			ant:=act;
			act:=act^.sig;
	end;
	if act=v[e.dia] then
	begin
		nuevo^.sig:=v[e.dia];
		v[e.dia]:=nuevo;
	end
	else begin
		ant^.sig:=nuevo;
		nuevo^.sig:=act;
	end;
end;
procedure leerentrada(var e:entrada);
begin
	randomize;
	write('Codigo: '); readln(e.cod);
	if e.cod<> 0 then begin
		write('Dia: '); e.dia:=random(7)+1; writeln(e.dia);
		write('Asiento: '); e.asiento:=random(100)+1; writeln(e.asiento);
		write('Monto: '); e.monto:=random(500)+1; writeln(e.monto);
		writeln;
	end;
end;
procedure inicializarpunteros;
var i:integer;
begin
	for i:=1 to 7 do
		v[i]:=nil;
end;
var e:entrada;
begin
	inicializarpunteros;
	leerentrada(e);
	while e.cod<>0 do
	begin
		cargarentrada(e);
		leerentrada(e);
	end;
end;
procedure merge(v:vector;var pri:listaob);
procedure minimo(var v:vector; var min:integer);
var mindia,i:integer;
begin
	min:=9999;
	for i:=1 to 7 do
begin
	if v[i]<>nil then
		if (v[i]^.dato.cod<=min) then 
		begin
			mindia:=i;
			min:=v[i]^.dato.cod;
		end;
end;
		if min<> 9999 then
		begin
			min:= v[mindia]^.dato.cod;
			v[mindia]:=v[mindia]^.sig;
		end;
end;
procedure agregaratras(var pri,ult:listaob; cod:integer; cant:integer);
var nuevo:listaob;
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
var ult:listaob;actual:entrada;min,canttotal:integer;
begin
	pri:=nil;
	minimo(v,min);
	while min<>9999 do 
	begin
		actual.cod:=min;
		canttotal:=0;
		while (min<>9999) and (actual.cod=min) do begin
			canttotal:=canttotal+1;
			minimo(v,min);
		end;
		agregaratras(pri,ult,actual.cod,canttotal);	
	end;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:= 1 to 7 do begin
		writeln('----------DIA ', i,'----------');
		while v[i]<>nil do 
		begin
			writeln('cod: ', v[i]^.dato.cod);
			v[i]:=v[i]^.sig;
		end;
	end;
end;
procedure imprimirlista(l:listaob);
begin
	if l<>nil then 
	begin
		write('Obra: ', l^.cod); writeln(' | ', l^.cant); 
		imprimirlista(l^.sig);
	end;
end;
var v:vector;pri: listaob;
begin
	cargarvector(v);
	imprimirvector(v);
	merge(v, pri);
	imprimirlista(pri);
end.
