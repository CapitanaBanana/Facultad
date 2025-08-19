{El administrador de un edificio de oficinas, cuenta en papel, con la información del pago de las expensas de dichas oficinas. 
Implementar un programa que:
a)	Genere un vector, sin orden, con a lo sumo las 300 oficinas que administra. De cada oficina se ingresa código de identificación, DNI del propietario y
*  valor de la expensa. La lectura finaliza cuando llega el código de identificación -1.
b)	Ordene el vector, aplicando uno de los métodos vistos en la cursada, para obtener el vector ordenado por código de identificación de la oficina.
c)	Realice una búsqueda dicotómica que recibe el vector generado en b) y un código de identificación de oficina y retorne si dicho código está en el vector. 
* En el caso de encontrarlo, se debe informar el DNI del propietario y en caso contrario informar que el código buscado no existe.
d)	Tenga un módulo recursivo que retorne el monto total de las expensas.
}
program ejercicio_1;
const df=300;
type
oficina=record
	cod:integer;
	dni:integer;
	valor:real;
end;

vector=array [1..df] of oficina;
procedure cargarvector(var dl:integer;var v:vector);
procedure leeroficina(var o:oficina);
begin
	randomize;
	write('Codigo: '); readln(o.cod);
	if o.cod<> -1 then begin
		write('DNI: '); o.dni:=random(9999)+1000; writeln(o.dni);
		write('Expensas: '); o.valor:=(random(9999)+1)/random(200)+1; writeln(o.valor:0:2);
		writeln;
	end;
end;

var o:oficina;
begin
	dl:=0;
	leeroficina(o);
	while (o.cod<>-1) and (dl<df) do
	begin
		dl:=dl+1;
		v[dl]:=o;
		leeroficina(o);
	end;
end;
procedure ordenarvector(var v:vector; dl:integer);
var i,j,p:integer; o:oficina;
begin
	for i:=1 to dl-1 do
	begin
			p:=i;
		for j:=i+1 to dl do
			if v[j].cod<v[p].cod then
				p:=j;
	o:=v[i];
	v[i]:=v[p];
	v[p]:=o;
	end;
end;
procedure imprimirvector(v:vector;dl:integer);
var i:integer;
begin
	for i:=1 to dl do
	begin
		writeln(i, ' | Codigo: ', v[i].cod);
	end;
end;
function busqueda(v:vector; dl:integer; cod:integer):boolean;
var medio, ult,pri:integer; esta:boolean;
begin
	esta:=false;
	pri:=1; ult:=dl; medio:=(pri+ult) div 2;
	while (pri<=ult) and (cod<>v[medio].cod) do
	begin
		if cod<v[medio].cod then
			ult:= medio-1
		else
			pri:= medio+1;
		medio:= (pri+ult) div 2
	end;
	if (pri<=ult) and (v[medio].cod=cod) then esta:=true;
	busqueda:=esta;
end;
procedure sumarexpensas(v:vector;dl:integer; var i:integer; var suma:real);
begin
	if i<dl then begin
		i:=i+1;
		suma:=suma+v[i].valor;
		sumarexpensas(v,dl,i,suma);
	end;
end;

var v:vector;dl,cod,i:integer;suma:real;
begin

	cargarvector(dl,v);
	imprimirvector(v,dl);
	writeln('--------------------');
	ordenarvector(v,dl);
	imprimirvector(v,dl);
	write('codigo a buscar: '); readln(cod);
	if busqueda(v,dl,cod) then writeln('esta')
	else writeln('nota');
	suma:=0;
	i:=0;
	sumarexpensas(v,dl,i,suma);
	writeln('expensas totales: ',suma:0:2);
end.
