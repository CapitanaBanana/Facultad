program Ej_14;
const fin=9999;
finString='zzz';
type
    cadena=string[20];
    vuelo=record
        dest:cadena;
        fecha:integer;
        hora:integer;
        disponibles:integer;
    end;

    reg_detalle= record
        dest:cadena;
        fecha:integer;
        hora:integer;
        comprados:integer;
    end;

    lista=^nodo;
    nodo=record
	    destino:cadena;
        fecha:integer;
        hora:integer;
	    sig:lista;
    end;

    vuelos= file of vuelo;
    detalle= file of reg_detalle;


procedure importarDetalle1();
var texto:text; arch:detalle; r:reg_detalle;
begin
    assign(texto, 'detalle1.txt');
    reset(texto);
    assign(arch, 'detalle1');
    rewrite(arch);
    while(not(eof(texto))) do 
    begin
        with r do begin 
            readln(texto,fecha,hora,comprados,dest);
        end;
        write(arch,r);
    end;
    close(arch);
    close(texto);
end;
procedure importarDetalle2();
var texto:text; arch:detalle; r:reg_detalle;
begin
    assign(texto, 'detalle2.txt');
    reset(texto);
    assign(arch, 'detalle2');
    rewrite(arch);
    while(not(eof(texto))) do 
    begin
        with r do begin 
            readln(texto,fecha,hora,comprados,dest);
        end;
        write(arch,r);
    end;
    close(arch);
    close(texto);
end;
procedure exportarMaestro();
var texto:text;v:vuelo; ma:vuelos;
begin
    assign(texto,'vuelosUpdate.txt');
    rewrite(texto);
    assign(ma, 'vuelos');
    reset(ma);
    while (not(eof(ma))) do
    begin
        read(ma,v);
        with v do
        begin
            writeln(texto, fecha,' ', hora,' ', disponibles,dest);
        end;
    end;
    close(ma);
    close(texto);
end;
procedure importarMaestro();
var texto:text; arch:vuelos; v:vuelo;
begin
    assign(texto, 'vuelos.txt');
    reset(texto);
    assign(arch, 'vuelos');
    rewrite(arch);
    while(not(eof(texto))) do 
    begin
        with v do begin 
            readln(texto,fecha,hora,disponibles,dest);
        end;
        write(arch,v);
    end;
    close(arch);
    close(texto);
end;
procedure leer(var det:detalle;var r:reg_detalle);
begin
    if(not(eof(det))) then
    read(det,r)
    else
    begin
        r.dest:=finString;
        r.hora:=fin;
        r.fecha:=fin;
    end;
end;
procedure minimo(var det1:detalle; var det2:detalle;var r1:reg_detalle; var r2:reg_detalle; var min:reg_detalle);
begin
    if ((r1.dest<r2.dest)or((r1.dest=r2.dest)and(r1.fecha<r2.fecha)) or ((r1.dest=r2.dest)and(r1.fecha=r2.fecha) and (r1.hora<r2.hora))) then
    begin
        min:=r1;
        leer(det1,r1);
    end
    else
    begin
        min:=r2;
        leer(det2,r2);
    end;
end;
procedure cargarlista(var pri:lista; ma:vuelo);
var nuevo:lista;
begin	
	new(nuevo); nuevo^.sig:=nil; nuevo^.destino:=ma.dest; nuevo^.fecha:= ma.fecha; nuevo^.hora:=ma.hora;
	begin
		if pri=nil then
			pri:=nuevo
		else 
        begin
			nuevo^.sig:=pri;
			pri:=nuevo;
		end;
	end;
end;
var det1:detalle; det2:detalle; r1:reg_detalle; r2:reg_detalle; min:reg_detalle; ma:vuelos; v:vuelo; num:integer;pri:lista;
begin 
    importarDetalle1();
    importarDetalle2();
    importarMaestro();
    assign(det2, 'detalle2');
    reset(det2);
    assign(det1, 'detalle1');
    reset(det1);
    assign(ma, 'vuelos');
    reset(ma);
    write('Ingrese el numero que quiere checkear como minimo ');
    readln(num);
    leer(det1,r1);
    leer(det2,r2);
    minimo(det1,det2,r1,r2,min);
    read(ma,v);
    while((min.dest<>finString)and (min.hora<>fin) and (min.fecha<>fin)) do
    begin
        while((v.dest<>min.dest)or (v.fecha<>min.fecha)or (v.hora<>min.hora)) do
        begin
            read(ma,v);
        end;
        while((v.dest=min.dest) and (v.fecha=min.fecha)and (v.hora=min.hora)) do
        begin
            v.disponibles:=v.disponibles-min.comprados;
            minimo(det1,det2,r1,r2,min);
        end;
        seek(ma,filepos(ma)-1);
        write(ma,v);
        if (v.disponibles<num) then
            cargarlista(pri,v);
    end;
    close(ma);
    close(det1);
    close(det2);
    exportarMaestro();
    while (pri<>nil) do
    begin
        writeln(pri^.destino,' ', pri^.fecha,' ', pri^.hora);
        pri:=pri^.sig;
    end;
end.