
----------------------Practica 2-------------------------------
/* 1. Existen N personas que deben ser chequeadas por un detector de metales antes de poder ingresar al avión.
a. Analice el problema y defina qué procesos, recursos y semáforos/sincronizaciones serán necesarios/convenientes para resolverlo.
Proceso persona[n], semáforo, variable compartida (para asegurarse de que todos hayan pasado)

b. Implemente una solución que modele el acceso de las personas a un detector (es decir, si el detector está libre la persona lo puede utilizar; en caso contrario, debe esperar). */

1b-
sem mutex=1
process persona[id:0..N-1]
{
  P(mutex);
  //usar detector
  V(mutex);
}

/* c. Modifique su solución para el caso que haya tres detectores. */
1c-
sem mutex=3
process persona[id:0..N-1]
{
  P(mutex);
  //usar detector
  V(mutex);
}

/* d. Modifique la solución anterior para el caso en que cada persona pueda pasar más de una vez, siendo aleatoria esa cantidad de veces.  */

1d-
sem mutex=3
process persona[id:0..N-1]
{
  int veces= random
  for (int i=0; i<veces; i++){
    P(mutex);
    //usar detector
    V(mutex);
  }
}
/* 2. Un sistema de control cuenta con 4 procesos que realizan chequeos en forma colaborativa. Para ello, reciben el historial de fallos del día anterior (por simplicidad, de tamaño N). De cada fallo, se conoce su número de identificación (ID) y su nivel de gravedad (0=bajo, 1=intermedio, 2=alto, 3=crítico). Resuelva considerando las siguientes situaciones:
a) Se debe imprimir en pantalla los ID de todos los errores críticos (no importa el orden). */

2a-
Cola c;
int cant=0;
sem mutex=1, 
Process chequeador[0..4]
{
  int nivel, ID;
  P(mutex);
  while (cant<n){
    cant++;
    Pop(C,(nivel, ID));
    V(mutex);
     if (nivel==3){
      print(ID);
    }
    P(mutex)
  }
  V(mutex);
}
/* b) Se debe calcular la cantidad de fallos por nivel de gravedad, debiendo quedar los resultados en un vector global. */
2b-
Cola c;
int cant=0;
sem mutex[4] = ([4] 1);
sem mCola=1;
int conteo[4] = ([4] 0);
Process chequeador[0..4]
{
  int nivel, ID;
  P(mCola);
  while (cant<n){
    cant++;
    Pop(C,(nivel, ID));
    V(mCola);
    P(mutex[nivel]);
    conteo[nivel]++;
    V(mutex[nivel]);
     if (nivel==3){
      print(ID);
    }
    P(mCola)
  }
  V(mCola);
}

/* c) Ídem b) pero cada proceso debe ocuparse de contar los fallos de un nivel de gravedad determinado. */

2c-
Cola c;
int cant=0;
sem mutex=1;
int conteo[4] = ([4] 0);
Process chequeador[0..4]
{
  int nivel, ID;
  P(mutex);
  while (cant<n){
    cant++;
    Pop(C,(nivel, ID));
    V(mutex);
    if(nive==id){
      conteo[nivel]++;
      if (nivel==3){
      print(ID);
    }
    else{
      P(mutex);
      Push(C,(nivel, ID));
      cant--;
      V(mutex);
    }
    P(mutex)
  }
  V(mutex);
  }
}

/* 3. Un sistema operativo mantiene 5 instancias de un recurso almacenadas en una cola. Además, existen P procesos que necesitan usar una instancia del recurso. Para eso, deben sacar la instancia de la cola antes de usarla. Una vez usada, la instancia debe ser encolada nuevamente para su reúso. */

