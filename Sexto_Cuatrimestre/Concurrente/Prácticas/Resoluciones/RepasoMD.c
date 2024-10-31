//-------------------Pasaje de mensajes---------------------
/* 
1) En una oficina existen 100 empleados que envían documentos para imprimir en 5 impresoras compartidas. Los pedidos de impresión son procesados por orden de llegada y se asignan a la primera impresora que se encuentre libre:
a) Implemente un programa que permita resolver el problema anterior usando PMA.
*/
chan impresión(archivo)
Process empleado[0..99]
{
  archivo a;
  send(a);
}
process impresora[0..4]
{
  while true{
    receive impresión(a);
    imprimir(a);
  }
}


/* b) Resuelva el mismo problema anterior pero ahora usando PMS.  */

Process empleado[id: 0..99]
{
  archivo a;
  administrador!imprimir(a);
}
process impresora[id: 0..4]
{
  while true{
    administrador!pedir(id);
    administrador?archivo(a);
    imprimir(a);
  }
}
process administrador
{
  cola archivos;
    do empleado[*]?imprimir(a)-> archivos.push(a)
    [] impresora[*]?pedir(idImpresora)-> impresora[idImpresora]!archivo(archivos.pop());
    od
}

/* 2) Resolver el siguiente problema con PMS. En la estación de trenes hay una terminal de SUBE que debe ser usada por P personas de acuerdo con el orden de llegada. Cuando la persona accede a la terminal, la usa y luego se retira para dejar al siguiente. Nota: cada Persona usa sólo una vez la terminal.  */

process terminal
int id;
bool libre=true;
cola personas;
{
  do persona[*]?llegue(id)-> if(!libre){
                              personas.push(id);
                            }
                            else{
                              libre=false;
                              persona[id]!ingreso();
                            }
  [] persona[*]?termine()-> if(personas.lenght>0){
                              persona[personas.pop()]!ingreso();
                            }
                            else{
                              libre=true;
                            }     
  od
}
process administrador


process persona[id:0..p-1]
{
  terminal!llegue();
  terminal?ingreso();
  //usar terminal;
  terminal!termine();
}

/* 3) Resolver el siguiente problema con PMA. En un negocio de cobros digitales hay P personas que deben pasar por la única caja de cobros para realizar el pago de sus boletas. Las personas son atendidas de acuerdo con el orden de llegada, teniendo prioridad aquellos que deben pagar menos de 5 boletas de los que pagan más. Adicionalmente, las personas embarazadas tienen prioridad sobre los dos casos anteriores. Las personas entregan sus boletas al cajero y el dinero de pago; el cajero les devuelve el vuelto y los recibos de pago. */

process persona[id:0..P-1]{
  
}