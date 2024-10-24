//PRÁCTICA 4
/*  1. Suponga que N clientes llegan a la cola de un banco y que serán atendidos por sus  empleados. Analice el problema y defina qué procesos, recursos y canales/comunicaciones serán necesarios/convenientes para resolverlo. Luego, resuelva considerando las siguientes  situaciones:
a. Existe un único empleado, el cual atiende por orden de llegada.
 */
1a-
chan cola(int)
Process persona[id: 0..N-1]{
  send cola(id);
}
Process empleado{
  int persona;
  while true{
    receive cola(persona);
    //atender persona(persona)
  }
}

/* b. Ídem a) pero considerando que hay 2 empleados para atender, ¿qué debe modificarse en la solución anterior? */
1b-
chan cola(int)
Process persona[id: 0..N-1]{
  send cola(id);
}
Process empleado[id:0..2]{
  int persona;
  while true{
    receive cola(persona);
    //atender persona(persona)
  }
}
/* c. Ídem b) pero considerando que, si no hay clientes para atender, los empleados realizan tareas administrativas durante 15 minutos. ¿Se puede resolver sin usar procesos adicionales? ¿Qué consecuencias implicaría? */ 
1c-
chan siguiente[2](int)
chan cola(int)
chan libre(int)

Process persona[id: 0..N-1]{
  send cola(id);
}
Process empleado[id: 0..1]{
  int persona;
  while true{
    send libre(id)
    receive siguiente[id](persona)
    if(persona)!=-1{
      //atender Persona(persona)
    }
    else
    delay(15 min)//tareas administrativas
  }
}
Process recepcion{
  int persona;
  int proxEmpleado;
  while true{
    receive libre(proxEmpleado);
    if(empty(cola)) 
      persona=-1;
    else
      receive cola(persona);
    send siguiente[proxEmpleado](persona);
  }
}
//Se podría resolver sin procesos adicionales pero no estaría bueno porque puede haber demora innecesaria si hay un solo mensaje cuando ambos preguntan por los que ambos no se ponen a hacer tareas administrativas pero uno solo puede atender y el otro se queda zzz

/* 2. Se desea modelar el funcionamiento de un banco en el cual existen 5 cajas para realizar pagos. Existen P clientes que desean hacer un pago. Para esto, cada una selecciona la caja donde hay menos personas esperando; una vez seleccionada, espera a ser atendido. En cada caja, los clientes son atendidos por orden de llegada por los cajeros. Luego del pago, se les entrega un comprobante. Nota: maximizar la concurrencia. */
2-
chan liberado(int)
chan cola[5](int)
chan colaGeneral(int)
chan recibos[N](text, int)
process caja[id: 0..4]{
  int cliente;
  while true{
    receive cola[id](cliente);
    //atender(cliente);
    //recibo= hacerComprobante
    send recibo[cliente](recibo, id)
  }
}

process cliente[id: 0..n]{
  text comprobante;
  send colaGeneral(id);
  receive recibos[id] (comprobante, cola);
  send liberado(cola)
}

process coordinador{
  int proxCliente;
  int minCaja;
  int idCaja;
  int cantxCola[0..4]=0;
  while true{
    if(not empty(colaGeneral) and empty(liberado)){
      receive colaGeneral(proxCliente);
      minCaja = min(cantXCola);//retorna el id del mínimo elemento del array
      cantXCola[minCaja]++;
      send cola[minCaja](proxCliente);
    }
    else if(not empty(liberado)){
      receive liberado(idCaja)
      cantXCola[idCaja]--;
    }
  }
}

/* 3. Se debe modelar el funcionamiento de una casa de comida rápida, en la cual trabajan 2 cocineros y 3 vendedores, y que debe atender a C clientes. El modelado debe considerar que:
- Cada cliente realiza un pedido y luego espera a que se lo entreguen.
- Los pedidos que hacen los clientes son tomados por cualquiera de los vendedores y se lo pasan a los cocineros para que realicen el plato. Cuando no hay pedidos para atender, los vendedores aprovechan para reponer un pack de bebidas de la heladera (tardan entre 1 y 3 minutos para hacer esto).
- Repetidamente cada cocinero toma un pedido pendiente dejado por los vendedores, lo cocina y se lo entrega directamente al cliente correspondiente.
Nota: maximizar la concurrencia. */
chan filaPedir(int)
chan pedidos[c-1](int)
chan alPedo(int)
chan atender[2](int)
chan hacerPedido[c](int)
chan pedido[2](text)
chan filaPedidos(text, int)
chan comidita[c](comida)

