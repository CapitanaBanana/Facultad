{2.- Realizar un programa que lea números hasta leer el valor 0 e imprima, para cada número leído,
sus dígitos en el orden en que aparecen en el número. Debe implementarse
 un módulo recursivo que reciba el número e imprima lo pedido. Ejemplo si se lee el valor 256, se debe imprimir 2  5  6}
 
 program ejercicio_2;
 type
 lista=^nodo;
nodo=record
	sig:lista;
	num:integer;
end;

procedure leernumeros(var pri:lista);
procedure cargarlista(var n:integer);
var nuevo:lista;
begin
	new(nuevo); nuevo^.sig:=nil; nuevo^.num:=n;
	if pri=nil then
		pri:=nuevo
	else
		nuevo^.sig:=pri;
		pri:=nuevo;
end;
var n:integer;
begin
	write('num: '); 
	readln(n);
	if n<>0 then
	begin
		cargarlista(n);
		leernumeros(pri);
	end;
end;
procedure cifras(num:integer);
begin
	if num <>0 then begin
		write (num mod 10,', ');
		cifras(num div 10);
	end
	else writeln('');
end;

procedure imprimircifras(pri:lista);
begin
	if pri<>nil then begin
		cifras(pri^.num);
		pri:= pri^.sig;
		imprimircifras(pri);
	end;
end;
var pri:lista;
begin
	leernumeros(pri);
	imprimircifras(pri);
end.
