{6.- Realizar un programa que lea números y que utilice un procedimiento recursivo que 
escriba el equivalente en binario de un número decimal. El programa termina cuando el usuario ingresa el número 0 (cero). }
program ejercicio_6;

procedure pasaje(var n,i:integer;var res:integer);
begin
	if n>0 then
	begin
		if n mod 2=1 then begin
			res:=res+1*i;
			end;
		i:=i*10;
		n:=n div 2;
		pasaje(n,i,res);
	end;
	
end;
var num,res,i:integer;
begin 
	i:=1;
	write ('ingrese un decimal: ');read(num);
	pasaje(num,i,res);
	writeln(res);
end.