Process cliente[id: 0..c]{
  int idVendedor;
  send filaPedir(id);
  receive hacerPedido[id](idVendedor);
  send pedido[idVendedor](text pedido)
  receive comidita[id](comida)
  //comer :)
}

Process cocinero[id: 0..1]{
  text pedido;
  int idCliente;
  while true{
    receive filaPedidos(idPedido, idCliente);
    //hacer pedido
    send comidita[cliente](comida)
  }
}

Process vendedor[id:0..2]{
  int cliente;
  text pedido;
  while true{
    send alPedo(id)
    if(not empty atender[id]()){
      receive atender[id](cliente);
      send hacerPedido[cliente](id);
      receive pedido[id](pedido);
      send filaPedidos(pedido, cliente);
    }
    else{
      delay(random(1,3))
    }
  }
}

Process administrador{
  int proximoCliente;
  int proximoVendedor;
  while true{
    receive alPedo(proximoVendedor)
    if(not empty(filaPedir)){
      receive filaPedir(proximoCliente)
    }
    else{
      proximoCliente=-1
    }
    send atender[proximoVendedor](proximoCliente);
  }
}

/* 4. Simular la atención en un locutorio con 10 cabinas telefónicas, el cual tiene un empleado que se encarga de atender a N clientes. Al llegar, cada cliente espera hasta que el empleado le indique a qué cabina ir, la usa y luego se dirige al empleado para pagarle. El empleado atiende a los clientes en el orden en que hacen los pedidos. A cada cliente se le entrega un ticket factura por la operación.
a) Implemente una solución para el problema descrito.
*/
a-
chan colaLlegada(int);
chan cabinaAusar[n](int);
chan termino(int);
chan ticket[n](ticket)
pagar(int)


Process empleado{
  int proximoCliente;
  int clientePagar;
  int cabinaLiberada;
  int cabinasLibres[10]=true;
  while true{
    if(not empty(colaLlegada) and empty(termino) and empty(pagar)){
    receive colaLlegada(proximoCliente);
    cabinaDisponible=buscarCabina(cabinas)
    cabinasLibres[cabinaDisponible]=false;
    send cabinaAusar[proximoCliente](cabina);
    }
    else if(not empty(pagar) and empty(termino)){
      receive pagar(clientePagar);
      ticket= cobrar(clientePagar);
      send ticket[clientePagar](ticket);
    }
    else if(not empty(termino)){
      receive termino(cabinaLiberada);
      cabinasLibres[cabinaLiberada]=true;
      
    }
  }
}

Process cliente[id:0..n]{
  int cabina;
  ticket ticket;
  send colaLlegada(id)
  receive cabinaAusar[id] cabina
  //usarCabina
  send termino(id, cabina)
  send pagar(id)
  receive ticket(ticket)
}

/* b) Modifique la solución implementada para que el empleado dé prioridad a los que terminaron de usar la cabina sobre los que están esperando para usarla.
Nota: maximizar la concurrencia; suponga que hay una función Cobrar() llamada por el empleado que simula que el empleado le cobra al cliente.  */
b-
chan colaLlegada(int);
chan cabinaAusar[n](int);
chan termino(int);
chan ticket[n](ticket)
pagar(int)


Process empleado{
  int proximoCliente;
  int clientePagar;
  int cabinaLiberada;
  int cabinasLibres[10]=true;
  while true{
    if(not empty(pagar)){
      receive pagar(clientePagar);
      ticket= cobrar(clientePagar);
      send ticket[clientePagar](ticket);
    }
    else if(not empty(colaLlegada) and empty(termino) and empty(pagar)){
    receive colaLlegada(proximoCliente);
    cabinaDisponible=buscarCabina(cabinas)
    cabinasLibres[cabinaDisponible]=false;
    send cabinaAusar[proximoCliente](cabina);
    }
    
    else if(not empty(termino) and empty(colaLlegada) and empty(pagar)){
      receive termino(cabinaLiberada);
      cabinasLibres[cabinaLiberada]=true;
      
    }
  }
}

Process cliente[id:0..n]{
  int cabina;
  ticket ticket;
  send colaLlegada(id)
  receive cabinaAusar[id] cabina
  //usarCabina
  send termino(id, cabina)
  send pagar(id)
  receive ticket(ticket)
}
/* 5. Resolver la administración de 3 impresoras de una oficina. Las impresoras son usadas por N administrativos, los cuales están continuamente trabajando y cada tanto envían documentos a imprimir. Cada impresora, cuando está libre, toma un documento y lo imprime, de acuerdo con el orden de llegada. 
a) Implemente una solución para el problema descrito. */
a-
chan documentos(documento)

