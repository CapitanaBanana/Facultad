program Ej_3;
const dimF=30;
fin=9999;
type
    cadena=String[20];
    producto=record
        cod:integer;
        nom:cadena;
        desc:cadena;
        stock:integer;
        minimo:integer;
        precio:real;
    end;
    venta= record
        cod:integer;
        cant:integer;
    end;
    maestro= file of producto;
    detalle= file of venta;

    reg_archivos= array [1..dimF] of file of venta;//array de archivos
    reg_ventas= array [1..dimF] of venta;//array de ventas

//proceso para leer los archivos, pone 9999 en el vector si es eof
procedure leer(var archivo:detalle; var v:venta);
begin
    if (not(eof(archivo))) then
        read(archivo,v)
    else
        v.cod:=fin;
end;

procedure abrirArchivos(var reg_arch:reg_archivos; var reg_ven:reg_ventas;var ma:maestro);
var i:integer;s:String;
begin
    for i:=0 to dimF do
    begin
        Str(i,s);
        assign(reg_arch[i],'det'+s);//abro cada archivo, les asigno un nombre correspondiente a su pos (OJO no se puede)
        reset(reg_arch[i]);
        leer(reg_arch[i],reg_ven[i]);//seteo por primera vez el vector
    end;
    assign (ma,'maestro');//abrir maestro
    reset(ma);
end;

procedure minimo(var reg_arch: reg_archivos;var min:venta; var reg_ven: reg_ventas);
var i:integer;min_pos:integer;
begin
    for i:=0 to dimF do
    begin
        if (reg_ven[i].cod< min.cod) then
        begin
            min:=reg_ven[i];
            min_pos:=i;
        end;
    end;
    leer(reg_arch[min_pos],reg_ven[min_pos]);
end;
//hago un archivo con los que tienen menos ventas que el stock minimo
procedure exportar();
var texto:text; p:producto;ma:maestro;
begin
    assign(texto, 'productos.txt');
    rewrite(texto);
    seek(ma, 0);
    while (not(eof(ma))) do
    begin
        read(ma,p);
        with p do
        begin
            if (stock<minimo) then
            begin
                writeln(texto, stock,' ', nom);
                writeln(texto, precio:0:2,' ', desc);
            end;
        end;
    end;
    close(texto);
    close(ma);
end;

var reg_ven:reg_ventas; reg_arch: reg_archivos; ma:maestro; min:venta; m_reg:producto;i:integer;
begin 
    min.cod:=-1;
    abrirArchivos(reg_arch,reg_ven,ma);
    minimo(reg_arch,min,reg_ven);
    while (min.cod<>fin) do
    begin
        read(ma,m_reg);
        while(m_reg.cod<>min.cod)do//busco en el maestro el que coincida con el detalle
            read(ma,m_reg);
        while (m_reg.cod=min.cod) do//mientras sean iguales, avanzo
        begin
            m_reg.stock:=m_reg.stock-min.cant;//actualizo stock
            minimo(reg_arch,min,reg_ven);//me fijo cual es el minimo actual
        end;
        seek(ma,filepos(ma)-1);//me posiciono
        write(ma,m_reg);
    end;
    exportar(); 
    for i:=0 to dimF do
        close(reg_arch[i]);
end.