//------------------------Práctica3---------------------------------

//NOTA PREGUNTAR POR EL TEMA DE LA ROPTURA DE LA MUTEX EN ESTE TIPO DE EJERCICIOS
//NOTA2 PREGUNTAR CUAL ES LA DIFERENCIA ENTRE EL IF Y EL WHILE
/*
1. Se dispone de un puente por el cual puede pasar un solo auto a la vez. Un auto pide permiso para pasar por el puente, cruza por el mismo y luego sigue su camino.

Monitor Puente
 cond cola;
 int cant= 0;
 Procedure entrarPuente ()
 while ( cant > 0){
  wait (cola);
  //comentario
}
 cant = cant + 1;
 end;
 Procedure salirPuente ()
 cant = cant – 1;
 signal(cola);
 end;
End Monitor;
Process Auto [a:1..M]
 Puente. entrarPuente (a);
 “el auto cruza el puente”
 Puente. salirPuente(a);
End Process;

a. ¿El código funciona correctamente? Justifique su respuesta.
*/
No, el código tiene el problema de que puede ser que el auto que marcó como libre llame a otro mientras entra uno nuevo y entonces se rompa la exclusión mutua
/*
b. ¿Se podría simplificar el programa? ¿Sin monitor? ¿Menos procedimientos? ¿Sin variable condition? En caso afirmativo, rescriba el código.
*/
Si, se podría simplificar el programa. 
Monitor Puente
 procedure cruzarPuente()
  //cruza el puente
 end;
End Monitor;
Process Auto [a:1..M]
 puente.cruzarPuente();
End Process;

El monitor maneja la exclusión mutua, no es necesario hacerlo manualmente
/*
c. ¿La solución original respeta el orden de llegada de los vehículos? Si rescribió el código en el punto b), ¿esa solución respeta el orden de llegada?
*/

No, ninguna de las soluciones asegura orden porque no podemos asegurar cuando se le da acceso al monitor a un proceso