process impresora[0..2]{
  while true{
    receive documentos(documento);
    //imprimir(documento)
  }
}

process administrativo[0..n]{
  while true{
    delay(random());
    send imprimir(documento);
  }
}

/* b) Modifique la solución implementada para que considere la presencia de un director de 
oficina que también usa las impresas, el cual tiene prioridad sobre los administrativos.
 */
chan documentos(documento)
chan documentosPijudo(documento)
chan imprimir(documento)

process impresora[0..2]{
  while true{
    receive imprimir(documento);
    //imprimir(documento)
  }
}

process administrativo[0..n]{
  while true{
    delay(random());
    send documentos(documento);
  }
}
process directorPijudo{
  while true{
    delay(random());
    send documentosPijudo(documento);
  }
}

process coordinador{
  while true{
    if(not empty(documentosPijudo)){
      receive documentosPijudo(documentoPijudo)
      send imprimir(documentoPijudo);
    }
    else if((not empty (documentos) and empty(documentoPijudo)){
      receive documentos(documento);
      send imprimir(documento);
    }
    
  }
}



/* c) Modifique la solución (a) considerando que cada administrativo imprime 10 trabajos y 
que todos los procesos deben terminar su ejecución. */
a-
chan documentos(documento);

process impresora[0..2]{
  documento documento=0;
  while documento!>-1{
    receive imprimir(documento);
    if (documento !=-1){
      //imprimir(documento)
    }
  }
}

process administrativo[0..n]{
  repeat 10{
    delay(random());
    send documentos(documento);
  }
}

process coordinador[]{
  int cant=0;
  while cant<n*10{
    receive documentos(documento);
    cant++;
    send imprimir(documento);
  }
  repeat(2)
    send imprimir(-1);
}

/* 
d) Modifique la solución (b) considerando que tanto el director como cada administrativo 
imprimen 10 trabajos y que todos los procesos deben terminar su ejecución. */

chan documentos(documento)
chan documentosPijudo(documento)
chan imprimir(documento)

process impresora[0..2]{
  int documento=0;
  while (documento!=-1){
    receive imprimir(documento);
    if (documento !=-1){
      //imprimir(documento)
    }
  }
}

process administrativo[0..n]{
  repetat 10{
    delay(random());
    send documentos(documento);
  }
}
process directorPijudo{
  repeat 10{
    delay(random());
    send documentosPijudo(documento);
  }
}

process coordinador{
  int cant=0;
  while (cant< (n+1)*10){
    if(not empty(documentosPijudo)){
      receive documentosPijudo(documentoPijudo)
      send imprimir(documentoPijudo);
      cant++;
    }
    else if(not empty (documentos) and empty(documentoPijudo)){
      receive documentos(documento);
      send imprimir(documento);
      cant++;
    }
  }
  send imprimir(-1);
  send imprimir(-1);
}

/* e) Si la solución al ítem d) implica realizar Busy Waiting, modifíquela para evitarlo. Nota: ni los administrativos ni el director deben esperar a que se imprima el documento */

chan documentos(documento)
chan documentosPijudo(documento)
chan imprimir(documento)
chan hayDocumentos(bool)

process impresora[0..2]{
  int documento=0;
  while (documento!=-1){
    receive imprimir(documento);
    if (documento !=-1){
      //imprimir(documento)
    }
  }
}

process administrativo[0..n]{
  repetat 10{
    delay(random());
    send documentos(documento);
    send hayDocumentos(true)
  }
}
process directorPijudo{
  repeat 10{
    delay(random());
    send documentosPijudo(documento);
    send hayDocumentos(true)
  }
}

process coordinador{
  int cant=0;
  while (cant< (n+1)*10){
    receive hayDocumentos(bool)
    if(not empty(documentosPijudo)){
      receive documentosPijudo(documentoPijudo)
      send imprimir(documentoPijudo);
      cant++;
    }
    else if(not empty (documentos) and empty(documentoPijudo)){
      receive documentos(documento);
      send imprimir(documento);
      cant++;
    }
  }
  send imprimir(-1);
  send imprimir(-1);
}

//OJO: todos los ifs son no determinísticos, por lo que se elije uno al azar. tengo que en el segundo if en caso de querer prioridad preguntar que no haya cosas en el más prioritario. tambiuén tengol que tener cuidado con donce incremento cant, al hacerlo así ya no me sirve incrementarlo ni bien entra. por otro lado si no fuera no determinístico también estaría mal pq si enmtra un prioritario después de que hice el primer if y antes del segundo no lo va a atender. es probable que vaarios eejercicios tengan eso mal xd me da fiaca arreglarlo 