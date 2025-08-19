{4.- Una librería requiere el procesamiento de la información de sus productos. De cada producto se conoce el código del producto, código de rubro (del 1 al 8) y precio. 
Implementar un programa modularizado que:
a. Lea los datos de los productos y los almacene ordenados por código de producto y agrupados por rubro, en una estructura de datos adecuada. 
El ingreso de los productos finaliza cuando se lee el precio 0.
b. Una vez almacenados, muestre los códigos de los productos pertenecientes a cada rubro.
c. Genere un vector (de a lo sumo 30 elementos) con los productos del rubro 3. Considerar que puede haber más o menos de 30 productos 
del rubro 3. Si la cantidad de productos del rubro 3 es mayor a 30, almacenar los primeros 30 que están en la lista e ignore el resto. 
d. Ordene, por precio, los elementos del vector generado en b) utilizando alguno de los dos métodos vistos en la teoría. 
e. Muestre los precios del vector ordenado.
}
program ejercicio_4;
type
producto=record
	cod:integer;
	rubro:1..8;
	precio:real;
end;
lista=^nodo;
nodo=record
	dato:producto;
	sig:lista;
end;
vector=array [1..8] of lista;
rubro3=array[1..30] of producto;

procedure agregarelementos(var v:vector);
procedure leer(var p:producto);
	begin
		randomize;
		write('codigo de producto: ');
		readln(p.cod);
		if p.cod<>0 then
		begin
			write('Rubro: ');
			p.rubro:=random(8)+1;
			writeln(p.rubro);
			write('Precio: ');
			p.precio:=random(1000)+1;
			writeln(p.precio:0:2);
			writeln('~~~~~~~~~~~~~~~~~~~')
		end;
	end;
	procedure cargarnodo(p:producto);
	var nuevo, act, ant:lista;
	begin
		new(nuevo); nuevo^.sig:=nil; nuevo^.dato:=p; 
		act:=v[p.rubro];
		while (act<>nil) and (act^.dato.cod < nuevo^.dato.cod) do
			begin
				ant:=act;
				act:=act^.sig;
			end;
			if act = v[p.rubro] then
			begin
				nuevo^.sig:=v[p.rubro];
				v[p.rubro]:=nuevo;
			end
			else
			begin
				ant^.sig:=nuevo;
				nuevo^.sig:=act
			
		end;
	end;
	
var p:producto; i:integer;
begin
	for i:=1 to 8 do 
			v[i]:=nil;
	leer(p);
	while p.cod<>0 do
	begin
		cargarnodo(p);
		leer(p);
	end;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:=1 to 8 do
	begin
		writeln('~~~~~~~~RUBRO ', i, '~~~~~~~~');
		while v[i]<>nil do 
		begin
			writeln('- ', v[i]^.dato.cod);
			v[i]:=v[i]^.sig;
		end;
	end;
end;
procedure vector3 (pri:lista; var v3:rubro3; var cant:integer);
begin
	cant:=0;
	while (cant<30) and (pri<>nil) do
	begin
		cant:=cant+1;
		v3[cant]:=pri^.dato;
		pri:=pri^.sig;
	end;
end;
procedure ordenar(var v:rubro3;dl:integer);
var i,j:integer; aux:producto;
begin
	for i:=2 to dl do
	begin
		aux:=v[i];
		j:= i-1;
		while (j>0) and (v[j].precio>aux.precio) do
		begin
			v[j+1]:=v[j];
			j:=j-1;
		end;
		v[j+1]:=aux;
	end;
end;
procedure imprimirvector3(v:rubro3;dl:integer);
var i:integer;
begin
	writeln('         PRODUCTOS RUBRO 3           ');
	for i:=1 to dl do
	begin
		writeln(i,': ', v[i].precio:0:2);
	end;
end;
var v:vector;v3:rubro3;dl:integer;
begin
	agregarelementos(v);
	imprimirvector(v);
	vector3(v[3],v3,dl);
	ordenar(v3,dl);
	imprimirvector3(v3,dl);
end.
