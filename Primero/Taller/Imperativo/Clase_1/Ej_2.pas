{2.- El administrador de un edificio de oficinas cuenta, en papel, con la información del pago de las expensas de dichas oficinas. 
Implementar un programa modularizado que:
a. Genere un vector, sin orden, con a lo sumo las 300 oficinas que administra. 
* De cada oficina se ingresa el código de identificación, DNI del propietario y valor de la expensa. 
* La lectura finaliza cuando se ingresa el código de identificación -1, el cual no se procesa.
b. Ordene el vector, aplicando el método de inserción, por código de identificación de la oficina.
c. Ordene el vector aplicando el método de selección, por código de identificación de la oficina.}
program ejercicio_2;
const df=10;
type 
oficina=record
	cod: integer;
	DNI: integer;
	valor:integer;
end;
vector=array[1..df] of oficina;
procedure Imprimir (v: vector; dl: integer);
var
   i: integer;
begin
     write ('         -');
     for i:= 1 to dl do
         write ('-----');
     writeln;
     write ('  Codigo:| ');
     for i:= 1 to dl do begin
        if(v[i].cod <= 9)then
            write ('0');
        write(v[i].cod, ' | ');
     end;
     writeln;
     writeln;
     write ('DNI:| ');
     for i:= 1 to dl do begin
        if (v[i].dni<= 9)then
            write ('0');
        write(v[i].dni, ' | ');
     end;
     writeln;
     write ('         -');
     for i:= 1 to dl do
         write ('-----');
     writeln;
     writeln;
End;
procedure cargardatos(var v:vector; var dl:integer);
procedure leerdatos(var o:oficina);
begin
	Randomize;
	write ('Codigo de oficina: ');
	readln(o.cod);
	if o.cod<>-1 then begin
		write ('DNI: ');
		o.dni:= Random(100);
		writeln(o.dni);
		write ('Valor de la expensa: ');
		o.valor:= Random(100);
		writeln(o.valor);
		writeln('-------------------------');
	end;
end;

var o:oficina;
begin
	dl:=0;
	leerdatos(o);
	while (o.cod<>-1) and (dl<df) do
	begin
		dl:=dl+1;
		v[dl]:=o;
		leerdatos(o);
	end;
end;
procedure ordenarseleccion(var v:vector; dl:integer);
var act, min, i:integer; temp:oficina;
begin
	for act:= 1 to dl-1 do 
	begin
		min:=act;
		for i:= act+1 to dl do
			if v[i].cod<v[min].cod then min:=i;
		temp:= v[min];
		v[min]:= v[act];
		v[act]:=temp;
	end;
end;
procedure ordenarinsercion(var v:vector; dl:integer);
var i,j:integer; act:oficina;
begin
	for i:=2 to dl do 
	begin
		act:=v[i];
		j:=i-1;
		while (j>0) and (v[j].cod > act.cod) do
		begin
			v[j+1]:=v[j];
			j:=j-1;
		end;
		v[j+1]:=act;
	end;
end;
var v:vector; dl:integer;
begin
	cargardatos(v,dl);
	//ordenarseleccion(v,dl);
	ordenarinsercion(v,dl);
	imprimir(v,dl);
end.
