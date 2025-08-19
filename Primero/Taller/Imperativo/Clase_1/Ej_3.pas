{3.- Netflix ha publicado la lista de películas que estarán disponibles durante el mes de diciembre de 2022. De cada película se conoce: código de película, código de género 
* (1: acción, 2: aventura, 3: drama, 4: suspenso, 5: comedia, 6: bélica, 7: documental y 8: terror) y puntaje promedio otorgado por las críticas. 
Implementar un programa modularizado que:
a. Lea los datos de películas y los almacene por orden de llegada y agrupados por código de género, en una 
* estructura de datos adecuada. La lectura finaliza cuando se lee el código de la película -1. 
b. Una vez almacenada la información, genere un vector que guarde, para cada género, el código de película con mayor puntaje obtenido entre todas las críticas.
c. Ordene los elementos del vector generado en b) por puntaje utilizando alguno de los dos métodos vistos en la teoría. 
d. Luego de ordenar el vector, muestre el código de película con mayor puntaje y el código de película con menor puntaje.
}
program ejercicio_3;
type
genero=1..8;
pelicula=record
	cod:integer;
	gen:genero;
	punt:real;
end;
lista=^nodo;
nodo=record
	dato:pelicula;
	sig:lista;
end;
generos= record
	pri:lista;
	dl:integer;
end;
gen=record
	cod:integer;
	max:real;
end;
maximo=array[1..8] of gen;
vector=array [1..8] of generos;
procedure agregarelementos(var v:vector);
	procedure leer(var p:pelicula);
	begin
		randomize;
		write('codigo de pelicula: ');
		readln(p.cod);
		if p.cod<>-1 then
		begin
			write('Genero: ');
			p.gen:=random(8)+1;
			writeln(p.gen);
			write('Puntaje: ');
			p.punt:=random(10)+1;
			writeln(p.punt:0:2);
			writeln('~~~~~~~~~~~~~~~~~~~')
		end;
	end;
	procedure cargarnodo(var v:vector; p:pelicula);
	var nuevo:lista;
	begin
		v[p.gen].dl:=v[p.gen].dl+1;
		new(nuevo); nuevo^.sig:=nil; nuevo^.dato:=p; 
		if v[p.gen].pri=nil then
			v[p.gen].pri:=nuevo
		else begin
			nuevo^.sig:=v[p.gen].pri;
			v[p.gen].pri:=nuevo;
		end;
	end;
		
var p:pelicula; i:integer;
begin
	for i:=1 to 8 do begin
			v[i].pri:=nil;
			v[i].dl:=0;
		end;
	leer(p);
	while p.cod<>-1 do
	begin
		cargarnodo(v,p);
		leer(p);
	end;
end;

procedure generarvector(var g:maximo; v:vector);
var i:integer;
begin
	for i:=1 to 8 do
	begin
		g[i].cod:=i;
		g[i].max:=-1;
	end;
	for i:=1 to 8 do
	begin
		while v[i].pri<>nil do
		begin
			if v[i].pri^.dato.punt>g[i].max then
				g[i].max:= v[i].pri^.dato.punt;
			v[i].pri:=v[i].pri^.sig;
		end;
	end;
end;
procedure imprimirvector(v:maximo);
var i:integer;
begin
	for i:=1 to 8 do
		writeln('Genero: ', v[i].cod, '/ ', v[i].max:0:2);
	writeln('------------------------------------------------------------------------');
end;
procedure ordenarvector(var v:maximo);
var i,j,p:integer; aux:gen;
begin
	for i:=1 to 8-1 do
	begin
		p:=i;
		for j:=i+1 to 8 do
			if v[j].max < v[p].max then 
				p:=j;
		aux:=v[p];
		v[p]:=v[i];
		v[i]:=aux;
	end;
end;
procedure maxymin (v:maximo);
begin
	writeln('Mayor puntaje: ', v[1].cod, '(', v[1].max:0:2, ')');
	writeln('Menor puntaje: ', v[8].cod, '(', v[8].max:0:2, ')');
end;

var v:vector; g:maximo;
begin
	agregarelementos(v);
	generarvector(g,v);
	imprimirvector(g);
	ordenarvector(g);
	imprimirvector(g);
	maxymin(g);
end.
