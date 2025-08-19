{2.	Un cine posee la lista de películas que proyectará durante el mes de octubre. De cada película se conoce: código de película, código de género 
* (1: acción, 2: aventura, 3: drama, 4: suspenso, 5: comedia, 6: bélica, 7: documental y 8: terror) y puntaje promedio otorgado por las críticas. Implementar 
* un programa que contenga:
a.	Un módulo que lea los datos de películas y los almacene ordenados por código de película y agrupados por código
*  de género, en una estructura de datos adecuada. La lectura finaliza cuando se lee el código de película -1. 
b.	Un módulo que reciba la estructura generada en el punto a y retorne una estructura de datos donde estén todas las películas 
* almacenadas ordenadas por código de película.
}
program ejercicio_1;
type rango=1..8;
pelicula=record
	cod:integer;
	gen:rango;
	pun:integer;
end;

lista=^nodo;
nodo=record
	sig:lista;
	dato:pelicula;
end;
vector=array [1..8] of lista;

procedure cargarinfo(var v:vector);
procedure inicializarpunteros;
var i:integer;
begin
	for i:=1 to 8 do
		v[i]:=nil;
end;
procedure cargarlistas(p:pelicula);
var nuevo,ant,act:lista;
begin
	new(nuevo);nuevo^.sig:=nil; nuevo^.dato:=p;
	act:=v[p.gen];
	while (act<>nil) and (act^.dato.cod<nuevo^.dato.cod) do 
	begin
			ant:=act;
			act:=act^.sig;
	end;
	if act=v[p.gen] then
	begin
		nuevo^.sig:=v[p.gen];
		v[p.gen]:=nuevo;
	end
	else begin
		ant^.sig:=nuevo;
		nuevo^.sig:=act;
	end;
end;
procedure leerp(var p:pelicula);
begin
	randomize;
	write('Codigo: '); read(p.cod);
	if p.cod<>-1 then begin
		write('Puntaje: '); p.pun:= random(10); writeln(p.pun);
		write('Genero: '); p.gen:= random(8)+1; writeln(p.gen);writeln;
	end;
end;
var p:pelicula;
begin
	inicializarpunteros;
	leerp(p);
	while p.cod<>-1 do
	begin
		cargarlistas(p);
		leerp(p);
	end;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:= 1 to 8 do begin
		writeln('----------GENERO ', i,'----------');
		while v[i]<>nil do 
		begin
			writeln('cod: ', v[i]^.dato.cod);
			v[i]:=v[i]^.sig;
		end;
	end;
end;
procedure merge(v:vector; var l:lista);
procedure agregaratras(p:pelicula;var ult:lista);
var nuevo:lista;
begin
	new (nuevo); nuevo^.sig:=nil; nuevo^.dato:=p;
	if l=nil then begin
		l:=nuevo;
		ult:=nuevo;
	end
	else begin
		ult^.sig:=nuevo;
		ult:=nuevo;
	end;
end;
procedure minimo(var v:vector; var min:pelicula);
var ming,i:integer;
begin
	min.cod:=9999;
	for i:=1 to 8 do
	begin
		if (v[i]<>nil) then
			if v[i]^.dato.cod<=min.cod  then begin
				ming:=i;
				min:=v[i]^.dato;
			end;
	end;
			if min.cod<>9999 then
			begin
				v[ming]:=v[ming]^.sig;
			end;
end;
var min:pelicula;ult:lista;
begin
	l:=nil;
	ult:=nil;
	minimo(v,min);
	while min.cod<>9999 do
	begin
		agregaratras(min,ult);
		minimo(v,min);
	end;
end;
procedure imprimirlista(l:lista);
begin
	writeln('lista con merge:');
	while l<>nil do begin
		writeln('cod: ', l^.dato.cod);
		l:=l^.sig;
	end;
end;
var v:vector;l:lista;
begin
	cargarinfo(v);
	imprimirvector(v);
	merge(v,l);
	imprimirlista(l);
end.