3-
cola recursos[0..5];
sem mutex = 5;
Process proceso[0..n-1];
{
  recurso recurso;
  P(mutex);
  pop(recursos, recurso);
  V(mutex);
  //usar recurso
  P(mutex);
  push(recursos, recurso);
  V(mutex);
}
/*
. Suponga que existe una BD que puede ser accedida por 6 usuarios como máximo al mismo tiempo. Además, los usuarios se clasifican como usuarios de prioridad alta y usuarios de prioridad baja. Por último, la BD tiene la siguiente restricción:
• no puede haber más de 4 usuarios con prioridad alta al mismo tiempo usando la BD.
• no puede haber más de 5 usuarios con prioridad baja al mismo tiempo usando la BD.
Indique si la solución presentada es la más adecuada. Justifique la respuesta. 
Var
total: sem := 6;
alta: sem := 4;
baja: sem := 5;
Process Usuario-Alta [I:1..L]:: 
 { P (total);
 P (alta);
 //usa la BD
 V(total);
 V(alta);
 }
Process Usuario-Baja [I:1..K]:: 
 { P (total);
 P (baja);
//usa la BD
 V(total);
 V(baja);
 }
*/

No está buena, tendría más sentido que primero se haga el P(alta/baja) y después el de la total pq si por ejemplo no puede entrar ninguno de p alta pero si de baja va a entrar al primero (ocupando un lugar) pero no puede entrar al segundo

/*
5. En una empresa de logística de paquetes existe una sala de contenedores donde se preparan las entregas. Cada contenedor puede almacenar un paquete y la sala cuenta con
capacidad para N contenedores. Resuelva considerando las siguientes situaciones:
a) La empresa cuenta con 2 empleados: un empleado Preparador que se ocupa de preparar los paquetes y dejarlos en los contenedores; un empelado Entregador que se ocupa de tomar los paquetes de los contenedores y realizar la entregas. Tanto el Preparador como el Entregador trabajan de a un paquete por vez.
*/
5a-
Paquete c[N]; 
sem vacio = 1; 
sem lleno = 0;

Process preparador {
    while (true){
        //preparar paquete;
        P(vacio);
        c.push(paquete);
        V(lleno);
    }
}

Process entregador {
    while (true){
        P(lleno);
        paquete = c.pop();
        V(vacio);
        //entregar paquete;
    }
}
//PREGUNTAR: esta solucionno obliga a que siempre sea uno y uno????? yo querria que si el entregador es muy lentto puedan ir produciendo varios a la vez

5a-
cola paquetes[n];
int cant=0;
sem mutex = 1;
sem pedidos = 0;
process Preparador
{
  while (true){
    //producir paquete
    if (cant<n){
      P(mutex);
      push(paquetes, paquete);
      cant++;
      V(pedidos)
      V(mutex);
  }
}
}
process Entregador
{
  while (true){
    P(pedidos);
    P(mutex);
    paquete = pop(paquetes);
    cant--;
    V(mutex);
    //entregar
    }
}

//el entregador avisa que llegó haciendo el p de pedidos, el preparador avisa que hay algo poniendolo en V(pedidos)

//b) Modifique la solución a) para el caso en que haya P empleados Preparadores.
5b-
//la unica diferencia es que tengo que hacer que el acceso a cant sea exclusivo
cola paquetes[n];
int cant=0;
sem mutex = 1;
sem pedidos = 0;

process Preparador[0..p-1]
{
  while (true){
    //producir paquete
    P(mutex);
    if (cant<n){
      push(paquetes, paquete);
      cant++;
      V(pedidos)
  }
   V(mutex);
}
}
process Entregador
{
  while (true){
    P(pedidos);
    P(mutex);
    paquete = pop(paquetes);
    cant--;
    V(mutex);
    }
}
/*
c) Modifique la solución a) para el caso en que haya E empleados Entregadores.
*/

5a-
cola paquetes[n];
int cant=0;
sem mutex = 1;
sem pedidos = 0;
process Preparador
{
  while (true){
    //producir paquete
    if (cant<n){
      P(mutex);
      push(paquetes, paquete);
      cant++;
      V(mutex);
      V(pedidos)
  }
}
}
process Entregador[0..E-1]
{
  while (true){
    P(pedidos);
    P(mutex);
    paquete = pop(paquetes);
    cant--;
    V(mutex);
    }
    //entregar
   
}
/*
d) Modifique la solución a) para el caso en que haya P empleados Preparadores y E empleadores Entregadores.
*/
cola paquetes[n];
int cant=0;
sem mutex = 1;
sem pedidos = 0;
process Preparador
{
  while (true){
    //producir paquete
    P(mutex);
    if (cant<n){
      push(paquetes, paquete);
      cant++;
      V(pedidos)
  }
   V(mutex);
}
}
process Entregador[0..E-1]
{
  while (true){
    P(pedidos);
    P(mutex);
    paquete = pop(paquetes);
    cant--;
    V(mutex);
    }
    //entregar
   
}