/* 2. Existen N procesos que deben leer información de una base de datos, la cual es administrada por un motor que admite una cantidad limitada de consultas simultáneas.
a) Analice el problema y defina qué procesos, recursos y monitores sincronizaciones serán necesarios/convenientes para resolverlo.
b) Implemente el acceso a la base por parte de los procesos, sabiendo que el motor de base de datos puede atender a lo sumo 5 consultas de lectura simultáneas.
*/
2-
Monitor BD{
  int cant=5;
  cond esperaC;
  int esperando=0;
  
  Procedure pasar(){
    if (cant==0){
      esperando++;
      wait (esperaC)
    }
    else {
      cant--
  }
  Procedure salir(){
    cant ++;
    if (esperando >0){
      esperando--
      signal(esperaC)
    }
  }
}
process proceso[0..N-1]
{
  BD.pasar()
  //leer
  BD.salir()
}
/*
3. Existen N personas que deben fotocopiar un documento. La fotocopiadora sólo puede ser usada por una persona a la vez. Analice el problema y defina qué procesos, recursos y monitores serán necesarios/convenientes, además de las posibles sincronizaciones requeridas para resolver el problema. Luego, resuelva considerando las siguientes situaciones:
a) Implemente una solución suponiendo no importa el orden de uso. Existe una función Fotocopiar() que simula el uso de la fotocopiadora.
*/
3a- 
Monitor fotocopiadora{
  procedure fotocopiar(documento){
    //fotocopiar
  }
}
Process persona[0..N-1]{
  fotocopiadora.fotocopiar(documento)
}
/*
b) Modifique la solución de (a) para el caso en que se deba respetar el orden de llegada.
*/
3b- 
Monitor fotocopiadora{
  cond colaEspera;
  int esperando=0;
  boolean ocupada=false;

  procedure usar(){
    if (ocupada){
      esperando++;
      wait(colaEspera);
    }
    else ocupada=true;
    
  }
  procedure salir(){
    if (esperando>0){
      esperando--;
      signal(colaEspera);
    }
    else ocupada=false;
  }
}
Process persona[0..N-1]{
  fotocopiadora.usar();
  //fotocopiar
  fotocopiadora.salir();
}
/*c) Modifique la solución de (b) para el caso en que se deba dar prioridad de acuerdo con la edad de cada persona (cuando la fotocopiadora está libre la debe usar la persona de mayor edad entre las que estén esperando para usarla). */
3c- 
Monitor fotocopiadora{
  cond espera[N];
  colaOrdenada fila;
  int esperando=0;
  boolean ocupada=false;
  int idProx;

  procedure usar(idProceso, edad: in int){
    if (ocupada){
      esperando++;
      push(filaOrdenada, idProceso, edad)
      wait(espera[idProceso]);
    }
    else ocupada=true;
    
  }
  procedure salir(){
    if (esperando>0){
      esperando--;
      pop(filaOrdenada, idProx)
      signal(espera[idProx]);
    }
    else ocupada=false;
  }
}
Process persona[0..N-1]{
  fotocopiadora.usar(id, edad);
  //fotocopiar
  fotocopiadora.salir();
}
/*
d) Modifique la solución de (a) para el caso en que se deba respetar estrictamente el orden dado por el identificador del proceso (la persona X no puede usar la fotocopiadora hasta que no haya terminado de usarla la persona X-1).
*/
3d- 
Monitor fotocopiadora{
  cond espera[N];
  int idProx=1;

  procedure usar(idProceso: in int){
    if (idProceso !=proximo){
      wait(espera[idProceso]);
    }
  }
  procedure salir(){
    idProx++;
    signal(espera[idProx]);

  }
}
Process persona[0..N-1]{
  fotocopiadora.usar(id);
  //fotocopiar
  fotocopiadora.salir();
}
/*
e) Modifique la solución de (b) para el caso en que además haya un Empleado que le indica a cada persona cuando debe usar la fotocopiadora
*/
3e- 
Monitor fotocopiadora{
  cond Espera[N];
  cond terminó
  cola colaEspera;
  int esperando=0;
  boolean ocupada=false;

  procedure usar(id: int in){
      esperando++//esto lko hago para que no pueda hacer el empleado un pop antes de que se pushee alguien
      push(colaEspera,id)
      wait(Espera[id]);
      //para salir de acá lo tienen que haber desperado
  }
  procedure salir(){
    signal(terminó)
  }
  procedure siguiente(){
    int idAux;
    if(esperando>0){
      idAux= pop(id, colaEspera);
      esperando--;
      signal(Espera[id])
      wait(Terminó)
    }
    
  }
}
Process persona[0..N-1]{
  fotocopiadora.usar();
  //fotocopiar
  fotocopiadora.salir();
}
Process Empleado{
  for (int i=0; i<N-1; n++){
    impresora.siguiente();
  }
}

/*
f) Modificar la solución (e) para el caso en que sean 10 fotocopiadoras. El empleado le indica a la persona cuál fotocopiadora usar y cuándo hacerlo. */
3f-
Monitor fotocopiadora{
  cond Espera[N];
  cond terminó
  cola colaEspera;
  cola fotocopiadorasLibres{1,2,3,4,5,6,7,8,9,10};
  int proximaImpresora=-1

  int esperando=0;
  boolean ocupada=false;

  procedure usar(id: int in){
      esperando++//esto lko hago para que no pueda hacer el empleado un pop antes de que se pushee alguien
      push(colaEspera,id)
      wait(Espera[id]);
      //para salir de acá lo tienen que haber desperado
      return proximaImpresora
  }
  procedure salir(){
    signal(terminó)
    push fotocopiadorasLibres(proximaImpresora)
  }
  procedure siguiente(){
    int idAux; 
    if(esperando>0 && fotocopiadorasLibres.length>0){
      proximaIprpesora=pop(fotocopiadorasLibres)
      idAux= pop(colaEspera);
      esperando--;
      signal(Espera[idAux])
      wait(Terminó)
    }
    
  }
}
Process persona[0..N-1]{
  int proximaImpresora;             
  proximaImpresora = fotocopiadora.usar(id); 
  // Fotocopiar
  fotocopiadora.salir(proximaImpresora);
}
Process Empleado{
  for (int i=0; i<N-1; n++){
    impresora.siguiente();
  }
}

/*
4. Existen N vehículos que deben pasar por un puente de acuerdo con el orden de llegada.
Considere que el puente no soporta más de 50000kg y que cada vehículo cuenta con su propio
peso (ningún vehículo supera el peso soportado por el puente).
*/
monitor puente{
  int pesoMax=50000
  int pesoActual=0
  cond espera
  int esperando=0;

  procedure llegar(peso: in int){
    while(pesoMax<peso+pesoActual){
      esperando++;
      wait(espera);
    }
    //si sale es porque puede cruzar :)
    pesoActual=pesoActual+peso;
  }
  procedure irse(peso: in int){
    if(esperando>0){
      esperando--;
      pesoActual=pesoActual-peso
      signal(espera)
    }
  }
}
process vehiculo[0..N-1]{
  puente.llegar(peso)
  //cruzar
  puente.irse(peso)
}
/*
5. En un corralón de materiales se deben atender a N clientes de acuerdo con el orden de llegada.Cuando un cliente es llamado para ser atendido, entrega una lista con los productos que comprará, y espera a que alguno de los empleados le entregue el comprobante de la compra realizada.
a) Resuelva considerando que el corralón tiene un único empleado.
*/
Monitor corralon{
  cond espera; empleado; entrega;
  int cant=0
  boolean ocupado=false

  procedure llegar(){
    while (ocupado){
      cant++;
      wait(espera)
    }
    ocupado=true;
    signal(empleado)
    wait(entrega)
  }
  procedure irse(){
    if (cant<>0){
      cant--
      signal(espera)
    }
    ocupado=false;
  }
  atender(){
    wait(empleado)
  }
  entregar(){
    signal(entrega)
  }

}
process Cliente[0..N-1]{
  corralon.llegar()
  //entregar lista de compra
  corralon.irse()
}
process Empleado{
  for (int i=0; i<n-1; i++){
    corralon.atender()
    //hacer comrpobante
    corralon.entregar()
  }
}
/*
b) Resuelva considerando que el corralón tiene E empleados (E > 1). Los empleados no deben terminar su ejecución
*/
Monitor corralon{
  cola empleadosLibres;
  cond espera; 
  int cantLibres=0, cantEsperando=0

  procedure llegar(idE: out int){
    while (cantLibres==0){
      cantEsperando++;
      wait(espera)
    }
    cantLibres--;
    idE= pop(empleadosLibres)
  }
  
  atender(idE: in int){
    push(empleadosLibres, idE);
    cantLibres++;
    if (cantEsperando<>0){
      cantEsperando--
      signal(espera)
    }
    
  }
  
}
Monitor escritorio[0..E-1]{
  string papel;
  boolean hayPapel;
  cond esperandoPapeles; estaElRecibo;
  
  procedure darPapeles(papel: string in){
    papel=papel;
    hayPapel=true;
    signal(esperandoPapeles);
    wait(estaElRecibo)
  }
  procedure recibirPapel(){
    if(!hayPapel){
      wait(esperandoPapeles);
    }
  }
  procedure dejarRecibo(){
    signal(estaElRecibo)
  }
}
process Cliente[0..N-1]{
  corralon.llegar(idE)
  escritorio[idE].darPapeles(papel)
}
process Empleado[0..E-1]{
  while true{
    banco.atender(idEmp);
    escritorio[idEmp].recibirPapel;
    //generar recibo
    escritorio[idEmp].dejarRecibo;
  }
}
/*
c) Modifique la solución (b) considerando que los empleados deben terminar su ejecución cuando se hayan atendido todos los clientes.
*/
Monitor corralon{
  cola empleadosLibres;
  cond espera; 
  int cantLibres=0, cantEsperando=0 int cantRestante=N

  procedure hayClientes(){
    return cantRestante;
  }
  procedure llegar(idE: out int){
    while (cantLibres==0){
      cantEsperando++;
      wait(espera)
    }
    cantLibres--;
    idE= pop(empleadosLibres)
  }
  
  atender(idE: in int){
    push(empleadosLibres, idE);
    cantLibres++;
    cantRestante--;
    if (cantEsperando<>0){
      cantEsperando--
      signal(espera)
    }
    
  }
  
}
Monitor escritorio[0..E-1]{
  string papel;
  boolean hayPapel;
  cond esperandoPapeles; estaElRecibo;
  
  procedure darPapeles(papel: string in){
    papel=papel;
    hayPapel=true;
    signal(esperandoPapeles);
    wait(estaElRecibo)
  }
  procedure recibirPapel(){
    if(!hayPapel){
      wait(esperandoPapeles);
    }
  }
  procedure dejarRecibo(){
    signal(estaElRecibo)
  }
}
process Cliente[0..N-1]{
  corralon.llegar(idE)
  escritorio[idE].darPapeles(papel)
}
process Empleado[0..E-1]{
  while Corralon.HayClientes(){
    banco.atender(idEmp);
    escritorio[idEmp].recibirPapel;
    //generar recibo
    escritorio[idEmp].dejarRecibo;
  }
}

/*
5. En un corralón de materiales se deben atender a N clientes de acuerdo con el orden de llegada.Cuando un cliente es llamado para ser atendido, entrega una lista con los productos que comprará, y espera a que alguno de los empleados le entregue el comprobante de la compra realizada.
a) Resuelva considerando que el corralón tiene un único empleado.
No sé que pasó con este ejercicio, estoy bastante segura de que lo resolví pero despawneó
*/
Monitor corralon{
  cola clientes;
  

}
process Cliente[0..N-1]{

}
process Empleado{

}

/*6. Existe una comisión de 50 alumnos que deben realizar tareas de a pares, las cuales son corregidas por un JTP. Cuando los alumnos llegan, forman una fila. Una vez que están todos en fila, el JTP les asigna un número de grupo a cada uno. Para ello, suponga que existe una función AsignarNroGrupo() que retorna un número “aleatorio” del 1 al 25. Cuando un alumno ha recibido su número de grupo, comienza a realizar su tarea. Al terminarla, el alumno le avisa al JTP y espera por su nota. Cuando los dos alumnos del grupo completaron la tarea, el JTP les asigna un puntaje (el primer grupo en terminar tendrá como nota 25, el segundo 24, y así sucesivamente hasta el último que tendrá nota 1). Nota: el JTP no guarda el número de grupo que le asigna a cada alumn */
6-
Monitor Tarea{
  cond espera[50]; esperaJTP, entregarNota; EsperarNota[50]
  fila filaAlumnos;
  int terminados[25];
  int alumnosConGrupo=0;
  int nroAsig[50] = ([50] = -1);
  int GruposTerminados[25] = ([50] = 0);
  int Nota[50] = ([50] = 0);
  int cantRestante=25;
  int cant=0;

  procedure esperarAlumnos{
    while(cant<50){
      wait(esperaJTP);
    }
  }
  procedure llegar(idAlumno:int in){
    cant++;
    wait(espera[idAlumno])
    filaAlumnos.push(idAlumno)
    if(cant==50)
    {
      signal(esperaJTP)
    }
  }
  procedure enviarNro(nroGrupo:int in){
    nroAsig[idAlumno]=AsignarNroGrupo;
    signal(espera[idAlumno])
    alumnosConGrupo++;
    if(alumnosConGrupo==50){
      wait(entregarNota)
    }
    
  }
  procedure recibirGrupo(out int nroGrupo; idAlumno){
    nroGrupo=nroAsig[idAlumno];
  }
  procedure termine(nroGrupo: int in){
    GruposTerminados[nroGrupo]++;
    wait(EsperarNota[idAlumno]);
    if GruposTerminados[nroGrupo]==2{
      signal(entregarNota);
    }
  }
  procedure darNota(nota: int in; idAlumno: int in){
    Nota[idAlumno]=nota;
    signal(EsperarNota[idAlumno]);
  }
  tarea.recibirNota(nota: int out; idAlumno: int in){
    nota= Nota[idAlumno];
  }
}

process JTP{
  esperarAlumnos();
  for(int i=0; i<49;i++){
    int aux=AsignarNroGrupo;
    tarea.enviarNro(aux)
  }
  for(int i=25; i>0;i--){
    tarea.darNota(i)
  }
}
process alumno{
  int nroGrupo; nota;
  Tarea.llegar(idAlumno);
  Tarea.recibirGrupo(nroGrupo, idAlumno);
  //realizarTarea
  Tarea.termine(nroGrupo, idAlumno);
  Tarea.recibirNota(nota, idAlumno);
}
/*
7. Se debe simular una maratón con C corredores donde en la llegada hay UNA máquina expendedoras de agua con capacidad para 20 botellas. Además, existe un repositor encargado de reponer las botellas de la máquina. Cuando los C corredores han llegado al inicio comienza la carrera. Cuando un corredor termina la carrera se dirigen a la máquina expendedora, espera su turno (respetando el orden de llegada), saca una botella y se retira. Si encuentra la máquina sin botellas, le avisa al repositor para que cargue nuevamente la máquina con 20 botellas; espera a que se haga la recarga; saca una botella y se retira. Nota: mientras se reponen las botellas se debe permitir que otros corredores se encolen.
*/