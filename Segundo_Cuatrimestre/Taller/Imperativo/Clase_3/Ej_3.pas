{3.	Implementar un programa que contenga:
a. Un módulo que lea información de alumnos de Taller de Programación y almacene en una estructura de datos sólo a aquellos
*  alumnos que posean año de ingreso posterior al 2010. De cada alumno se lee legajo, DNI y año de ingreso. La estructura generada debe
*  ser eficiente para la búsqueda por número de legajo. 
b. Un módulo que reciba la estructura generada en a. e informe el DNI y año de ingreso de aquellos alumnos cuyo legajo sea inferior a un valor ingresado como parámetro. 
c. Un módulo que reciba la estructura generada en a. e informe el DNI y año de ingreso de aquellos alumnos cuyo legajo esté comprendido entre dos valores que se reciben como parámetro. 
d. Un módulo que reciba la estructura generada en a. y retorne el DNI más grande.
e. Un módulo que reciba la estructura generada en a. y retorne la cantidad de alumnos con legajo impar.
}
program ejercicio_3;
type
alumno=record
	dni:integer;
	ing:integer;
	leg:integer;
end;
arbol=^nodo;
nodo=record
	hi:arbol;
	hd:arbol;
	dato: alumno;
end;

procedure cargarinformacion(var ar:arbol);
procedure leerinfo(var a:alumno);
begin
	randomize;
	write('Legajo: ');readln(a.leg);
	if a.leg<>0 then begin
		write('Anio de ingreso: '); a.ing:=random(15)+2005; writeln(a.ing);
		write('DNI: '); a.dni:=random(9999)+999; writeln(a.dni);
	end;
end;
procedure cargararbol(var ar:arbol;a:alumno);
begin
	if ar=nil then begin
		new (ar);
		ar^.dato:=a; ar^.hd:=nil; ar^.hi:=nil;
	end
	else 
		if a.leg > ar^.dato.leg then
			cargararbol(ar^.hd,a)
		else cargararbol(ar^.hi,a);
end;
var a:alumno;
begin
	leerinfo(a);
	while a.leg<>0 do
	begin
		if a.ing>=2010 then
			cargararbol(ar,a);
		leerinfo(a);
	end;
end;
procedure imprimir(a:arbol);
begin
	if a<>nil then
	begin
		imprimir(a^.hi);
		begin
			write('Legajo: ', a^.dato.leg,' | ');
			write('dni: ',a^.dato.dni,  ' | '); 
			writeln('Ingreso: ',a^.dato.ing); 
		end;
		imprimir(a^.hd);
	end;
end;

procedure imprimirlegajo(a:arbol;desde,hasta:integer);
begin
	if a<>nil then
	begin
		imprimirlegajo(a^.hi,desde,hasta);
		if (a^.dato.leg>=desde) and (a^.dato.leg<=hasta) then
		begin
			write('Legajo: ',a^.dato.leg, ' | '); 
			write('Anio: ', a^.dato.ing,' | ');
			writeln('dni: ',a^.dato.dni); 
		end;
		imprimirlegajo(a^.hd,desde,hasta);
	end;
end;
procedure imprimirhasta(a:arbol;hasta:integer);
begin
	if a<>nil then
	begin
		imprimirhasta(a^.hi,hasta);
		if (a^.dato.leg<=hasta) then
		begin
			write('Legajo: ', a^.dato.leg,' | ');
			write('Anio: ', a^.dato.ing,' | ');
			writeln('dni: ',a^.dato.dni); 
		end;
		imprimirhasta(a^.hd,hasta);
	end;
end;
procedure masgrande(a:arbol; var max:integer);
begin
	if a<>nil then 
	begin
		masgrande(a^.hi,max);
		if a^.dato.dni>max then
			max:=a^.dato.dni;
		masgrande(a^.hd,max);
	end;
end;
procedure dniimpar(a:arbol;var impar:integer);
begin
	if a<>nil then 
	begin
		dniimpar(a^.hi, impar);
		if a^.dato.dni mod 2=1 then 
			impar:=impar+1;
		dniimpar(a^.hd, impar);
	end;
end;
var ar:arbol; desde, max,hasta, impar:integer;
begin
	ar:=nil;
	cargarinformacion(ar);
	imprimir(ar);
	write('Imprimir legajo hasta: '); readln(hasta);
	imprimirhasta(ar,hasta);
	writeln('---------------------');
	write('Imprimir legajo a partir de: '); readln(desde);
	write('Imprimir hasta: '); readln(hasta);
	imprimirlegajo(ar,desde,hasta);
	writeln('---------------------');
	max:=-1;
	masgrande(ar, max);
	writeln('El dni mas grande es: ', max);
	impar:=0;
	dniimpar(ar,impar);
	writeln('La cantidad de alumnos con dni impar es: ', impar);
end.