/* 6. Existen N personas que deben imprimir un trabajo cada una. Resolver cada ítem usando 
semáforos:
a) Implemente una solución suponiendo que existe una única impresora compartida por todas las personas, y las mismas la deben usar de a una persona a la vez, sin importar el orden. Existe una función Imprimir(documento) llamada por la persona que simula el uso de la impresora. Sólo se deben usar los procesos que representan a las Personas. */
6a-
sem mutex=1;

process persona[0..N-1]
{
  P(mutex);
  Imprimir(documento);
  V(mutex);
}

/* b) Modifique la solución de (a) para el caso en que se deba respetar el orden de llegada */
6b-
sem mutex=1, espera[N]=([N]0)
cola orden[N];
boolean libre=true;

process persona[0..N-1]
{
  P(mutex);
  if(libre){
    libre=false;
    V(mutex);
  }
  else{
    push(orden, id);
    V(mutex);
    P(espera[id]);
  }
  Imprimir(documento);
  P(mutex);
  if (empty(orden)){
    libre=true;
  }
  else{
    int aux=pop(orden);
    V(espera[aux]);
  }
  V(mutex);
}
/* c) Modifique la solución de (a) para el caso en que se deba respetar estrictamente el orden dado por el identificador del proceso (la persona X no puede usar la impresora hasta que no haya terminado de usarla la persona X-1). */
6c-
sem espera[N]=([N]0);
int siguiente=0;
process persona[0..N-1]
{
  
  if(id!=siguiente){
    P(espera[id]);
  }
  else{
    siguiente++;
    Imprimir(documento);
    V(espera[siguiente]);
  }
}

/* d) Modifique la solución de (b) para el caso en que además hay un proceso Coordinador que le indica a cada persona que es su turno de usar la impresora */
6d-
sem mutex=1, 
espera[N]=([N]0)
sem alguien=0;
sem Ocupada=0;
cola orden[N];
int siguiente=0;

process persona[0..N-1]
{
  P(mutex);
    push(orden, id);
  V(mutex);
  V(alguien);
  P(espera[id]);
  Imprimir(documento);
  V(Ocupada)
}
Process Coordinador
{
  while(true){
    P(alguien);
    P(mutex);
    siguiente=pop(orden);
    V(mutex);
    V(espera[siguiente]);
    P(Ocupada);
  }
}

/* e) Modificar la solución (d) para el caso en que sean 5 impresoras. El coordinador le indica a la persona cuando puede usar una impresora, y cual debe usar. */
cola impresoras[5];
sem mutex=1, mutexImpresoras=5
sem espera[N]=([N]0)
sem alguien=0;
sem Ocupadas[5]=([5]1);
cola orden[N];
int siguiente=0;
int impresora;

process persona[0..N-1]
{
  P(mutex);
    push(orden, id);
  V(mutex);
  V(alguien);
  P(espera[id]);

  P(ocupadas[impresora]);
  Imprimir(documento, impresora);
  V(ocupadas[impresora]);

  p(mutexImpresoras);
  push(impresoras, impresora);
  V(mutexImpresoras);
}
Process Coordinador
{
  while(true){
    P(alguien);
    P(mutex);
    siguiente=pop(orden);
    V(mutex);

    P(mutexImpresoras);
    impresora=pop(impresoras);
    v(impresora);

    v(espera[siguiente]);
  }
}
/* 7. Suponga que se tiene un curso con 50 alumnos. Cada alumno debe realizar una tarea y existen 10 enunciados posibles. Una vez que todos los alumnos eligieron su tarea,comienzan a realizarla. Cada vez que un alumno termina su tarea, le avisa al profesor y se queda esperando el puntaje del grupo (depende de todos aquellos que comparten el mismo enunciado). Cuando un grupo terminó, el profesor les otorga un puntaje que representa el orden en que se terminó esa tarea de las 10 posibles Nota: Para elegir la tarea suponga que existe una función elegir que le asigna una tarea a un alumno (esta función asignará 10 tareas diferentes entre 50 alumnos, es decir, que 5 alumnos tendrán la tarea 1, otros 5 la tarea 2 y así sucesivamente para las 10 tareas). */
7-
sem seleccion
sem mutex=1, mutextareas=1; colaTermiandos=1; notaLista=0; notaRecibida=-4;//preguntar si esto es legal 
int cant=0;
int contadorTareas[10]=([10]0);
cola terminados;
int nota=0;

