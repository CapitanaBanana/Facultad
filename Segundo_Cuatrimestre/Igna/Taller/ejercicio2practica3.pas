{	Escribir un programa que:
a. Implemente un módulo que lea información de ventas de un comercio. De cada venta se lee código de producto, fecha y cantidad de unidades vendidas. 
* La lectura finaliza con el código de producto 0. Un producto puede estar en más de una venta. Se pide:
i. Generar y retornar un árbol binario de búsqueda de ventas ordenado por código de producto.
ii. Generar y retornar otro árbol binario de búsqueda de productos vendidos ordenado por código de producto. Cada nodo del árbol debe contener el código de producto y la cantidad total vendida.
               Nota: El módulo debe retornar los dos árboles.
b. Implemente un módulo que reciba el árbol generado en i. y un código de producto y retorne la cantidad total de unidades vendidas de ese producto.
c. Implemente un módulo que reciba el árbol generado en ii. y un código de producto y retorne la cantidad total de unidades vendidas de ese producto.
}
program ejer2p3;
type
arbol=^nodo;
arbol2=^nodo2;
fecha=record
	dia:integer;
	mes:integer;
	anio:integer;
end;
ventas=record
	cod:integer;
	f:fecha;
	cant:integer;
end;
nodo2=record
	dato:ventas;
	hi:arbol2;
	hd:arbol2;
end;
nodo=record
	dato:ventas;
	hi:arbol;
	hd:arbol;
end;
procedure generararbol (var a:arbol;var a2:arbol2);
procedure crear (var a:arbol;v:ventas);
begin
if (a=nil) then begin
new(a);a^.dato:=v;a^.hi:=nil;a^.hd:=nil;
end
else
if(v.cod<a^.dato.cod) then
	crear(a^.hi,v)
	else
	crear(a^.hd,v);
end;
procedure crear2(var a2:arbol2;v:ventas);
begin
	if (a2=nil) then begin
	new(a2);a2^.dato:=v;a2^.hi:=nil;a2^.hd:=nil;
	end
	else
	if(v.cod=a2^.dato.cod) then 
		a2^.dato.cant:=a2^.dato.cant+v.cant
	else 
	if (v.cod<a2^.dato.cod) then
	crear2(a2^.hi,v)
	else
	crear2(a2^.hd,v);
end;
procedure leerdatos(var v:ventas);
begin
	Randomize;
	write('Codigo de producto: ');
		read(v.cod);
		if (v.cod<>0) then begin
			v.f.dia:=random(30)+1;
				writeln('Dia: ',v.f.dia);
			v.f.mes:=random(11)+1;
				writeln('Mes: ',v.f.mes);
			v.f.anio:=random(22)+2000;
				writeln('Anio: ',v.f.anio);
			v.cant:=random(5)+1;
			writeln('Cantidad: ',v.cant);
	end;
end;
var v:ventas;
begin
	leerdatos(v);
	while (v.cod<>0) do begin
		crear(a,v);
		crear2(a2,v);
		leerdatos(v);
	end;
end;
procedure imprimir (a:arbol);
begin
	if (a<>nil) then begin
	imprimir(a^.hi);
	writeln('El codigo es: ',a^.dato.cod);
	writeln('La cantidad de productos son: ',a^.dato.cant);
	imprimir(a^.hd);
	end;
end;

procedure imprimira2 (a2:arbol2);
begin
	if (a2<>nil) then begin
	imprimira2(a2^.hi);
	writeln('El codigo es: ',a2^.dato.cod);
	writeln('La cantidad de productos son: ',a2^.dato.cant);
	imprimira2(a2^.hd);
	end;
end;
procedure canttotaldexprod (a:arbol);
	procedure buscar (a:arbol;n:integer;var cant:integer);
	begin
	if (a<>nil) then begin
		if (a^.dato.cod=n) then
			cant:=(cant+a^.dato.cant);
		if (n>=a^.dato.cod) then
		buscar(a^.HD,n,cant)
		else
		buscar(a^.HI,n,cant);
	end;
end;
var n,cant:integer;
begin
cant:=0;
	writeln('Ingrese cod de prod para buscar el total de cantidades vendidas: ');
		readln(n);
		buscar(a,n,cant);
	if (cant=0) then
	writeln('El producto buscado no tienen ventas')
	else
	writeln('La cantidad de ventas totales del producto son: ',cant);
end;
procedure cantotaldexproda2(a2:arbol2);
	procedure estoo (a2:arbol2;x:integer;var nums:integer);
	begin
		if (a2=nil) then
		nums:=-1
		else
		if(a2<>nil) then begin
			if (x=a2^.dato.cod) then
				nums:=a2^.dato.cant
			else
		if (x>a2^.dato.cod) then
		estoo(a2^.hd,x,nums)
		else
		estoo(a2^.hi,x,nums)
		end;
	end;
	var x:integer;nums:integer;
	begin
	nums:=0;
	writeln('Ingrese cod de prod para buscar el total de cantidades vendidas: ');
		readln(x);
	estoo(a2,x,nums);
		if(nums=-1) then
	writeln('El producto buscado no tienen ventas')
		else
	writeln('La cantidad de ventas totales del producto son: ',nums);
	end;
var a:arbol;a2:arbol2;//pp
begin
generararbol(a,a2);
imprimir(a);
writeln('---------------------------');
imprimira2(a2);
canttotaldexprod(a);
cantotaldexproda2(a2);
end.
