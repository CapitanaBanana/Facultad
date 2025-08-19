program UnMaestroUnDetalle;
var
procedure leer(var archivo: detalle; var dato: venta_prod);
begin
    if (not(EOF(archivo))) then 
        read (archivo, dato)
    else dato.cod := valoralto;
end;
begin 
    assign(mae, 'maestro'); assign(det, 'detalle');
    reset(mae); reset(det);
    read(mae, regm);
    leer(det, regd);
    while (regd.cod <> valoralto) do begin //Se procesan todos los registros del archivo detalle
        aux := regd.cod;
        total := 0;
        while (aux = regd.cod) do begin //Se totaliza la cantidad vendida de productos iguales en el archivo de detalle
            total := total + regd.cant_vendida; 
            leer(det, regd);
        end;
		while (regm.cod <> aux) do //se busca el producto del detalle en el maestro
			read (mae, regm);
 		regm.cant := regm.cant – total; //se modifica el stock del producto con la cantidad total vendida de ese producto
 		seek(mae, filepos(mae)-1); //se reubica el puntero en el maestro
        write(mae, regm); // se actualiza el maestro
	end;
	close(det);
	close(mae);
end.



procedure leer (var archivo:detalle; var dato:v_prod);
begin
    if (not eof(archivo)) then 
        read (archivo,dato)
    else dato.cod := valoralto;
end;
begin
    assign (mae1, 'maestro'); assign (det1, 'detalle');
    reset (mae1); reset (det1);
    leer(det1,regd); //se procesan todos los registros del archivo det1
    while (regd.cod <> valoralto) do begin
        read(mae1, regm);
        while (regm.cod <> regd.cod) do
            read (mae1,regm);
        while (regm.cod = regd.cod) do begin //se procesan códigos iguales 
            regm.cant := regm.cant - regd.cv;
            leer(det1,regd);
        end;
    seek (mae1, filepos(mae1)-1); //reubica el puntero
    write(mae1,regm);
    end;
End;