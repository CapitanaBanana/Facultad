{3.- Un supermercado requiere el procesamiento de sus productos. De cada producto se conoce código, rubro (1..10), stock y precio unitario. Se pide:
a)	Generar una estructura adecuada que permita agrupar los productos por rubro. A su vez, para cada rubro, 
* se requiere que la búsqueda de un producto por código sea lo más eficiente posible. La lectura finaliza con el código de producto igual a -1..
b)	Implementar un módulo que reciba la estructura generada en a), un rubro y un código de producto y retorne si dicho código existe o no para ese rubro.
c)	Implementar un módulo que reciba la estructura generada en a), y retorne, para cada rubro, el código y stock del producto con mayor código.
d)	Implementar un módulo que reciba la estructura generada en a), dos códigos y retorne, para cada rubro, la cantidad de productos con códigos entre los dos valores ingresados.
}
program ejer3prac5;
type
rango=1..10;
productos=record
	cod:integer;
	rubro:rango;
	stock:integer;
	precio:real;
end;
arbol=^nodo;
nodo=record
	dato:productos;
	HI:arbol;
	HD:arbol;
end;
vector=array[rango] of arbol;
procedure generar (var v:vector);
	procedure leerdatos(var p:productos);
	begin
		writeln('~~~~~~~~~~~~~~~~~~~~~~~~~~~');
		write('Ingresar cod de productor: ');
			readln(p.cod);
			if (p.cod<>-1) then begin
		p.rubro:=random(9)+1;
			writeln('El rubro es: ',p.rubro);
		p.stock:=random(5)+5;
			writeln('El stock es: ',p.stock);
		p.precio:=random(50)+150;
			writeln('El precio es: ',p.precio:0:2);
		end;
	end;
	procedure prepararvector (var v:vector); 
	var i:integer;
	begin 
		for i:= 1 to 10 do 
			v[i]:=nil;
		end;
	procedure creararbol (var a:arbol;p:productos);//v[p.rubro] va a ser la pos(1 al 10)
	begin
			if a=nil then begin
			new(a);a^.dato:=p;a^.hi:=nil;a^.hd:=nil;
			end
			else
				if(p.cod<a^.dato.cod) then 
					creararbol(a^.hi,p)
				else
					creararbol(a^.hd,p);
	end;	
	var p:productos;
	begin
		leerdatos(p);
		while (p.cod<>-1) do begin
			creararbol(v[p.rubro],p);
			leerdatos(p);
		end;
	end;
procedure llamarbuscar (v:vector);
	procedure buscar (a:arbol;cod:integer;var ok:boolean);//un rubro y un código de producto y retorne si dicho código existe o no para ese rubro.
		begin
			ok:=false;
			if a<>nil then
				if (a^.dato.cod>cod) then 
					buscar(a^.hi,cod,ok)
				else 
					if(a^.dato.cod=cod) then
						ok:=true
					else
					buscar (a^.hd,cod,ok);
		end;
var ok:boolean;rubro,cod:integer;
begin
	writeln('--------------------------------');
	writeln('Ingresar rubro en el cual buscar: ');
		readln(rubro);
	writeln('~~~~~~~~~~~~~~~~~~~~~~~~~~~');
	writeln('Ingresar codigo a buscar: ');
		readln(cod);
	writeln('~~~~~~~~~~~~~~~~~~~~~~~~~~~');
	buscar(v[rubro],cod,ok);
	if (ok) then
		writeln('El codigo: ',cod,' existe en el rubro: ',rubro)
	else
		writeln('El codigo buscado no existe en el rubro: ',rubro);
end;
procedure retorne(v:vector);//retorne, para cada rubro, el código y stock del producto con mayor código.
	procedure buscar(a:arbol;var max,stock:integer);
		begin 
			if (a<>nil) then 
				if(a^.dato.cod>max) then begin
					max:=a^.dato.cod;
					stock:=a^.dato.stock;
					buscar(a^.hd,max,stock);
				end;
			end;
		var i,max,stock:integer;
	begin 
		for i:= 1 to 10 do begin
			stock:=-1;
			max:=-1;
			buscar(v[i],max,stock);
			writeln('--------------------------------');
				if (stock<>-1) then
					writeln('El codigo maximo del rubro: ',i,'es: ',max,' y su stock es de: ',stock)
				else
					writeln('El rubro: ',i,'No tiene elementos');
		end;
	end;
	procedure llamado (v:vector);//dos códigos y retorne, para cada rubro, la cantidad de productos con códigos entre los dos valores ingresados.
		procedure buscarentre (a:arbol;desde,hasta:integer; var cant:integer);
		begin
			if a<>nil then begin 
			buscarentre(a^.hi,desde,hasta,cant);
				if (desde<a^.dato.cod) and (hasta>a^.dato.cod) then begin
					cant:=cant+1;
				buscarentre(a^.hd,desde,hasta,cant);
				end;
			end;
		end;
	var desde,hasta,cant,i:integer;
	begin
		writeln('~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~');
		writeln('Ingrese cod desde el cual se quiere buscar: ');
			readln(desde);
		writeln('Ingrese cod hasta el cual se va a buscar: ');
			readln(hasta);
		for i:=1 to 10 do begin
			cant:=0;
			buscarentre(v[i],desde,hasta,cant);
			if(cant<>0) then
			writeln('La cantidad de productos en el rubro: ',i,' con codigo entre: ',desde,' y ',hasta,'  son: ',cant)
			else
			writeln('El vector esta vacio');
			end;
	end;
		
	var v:vector;
	begin
	generar(v);
	llamarbuscar(v);
	retorne(v);
	llamado(v);
	end.
