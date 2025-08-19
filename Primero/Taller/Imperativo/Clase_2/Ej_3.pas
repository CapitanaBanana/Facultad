{3.- Escribir un programa que:
a. Implemente un módulo recursivo que genere una lista de números enteros “random” mayores a 0 y menores a 100. Finalizar con el número 0.
b. Implemente un módulo recursivo que devuelva el mínimo valor de la lista. 
c. Implemente un módulo recursivo que devuelva el máximo valor de la lista. 
d. Implemente un módulo recursivo que devuelva verdadero si un valor determinado se encuentra en la lista o falso en caso contrario. 
}
program ejercicio_3;
type
lista=^nodo;
nodo=record
	num:integer;
	sig:lista;
end;

procedure cargarlista(var pri:lista);
var n:integer;nuevo:lista;
begin	
	write('~ '); read(n);
	new(nuevo); nuevo^.sig:=nil; nuevo^.num:=n;
	if n<>0 then
	begin
		if pri=nil then
			pri:=nuevo
		else begin
			nuevo^.sig:=pri;
			pri:=nuevo;
		end;
		cargarlista(pri);
	end
end;
procedure maximo(pri:lista; var max:integer);
begin
	if pri<>nil then begin
		if pri^.num>max then
			max:= pri^.num;
		pri:=pri^.sig;
		maximo(pri, max);
	end;
end;
procedure minimo(pri:lista; var min:integer);
begin
	if pri<>nil then begin
		if pri^.num<min then
			min:= pri^.num;
		pri:=pri^.sig;
		minimo(pri, min);
	end;
end;
function buscar(pri:lista; n:integer):boolean;
var encontre:boolean;
begin
	encontre:=false;
	if (pri<>nil) and (encontre=false )then begin
		if pri^.num=n then 
			encontre:=true
		else begin
			pri:=pri^.sig;
			buscar (pri, n);
		end;
	end;
	buscar:=encontre;
end;
var pri:lista;max,min,n:integer;
begin
	max:=-1;
	min:=101;
	pri:=nil;
	cargarlista(pri);
	maximo(pri,max);
	writeln ('Maximo: ', max);
	minimo(pri,min);
	writeln ('Minimo: ', min);
	write('numero a buscar: '); readln(n);
	if buscar(pri,n) then writeln ('esta')
	else writeln('no ta');
end.
