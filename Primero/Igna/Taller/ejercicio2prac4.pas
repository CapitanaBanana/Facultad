{2.	Un cine posee la lista de películas que proyectará durante el mes de octubre. De cada película se conoce: código de película, código de genero 
 (1: acción, 2: aventura, 3: drama, 4: suspenso, 5: comedia, 6: bélica, 7: documental y 8: terror) y puntaje promedio otorgado por las críticas. 
 Implementar un programa que contenga:
a.	Un módulo que lea los datos de películas y los almacene ordenados por código de película y agrupados por código de genero, 
 en una estructura de datos adecuada. La lectura finaliza cuando se lee el código de película -1. 
b.	Un módulo que reciba la estructura generada en el punto a y retorne una estructura de datos donde estan todas las películas 
 almacenadas ordenadas por código de película.}
program	ejercicio2prac4;
type
rango=1..8;
lista=^nodo;
peliculas=record
	cod:integer;
	codg:rango;
	pp:integer;
end;
nodo=record
	dato:peliculas;
	sig:lista;
end;

vector=array[1..8] of lista;
procedure generarlista (var v:vector);
procedure leerdatos (var p:peliculas);
begin
	Randomize;
		writeln();
		write('Codigo de pelicula: ');
			readln(p.cod);
		p.codg:=random(7)+1;
			writeln('El codigo de genero es: ',p.codg);
		p.pp:=random(9)+1;
			writeln('El puntaje promedio es: ',p.pp);
end;
procedure crearlista (var v:vector;p:peliculas);
var nuevo,act,ant:lista;
begin
	new(nuevo);nuevo^.sig:=nil;nuevo^.dato:=p;
	act:=v[p.codg];
	while(act<>nil) and (act^.dato.cod<nuevo^.dato.cod) do begin
		ant:=act;
		act:=act^.sig;
	end;
	if act=v[p.codg] then begin
		nuevo^.sig:=v[p.codg];
		v[p.codg]:=nuevo;
	end
	else begin
		ant^.sig:=nuevo;
		nuevo^.sig:=act
	end;
end;
var p:peliculas;i:integer;
begin
	for i:=1 to 8 do 
		v[i]:=nil;
	leerdatos(p);
	while (p.cod<>-1) do begin
	crearlista(v,p);
	leerdatos(p);
	end;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:=1 to 8 do
	begin
		writeln('~~~~~~~~Codigo de Genero ', i, '~~~~~~~~');
		while v[i]<>nil do 
		begin
			writeln('- ', v[i]^.dato.cod);
			v[i]:=v[i]^.sig;
		end;
	end;
end;
procedure merge(v:vector;var l:lista);
procedure agregaratras(p:peliculas;var ult:lista);
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
	procedure minimo (var v:vector; var min:peliculas); 
	var imin,i:integer;
	begin
	min.cod:=9999;
		for i:= 1 to 8 do begin 
		if (v[i]<> nil) then
			if(v[i]^.dato.cod<min.cod) then begin
				imin:=i;
				min:=v[i]^.dato;
			end;
		end;
		if (min.cod<>9999) then begin
			v[imin]:=v[imin]^.sig;
			end;
	end;	

	var min:peliculas;ult:lista;                                 
	begin
		l:=nil;
		ult:=nil;
		minimo(v,min);
	while (min.cod<>9999) do begin
		agregarAtras(min,ult);
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
	generarlista(v);
	imprimirvector(v);
	merge(v,l);
	imprimirlista(l);
end.