process alumno[0..49]
{
  int tarea; minota;
  tarea =elegirTarea();
  p(mutex);
  cant++;
  if (cant==50){
    v(seleccion);//terminamos de elegir
  }
  v(mutex);
  //hace tarea
  p(mutexTareas);
  contadorTareas[tarea]++;
  if (contadorTareas[tarea]==5){
    p(colaterminados);
    push(terminados, tarea);
    v(colaterminados);
  }
  v(mutexTareas);
  p(notaLista)//se queda esperando a que le den la nota
  minota=nota;
  v(notaRecibida);

}

process profesor
{
  int terminaron=10; 
  p(seleccion);//cuando paso de aca es que todos eligieron
  while(terminaron!=0){
    p(colaterminados);
    int grupoTerminado=pop(terminados, tarea);
    v(colaterminados);
    nota=terrminaron;
    v(notaLista);
    p(notaRecibida);
  }
}

/* 8. Una fábrica de piezas metálicas debe producir T piezas por día. Para eso, cuenta con E empleados que se ocupan de producir las piezas de a una por vez. La fábrica empieza a producir una vez que todos los empleados llegaron. Mientras haya piezas por fabricar, los empleados tomarán una y la realizarán. Cada empleado puede tardar distinto tiempo en fabricar una pieza. Al finalizar el día, se debe conocer cual es el empleado que más piezas fabricó.
a) Implemente una solución asumiendo que T > E.
b) Implemente una solución que contemple cualquier valor de T y E. */
8a-
int cantidadPiezas=T;
sem llegados=-E; empezar=0; trabajar=1; terminado=0;
int cantPiezasEmpleado[E]=([E]0);

process empleado[0..E-1]
{
  v(llegados);
  p(empezar);
  while (true){
   p(trabajar);
  if (cantidadPiezas==0){
    v(terminado);
  }
   //fabricar pieza
   cantidadPiezas--;
  cantPiezasEmpleado[id]++;
  v(trabajar);
  
  }
}

process gerente
{
  p(llegados);
  v(empezar);
  p(terminado);
}

/* 9. Resolver el funcionamiento en una fábrica de ventanas con 7 empleados (4 carpinteros, 1 vidriero y 2 armadores) que trabajan de la siguiente manera:
• Los carpinteros continuamente hacen marcos (cada marco es armando por un único carpintero) y los deja en un depósito con capacidad de almacenar 30 marcos.
• El vidriero continuamente hace vidrios y los deja en otro depósito con capacidad para 50 vidrios.
• Los armadores continuamente toman un marco y un vidrio (en ese orden) de los depósitos correspondientes y arman la ventana (cada ventana es armada por un único armador) */

9-
cola marcos[30], vidrios[50];
int cantMarcos=0, cantVidrios=0;
mutex mutexMarcos=1, mutexVidrios=1; mutexCantMarcos=0, mutexCantVidrios=0;
process carpintero[0..3]
{
  while (true){
    //hacer marco
    P(mutexCantMarcos);
    if (cantMarcos<30){
      cantMarcos++;
      P(mutexMarcos);
      push(marcos, marco);
      V(mutexMarcos);
    }
    v(mutexCantMarcos);
  }
}

process vidriero
{
  while (true){
  //hacer vidrio
  P(mutexCantVidrios);
    if (cantVidrios<50){
      cantVidrios++;
      P(mutexVidrios);
      push(vidrios, vidrio);
      V(mutexVidrios);
    }
    v(mutexCantVidrios);
  }
}

