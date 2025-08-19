{4.- Escribir un programa que:
a. Implemente un módulo recursivo que genere un vector de 20 números enteros “random” mayores a 0 y menores a 100. 
b. Implemente un módulo recursivo que devuelva el máximo valor del vector. 
c. Implementar un módulo recursivo que devuelva la suma de los valores contenidos en el vector.
}
program ejercicio_4;
const df=5;
type
vector=array[1..df] of integer;

procedure GenerarVector(var v:vector);
procedure cargarvector(var dl:integer);
begin
	randomize;
	if dl<=df then begin
		write('num: '); readln(v[dl]);
		dl:=dl+1;
		cargarvector(dl);
	end;
end;

var dl:integer;
begin
	dl:=1;
	cargarvector(dl);
end;
procedure maximo(v:vector; var max:integer;var i:integer);
begin
	if i<=df then
	begin
		if v[i]>max then 
			max:=v[i];	
		i:=i+1;
		maximo(v,max,i);
	end;
	i:=1;
end;
procedure suma(v:vector; var sum:integer;var i:integer);
begin
	if i<=df then
	begin
		sum:=sum+v[i];
		i:=i+1;
		suma(v,sum,i);
	end;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:=1 to df do
		write('|| ', v[i],' ');
	write('||');
	writeln('');
	i:=1;
end;
var v:vector; i, max,sum:integer;
begin
	max:=-1;
	sum:=0;
	GenerarVector(v);
	imprimirvector(v);
	maximo(v,max,i);
	writeln('maximo: ', max);
	suma(v,sum,i);
	writeln('suma: ', sum);
end.
