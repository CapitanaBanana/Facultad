{5.- Implementar un módulo que realice una búsqueda dicotómica en un vector, utilizando el siguiente encabezado:
   	Procedure busquedaDicotomica (v: vector; ini,fin: indice; dato:integer; var pos: indice);
Nota: El parámetro “pos” debe retornar la posición del dato o -1 si el dato no se encuentra en el vector.
}
program ejercicio_5;
const df=20;
type 
vector=array[1..df] of integer;

procedure cargarvector(var v:vector);
var i:integer;

begin
	randomize;
	for i:=1 to df do
		v[i]:=random(100)+1;
end;
procedure imprimirvector(v:vector);
var i:integer;
begin
	for i:=1 to df do
		write('|| ', v[i],' ');
	write('||');
	writeln('');
end;

Procedure busquedaDicotomica (v: vector; ini,fin: integer; dato:integer; var pos: integer);
 Begin
     pos := (ini + fin ) div 2;
     While  ( ini <= fin ) and ( dato <> v[pos]) do 
       begin
			writeln('v en pos ',pos, ' es ', v[pos]);
         if ( dato < v[pos] ) then 
           fin:= pos -1
         else ini:= pos+1;
         pos := ( ini + fin ) div 2;
       end;
     if not((ini <=fin) and (dato = v[pos])) then pos:=-1;
  end;
procedure ordenarseleccion(var v:vector);
var act, min, i:integer; temp:integer;
begin
	for act:= 1 to df-1 do 
	begin
		min:=act;
		for i:= act+1 to df do
			if v[i]<v[min] then min:=i;
		temp:= v[min];
		v[min]:= v[act];
		v[act]:=temp;
	end;
end;
var v:vector;ini,fin,dato,pos:integer;
begin
	ini:=1;
	fin:=df;
	cargarvector(v);
	imprimirvector(v);
	ordenarseleccion(v);
	imprimirvector(v);
	write('numero a buscar: '); readln(dato);
	busquedaDicotomica (v, ini,fin,dato, pos);
	writeln('pos: ',pos);
end.