process armadores[0..1]
{
  marco, vidrio;
  while (true){
    P(mutexCantMarcos);
    if (cantMarcos>0){
      P(mutexMarcos);
      marco=pop(marcos);
      cantMarcos--;
      V(mutexMarcos);
    }
    V(mutexCantMarcos);
    P(mutexCantVidrios);
    if (cantVidrios>0){
      P(mutexVidrios);
      vidrio=pop(vidrios);
      cantVidrios--;
      V(mutexVidrios);
    }
    //armar ventana
  }
}
/*
10. A una cerealera van T camiones a descargarse trigo y M camiones a descargar maíz. Sólo 
hay lugar para que 7 camiones a la vez descarguen, pero no pueden ser más de 5 del mismo 
tipo de cereal.
a) Implemente una solución que use un proceso extra que actúe como coordinador 
entre los camiones. El coordinador debe retirarse cuando todos los camiones han 
descargado.
b) Implemente una solución que no use procesos adicionales (sólo camiones).
*/

//no se como plantear este, preguntaaaaaaaar
10a-
int cantTrigo=T, cantMaiz=M; 
mutex mutexTrigo=0, mutexMaiz=0; mCantMaiz=1, mCantTrigo=1; mCantTotal=1;
int actualTrigo=0, actualMaiz=0;

process coordinador
{
  int cantActual=0;
  while(cantTrigo>0 || cantMaiz>0){
   if(cantActual<7){
    cantActual++;
    if (actualTrigo<5){
      v(mutexTrigo);
      cantTrigo--;
      actualTrigo++;
    }
    else if (actualMaiz<5){
      v(mutexMaiz);
      cantMaiz--;
      actualMaiz++;
    }
    cantActual++;
  }
  //retirarse
}
}
process camionTrigo[0..T-1]
{
  p(mutexTrigo);
  //descargar
  p(mCantTrigo);
  actualTrigo--;
  v(mCantTrigo);
  p(mCantTotal);
  cantActual--;
  v(mCantTotal);

}
  
process camionMaiz[0..M-1]
{
 p(mutexMaiz);
 //descargar
  p(mCantMaiz);
  actualMaiz--;
  v(mCantMaiz);
  p(mCantTotal);
  cantActual--;
  v(mCantTotal);
}

10b-
int cantTrigo=0, cantMaiz=0; 
mutex mutexTrigo=1, mutexMaiz=1;
sem mutex=7

process camionTrigo[0..T-1]
{
  p(mutexTrigo);
  if (cantTrigo<5){
    cantTrigo++;
    v(mutexTrigo);
    p(mutex);
    //descargar
    p(mutexTrigo);
    cantTrigo--;
    v(mutex);
  }
    v(mutexTrigo);
}
  
process camionMaiz[0..M-1]
{
  p(mutexMaiz);
  if (cantMaiz<5){
    cantMaiz++;
    v(mutexMaiz);
    p(mutex);
    //descargar
    p(mutexMaiz);
    cantMaiz--;
    v(mutex);
  }
    v(mutexMaiz);
}
  
/*
11.En un vacunatorio hay un empleado de salud para vacunar a 50 personas. El empleado de salud atiende a las personas de acuerdo con el orden de llegada y de a 5 personas a la vez. Es decir, que cuando está libre debe esperar a que haya al menos 5 personas esperando, luego vacuna a las 5 primeras personas, y al terminar las deja ir para esperar  por otras 5. Cuando ha atendido a las 50 personas el empleado de salud se retira. Nota:todos los procesos deben terminar su ejecución; suponga que el empleado tienen una función VacunarPersona() que simula que el empleado está vacunando a UNA persona. 
*/
11-
//preguntar si esta bien
cola personas[50];
sem mutex=1, mutexCola=1, hayAlguien=0; vacunar[0..50]=([50]0);, continuar=0;

process persona[0..49]
{
  P(mutexCola);
  push(personas, id);
  v(hayAlguien);
  V(mutexCola);
  p(vacunar[id]);  
  //vacunando
  v(continuar);
}
process empleado
{
  int cant=0; int cantGrupo=0; int personas[5];
  while (cant<50){
   while (cantGrupo<5){
    P(hayAlguien);
    P(mutexCola);
      personas[cantGrupo]= pop(personas);
      cant++;
      cantGrupo++;
    V(mutexCola);
    }
    cantGrupo=0;
    for (int i=0; i<5; i++){
      V(Vacunar(personas[i]));
      VacunarPersona();
      p(continuar);
    }
  }
}
