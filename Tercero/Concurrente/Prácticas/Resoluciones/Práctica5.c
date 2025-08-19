// ----------------------- Práctica 5 ----------------------------
/* 1. Se requiere modelar un puente de un único sentido que soporta hasta 5 unidades de peso. El peso de los vehículos depende del tipo: cada auto pesa 1 unidad, cada camioneta pesa 2 unidades y cada camión 3 unidades. Suponga que hay una cantidad innumerable de vehículos (A autos, B camionetas y C camiones). Analice el problema y defina qué tareas, recursos y sincronizaciones serán necesarios/convenientes para resolverlo.
a. Realice la solución suponiendo que todos los vehículos tienen la misma prioridad.
 */
a.
Procedure puente is
  task administrador is
    entry auto();
    entry camion();
    entry camioneta();    
    entry salida(t:in texto)
  end administrador;

  task type vehiculo;

  task body vehículo is
    Tipo:texto;
  begin
    if tipo="auto" then
      empleado.auto()
    if tipo="camioneta" then
      empleado.camioneta()
    if tipo="camión" then
      empleado.camion()
    //pasa
    empleado.salida(tipo)
  end vehículo;

  task body administrador is
    total=0;
    max=5;
  begin
    loop
      select
        accept salida(t:IN texto) do;
          if (t="auto"){
            total-=1
          }
          else if(t="camioneta"){
            total-=2
          }
          else total-=3;
        when(total<max-1 and salida.count=0) => accept auto() do total+=1;
        or
        when(total<max-2 and salida.count=0) => accept camioneta() do total+=2;
        or
        when(total<max-3 and salida.count=0) => accept camion() do total+=3;
      end select
    end loop;
  end administrador;
end puente;

/* b. Modifique la solución para que tengan mayor prioridad los camiones que el resto de los vehículos. */
a.
Procedure puente is
  task administrador is
    entry auto();
    entry camion();
    entry camioneta();    
    entry salida(t:in texto)
  end administrador;

  task type vehiculo;

  task body vehículo is
    Tipo:texto;
  begin
    if tipo="auto" then
      empleado.auto()
    if tipo="camioneta" then
      empleado.camioneta()
    if tipo="camión" then
      empleado.camion()
    //pasa
    empleado.salida(tipo)
  end vehículo;

  task body administrador is
    total=0;
    max=5;
  begin
    loop
      select
        accept salida(t:IN texto) do;
          if (t="auto"){
            total-=1
          }
          else if(t="camioneta"){
            total-=2
          }
          else total-=3;
        when(total+1<=max and salida.count=0 camion.count=0) => accept auto() do total+=1;
        or
        when(total+2<=max and salida.count=0 and camion.count=0) => accept camioneta() do total+=2; 
        or
        when(total+3<=max and salida.count=0) => accept camion() do total+=3;
      end select
    end loop;
  end administrador;
end puente;

/* 2. Se quiere modelar el funcionamiento de un banco, al cual llegan clientes que deben realizar un pago y retirar un comprobante. Existe un único empleado en el banco, el cual atiende de acuerdo con el orden de llegada.
a) Implemente una solución donde los clientes llegan y se retiran sólo después de haber sido atendidos.
*/

Procedure banco is
  task type cliente;

  task empleado is
    entry llegada(comprobante: out text, pago:in text);
  end empleado;
begin
  task body cliente is
    text pago, comprobante;
    begin
      empleado.llegada(comprobante, pago);
    end;
  end cliente;

  task body empleado is
    text pago;
    begin
      loop
        accept llegada(comprobante: out text, pago:in text) do
          comprobante=generarComprobante(pago);
        end llegada;
      end loop
  end empleado
end banco

/* b) Implemente una solución donde los clientes se retiran si esperan más de 10 minutos para realizar el pago. */
b.
Procedure banco is
  task type cliente;

  task empleado is
    entry llegada(comprobante: out text, pago:in text);
  end empleado;
begin
  task body cliente is
    text pago, comprobante;
    begin
      select
        empleado.llegada(comprobante, pago);
      or delay 10
        null; //retirarse
      end select;
    end cliente;

  task body empleado is
    text pago;
    begin
      loop
        accept llegada(comprobante: out text, pago:in text) do
          comprobante=generarComprobante(pago);
        end llegada;
      end loop
  end empleado
end banco

/*
c) Implemente una solución donde los clientes se retiran si no son atendidos inmediatamente.*/
b.
Procedure banco is
  task type cliente;

  task empleado is
    entry llegada(comprobante: out text, pago:in text);
  end empleado;
begin
  task body cliente is
    text pago, comprobante;
    begin
      select
        empleado.llegada(comprobante, pago);
      else 
        null; //retirarse
      end select;
  end cliente;

  task body empleado is
    text pago;
    begin
      loop
        accept llegada(comprobante: out text, pago:in text) do
          comprobante=generarComprobante(pago);
        end llegada;
      end loop
  end empleado
end banco

/*
d) Implemente una solución donde los clientes esperan a lo sumo 10 minutos para ser atendidos. Si pasado ese lapso no fueron atendidos, entonces solicitan atención una vez más y se retiran si no son atendidos inmediatamente.  */

b.
Procedure banco is
  task type cliente;

  task empleado is
    entry llegada(comprobante: out text, pago:in text);
  end empleado;
begin
  task body cliente is
    text pago, comprobante;
    begin
      select
        empleado.llegada(comprobante, pago);
      or delay 10
        select
          empleado.llegada(comprobante, pago);
        else
          null; //retirarse
        end select;
      end select;
  end cliente;

  task body empleado is
    text pago;
    begin
      loop
        accept llegada(comprobante: out text, pago:in text) do
          comprobante=generarComprobante(pago);
        end llegada;
      end loop
    end empleado
end banco

/* 3. Se dispone de un sistema compuesto por 1 central y 2 procesos periféricos, que se comunican continuamente. Se requiere modelar su funcionamiento considerando las siguientes condiciones:
- La central siempre comienza su ejecución tomando una señal del proceso 1; luego toma aleatoriamente señales de cualquiera de los dos indefinidamente. Al recibir una señal de proceso 2, recibe señales del mismo proceso durante 3 minutos.
- Los procesos periféricos envían señales continuamente a la central. La señal del proceso 1 será considerada vieja (se deshecha) si en 2 minutos no fue recibida. Si la señal del proceso 2 no puede ser recibida inmediatamente, entonces espera 1 minuto y vuelve a mandarla (no se deshecha). */

procedure sistema is
  task central is
    entry proceso1()
    entry proceso2()
    entry terminarTemporizador()
  end central

  task proceso1;
  task proceso2;
  task temporizador is
    entry iniciarTemporizador;
  end temporizador	

begin
  task body central is
    loop
        accept proceso1() do
          //procesar señal 1
          select
            accept proceso1();
            //procesar senial
          or
            accept proceso2() do;
            //procesar senial
            continuar=true;
            temporizador.iniciarTemporizador();
            loop (continuar);
              select
                when(terminarTemporizador.count=0)-> accept proceso2();
              or
                accept terminarTemporizador;
                continuar=false;
            end continuar;
          end select;
    end loop;
  end central

  task temporizador is
    loop
      accept iniciarTemporizador();
      delay 180
      central.terminarTemporizador()
    end loop;
  end temporizador;


  task body proceso1 is
    loop
      select
        central.proceso1();
      or delay 120
        null;
      end select;
    end loop;
  end proceso1  

  task body proceso2 is
    loop
        select
          central.proceso2();
        or delay 60;
        end select;
      end loop;
  end proceso2  
end sistema


/* 4. En una clínica existe un médico de guardia que recibe continuamente peticiones de atención de las E enfermeras que trabajan en su piso y de las P personas que llegan a la clínica ser atendidos.Cuando una persona necesita que la atiendan espera a lo sumo 5 minutos a que el médico lo  haga, si pasado ese tiempo no lo hace, espera 10 minutos y vuelve a requerir la atención del médico. Si no es atendida tres veces, se enoja y se retira de la clínica. 
Cuando una enfermera requiere la atención del médico, si este no lo atiende inmediatamente le hace una nota y se la deja en el consultorio para que esta resuelva su pedido en el momento que pueda (el pedido puede ser que el médico le firme algún papel). Cuando la petición ha sido recibida por el médico o la nota ha sido dejada en el escritorio, continúa trabajando y haciendo más peticiones. 
El médico atiende los pedidos dándole prioridad a los enfermos que llegan para ser atendidos. Cuando atiende un pedido, recibe la solicitud y la procesa durante un cierto tiempo. Cuando está libre aprovecha a procesar las notas dejadas por las enfermeras. */

process clínica
  task médico is
    entry enfermera();
    entry paciente();
    entry recibirNota(nota:IN text, id: IN int)
  end médico

  task type enfermera is
    entry Id(id: in int);
    entry recibirResultado(resultado: IN text);
  end enfermera

  task type paciente is

  task notas is
    entry dejarNota(nota: IN text, id: IN int)
    entry sacarNota()
  end notas;

arrayEnfermera: array (1..E) of Enfermera;
begin
  task body médico is
    loop
      select
        accept paciente() do
          //atender paciente
        end paciente;
      or
        when (paciente.count==0) -> notas.sacarNota() do
          accept recibirNota(nota, id);
          if (nota!="no hay")
            resultado=procesarNota(nota)
            notas.resultado(resultado, id)
          end if;
        end recibirNota;
    end loop;
  end médico;

  task notas is
  cola colaNotas(nota, id)
  begin
    loop
      select
        when(sacarNota.count=0)->accept dejarNota(nota, id) do
          colaNotas.push(nota, id);
          end dejarNota;
      or
        accept sacarNota() do
          if (colaNotas>0) do
            medico.recibirNota(colaNotas.pop())
          else
            medico.recibirNota("no hay", -1)
        end sacarNota;
      or
        accept resultado(resultado, id) do
          enfermera(id).recibirResultado(resultado)
        end resultado;
      end select
    end loop;
  end notas;


  task body enfermera is
  int id;
  begin
    accept id(pos:in int) do
      id=pos;
    end id;
    loop
      //hacer nota
      notas.dejarNota(nota, id);
      select
        accept recibirResultado(resultado: in text);//si no está sigue con otra cosita
      else
        null;
      end select;
    end loop;
  end enfermera;

  task body paciente is
    select 
      médico.paciente()//solicita atención
    or 
      delay 300//espera 5 minutos
      for i in 1..3 loop
        select
          médico.paciente();
        or
          delay 600; 
        end select;
        end loop;
    end select;
    //se va
  end paciente;

  begin
    for (i=0; i<E; i++)
      arrayEnfermera(i).id(i);
    endfor;
end clínica


//en este ejercicio (y capaz los anteriores) me olvidé completamente de que no me conviene hacer las cosas dentro de un accept pq es bloqueante xd
/* 
x- En un sistema para acreditar carreras universitarias, hay UN Servidor que atiende pedidos de U Usuarios de a uno a la vez y de acuerdo con el orden en que se hacen los pedidos. Cada usuario trabaja en el documento a presentar, y luego lo envía al servidor; espera la  respuesta de este que le indica si está todo bien o hay algún error. Mientras haya algún error, vuelve a trabajar con el documento y a enviarlo al servidor. Cuando el servidor le responde que está todo bien, el usuario se retira. Cuando un usuario envía un pedido espera a lo sumo  2 minutos a que sea recibido por el servidor, pasado ese tiempo espera un minuto y vuelve a intentarlo (usando el mismo documento).  */
process sistema
  task servidor is
    entry documento(in documento:text, out correcto: boolean)
  end servidor;
  task type usuario;

  arrayUsuarios: array(1..U) of usuario;

  task body servidor is
  loop
    accept documento(documento: in text, correcto:out boolean) do
      correcto=documento.esCorrecto(documento)
    end documento;
  end loop;
  end servidor;


  task body cliente is
  bool correcto=false;
  begin  
    documento=generarDocumento();
    loop(not correcto)
      select
        sistema.documento(documento, correcto); 
        if(not correcto){
          documento=arreglarDocumento(documento)
        }
      or delay 120;
        DELAY 60;
      end select
    end loop
  end cliente;

begin
  null;
end process;

/* 5. En una playa hay 5 equipos de 4 personas cada uno (en total son 20 personas donde cada una conoce previamente a que equipo pertenece). Cuando las personas van llegando esperan con los de su equipo hasta que el mismo esté completo (hayan llegado los 4 integrantes), a partir de ese momento el equipo comienza a jugar. El juego consiste en que cada integrante del grupo junta 15 monedas de a una en una playa (las monedas pueden ser de 1, 2 o 5 pesos) y se suman los montos de las 60 monedas conseguidas en el grupo. Al finalizar cada persona debe conocer el grupo que más dinero junto. Nota: maximizar la concurrencia. Suponga que para simular la búsqueda de una moneda por parte de una persona existe una función Moneda() que retorna el valor de la moneda encontrada. */

process playa
  task type persona is
    entry recibirId(ID: IN int);
    entry comenzar();
    entry recibirGanador(id:IN int);
  end persona;

  task type grupo is
    entry llegada(ID: IN int);
    entry resultadoParcial(res:IN int); 
    entry recibirGanador(id:IN int);
  end grupo;

  task administrador is
    entry resultadoGrupo(id:in int; resultadoGrupo:in int);
  end administrador;

  arrayPersonas: array(1..20) of persona;
  arrayGrupo: array(1..5) of grupo;

  task body persona is
    int nroGrupo=x //cada persona sabe a qué grupo pertenece
    int id;
    int sumaParcial=0;
    int ganador;
    begin
      accept recibirID(id: IN int)do
        id=id
      end recibirID;
      grupo(nroGrupo).llegada(id); //avisa al coordinador del equipo que llegó
      accept comenzar()
      for i in 1..15 loop
        moneda=moneda()
        sumaParcial+=moneda.valor
      end loop;
      grupo(nroGrupo).resultadoParcial(sumaParcial); //le pasa el valor al grupo
      accept recibirGanador(id:IN int) do
        ganador=id;
      end recibirGanador;
      if (ganador==nroGrupo)
        gane :)
      endif
  end persona;

  task body administrador is
    int maxResultado=-1;
    int grupoGanador=-1;
    begin
      for i in 1..5 loop
        arrayGrupo(i).recibirId(i)
      end loop;
      for i in 1..5 loop
        accept resultadoGrupo(id:in int; resultadoGrupo:in int) do
          if(resultadoGrupo>maxResultado)
            maxResultado=resultadoGrupo;
            grupoGanador=id;
        end resultadoGrupo;
      end loop;
      for i in 1..5 loop
        arrayGrupo(i).recibirGanador(id: in int);
      end loop;
    end
  end administrador;


  task body grupo is
    arrayIDs: array(1..4) of int;
    int resultado=0;
    int id;
    int ganador;
    begin
      accept recibirId(id: IN int) do
        id=id
      end recibirID
      for i in 1..4 loop
        accept llegada(id:IN int) do
          arrayIDs[i]=id
        end llegada;
      end loop;
      //cuando paso esto es pq llegaron todas las personas del equipo. Entiendo que no hay que esperar a que estén todos los equipos listos
      for i in 1..4 loop
        arrayPersonas(arrayIDs[i]).comenzar()
      end loop;
      for i in 1..4 loop
        accept resultadoParcial(res:IN int) do
          resultado+=res; //suma los resultados parciales de las 4 personas
        end resultadoParcial
      end loop;
      administrador.resultadoGrupo(id:in int; resultadoGrupo:in int);
      accept recibirGanador(id:IN int) do
        ganador=id;
      end recibirGanador;
      for i in 1..4 loop
        arrayPersonas(arrayIDs[i]).recibirGanador(ganador)
      end loop;
      
  end grupo;

begin
  for i in 1..20 loop
    arrayPersonas(i).recibirId(i)
  end loop;
end playa;

//esta versión es aceptable pero produce demoras si alguna persona tarda en recibir el comenzar y demás

process playa
  task type persona is
    entry recibirId(ID: IN int);
    entry recibirGanador(id:IN int);
  end persona;

  task type grupo is
    entry llegada(ID: IN int);
    entry resultadoParcial(res:IN int); 
    entry recibirGanador(id:IN int);
    entry comenzar();
  end grupo;

  task administrador is
    entry resultadoGrupo(id:in int; resultadoGrupo:in int);
  end administrador;

  arrayPersonas: array(1..20) of persona;
  arrayGrupo: array(1..5) of grupo;

  task body persona is
    int nroGrupo=x //cada persona sabe a qué grupo pertenece
    int id;
    int sumaParcial=0;
    int ganador;
    begin
      accept recibirID(id: IN int)do
        id=id
      end recibirID;
      grupo(nroGrupo).llegada(id); //avisa al coordinador del equipo que llegó
      grupo(nroGrupo).comenzar();//esto es para poder arrancar una vez que llegaron todos;
      for i in 1..15 loop
        moneda=moneda()
        sumaParcial+=moneda.valor
      end loop;
      grupo(nroGrupo).resultadoParcial(sumaParcial); //le pasa el valor al grupo
      accept recibirGanador(id:IN int) do
        ganador=id;
      end recibirGanador;
      if (ganador==nroGrupo)
        gane :)
      endif
  end persona;

  task body administrador is
    int maxResultado=-1;
    int grupoGanador=-1;
    begin
      for i in 1..5 loop
        arrayGrupo(i).recibirId(i)
      end loop;
      for i in 1..5 loop
        accept resultadoGrupo(id:in int; resultadoGrupo:in int) do
          if(resultadoGrupo>maxResultado)
            maxResultado=resultadoGrupo;
            grupoGanador=id;
        end resultadoGrupo;
      end loop;
      for i in 1..5 loop
        arrayGrupo(i).recibirGanador(id: in int);
      end loop;
    end
  end administrador;


  task body grupo is
    arrayIDs: array(1..4) of int;
    int resultado=0;
    int id;
    int ganador;
    begin
      accept recibirId(id: IN int) do
        id=id
      end recibirID
      for i in 1..4 loop
        accept llegada(id:IN int) do
          arrayIDs[i]=id
        end llegada;
      end loop;
      //cuando paso esto es pq llegaron todas las personas del equipo. Entiendo que no hay que esperar a que estén todos los equipos listos
      for i in 1..8 loop
        select
          accept empezar();
        or
          accept resultadoParcial(res:IN int) do
            resultado+=res; //suma los resultados parciales de las 4 personas
          end resultadoParcial
        end select
      end loop;
      administrador.resultadoGrupo(id:in int; resultadoGrupo:in int);
      accept recibirGanador(id:IN int) do
        ganador=id;
      end recibirGanador;
      for i in 1..4 loop
        arrayPersonas(arrayIDs[i]).recibirGanador(ganador)
      end loop;
      
  end grupo;

begin
  for i in 1..20 loop
    arrayPersonas(i).recibirId(i)
  end loop;
end playa;


/* 6. Se debe calcular el valor promedio de un vector de 1 millón de números enteros que se encuentra distribuido entre 10 procesos Worker (es decir, cada Worker tiene un vector de  100 mil números). Para ello, existe un Coordinador que determina el momento en que se debe realizar el cálculo de este promedio y que, además, se queda con el resultado. Nota: maximizar la concurrencia; este cálculo se hace una sola vez. */

process promedio
  task type worker is

  task coordinador is
    entry recibirPromedioParcial(Prom:In int);
  workerArray: array 1..10 of worker;
  task worker body is
    nros: array 1..100000 of int;
    int suma=0;
    int promedioParcial;
    nros=inicializarVector();
    begin
      for i in 1..100000 loop
        suma+=nros[i]
      end loop
      promedioParcial=suma/100000;
      coordinador.promedioParcial(promedioParcial:in int);

  end worker


  task coordinador body is
    int suma=0;
    int promedio;
    begin
      loop 10
        accept promedioParcial(parcial) do
          suma+=parcial;
        end promedioParcial;
      end loop
    promedio=suma/10;
  end coordinador

begin 
  null;
end promedio;

/* 7. Hay un sistema de reconocimiento de huellas dactilares de la policía que tiene 8 Servidores para realizar el reconocimiento, cada uno de ellos trabajando con una Base de Datos propia; a su vez hay un Especialista que utiliza indefinidamente. El sistema funciona de la siguiente manera: el Especialista toma una imagen de una huella (TEST) y se la envía a los servidores para que cada uno de ellos le devuelva el código y el valor de similitud de la huella que más se asemeja a TEST en su BD; al final del procesamiento, el especialista debe conocer el código de la huella con mayor valor de similitud entre las devueltas por los 8 servidores. Cuando ha terminado de procesar una huella comienza nuevamente todo el ciclo. Nota: suponga que existe una función Buscar(test, código, valor) que utiliza cada Servidor donde recibe como parámetro de entrada la huella test, y devuelve como parámetros de salida el código y el valor de similitud de la huella más parecida a test en la BD correspondiente. 
Maximizar la concurrencia y no generar demora innecesaria.  */


process reconocimiento
  task type servidor is
    siguienteCiclo();
  end;
  task especialista is
    solicitarHuella(huella: OUT huella);
    resultado(codigo: in int, valor:in int)
  end;
  arrayServidor: array 1..8 of server;

  task body especialista is
    huellaTomada huella;
    int codigoHuella;
    int mejorValor=-1;
  begin
    loop
      huellaTomada= tomarHuella();
      for i in 1..16
        select
          when(resultado.count==0) -> accept tomarHuella(huella: OUT huella) do
            huella=huellaTomada;
          end tomarHuella;
        or
          accept resultado(codigo: in int, valor:in int) do
            if(valor>mejorValor) //asumo que la mejor huella es la que devuelve mejor valor
              mejorValor=valor
              codigoHuella=codigo
            endif
          end resultado;
        end select;
      end for;
      for i in 1..8
        arrayServidor(i).siguienteCiclo;
      endFor;
    end loop;
  end especialista

  task body servidor is
    huella huella;
    int codigo;
    int valor;
  begin
    loop
      especialista.solicitarHuella(huella);
      Buscar(huella, código, valor);
      especialista.resultado(codigo, valor);
      accept siguienteCiclo(); //esto lo agrego porque sino un sv muy rápido podría hacer el trabajo 2 veces y uno lento ninguna y entiendo que es necesario que todos lo hagan 1 vez.
    end loop;
  end servidor;
begin
  null;
end reconocimiento;


/* 8. Una empresa de limpieza se encarga de recolectar residuos en una ciudad por medio de 3 camiones. Hay P personas que hacen reclamos continuamente hasta que uno de los camiones pase por su casa. Cada persona hace un reclamo y espera a lo sumo 15 minutos a que llegue un camión; si no pasa, vuelve a hacer el reclamo y a esperar a lo sumo 15 minutos a que llegue un camión; y así sucesivamente hasta que el camión llegue y recolecte los residuos. Sólo cuando un camión llega, es cuando deja de hacer reclamos y se retira. Cuando un camión está libre la empresa lo envía a la casa de la persona que más reclamos ha hecho sin ser atendido. Nota: maximizar la concurrencia. */

process recolección
  task type camión;

  task type persona is
    entry recoleccion();
  end;

  task administrador is
    entry camionLibre(idCliente:OUT int);
    entry hacerReclamo(id:in int);
  end;

  camiónArray: array 1..3 of camión;
  personaArray: array 1..P of persona;
  task body camión is 
    int idCliente=-1;
    begin
    loop
      administrador.camionLibre(idCliente);
      personaArray(idCliente).recolección();
    end loop;
  end camión;

  task body administrador is 
  arrayCant: array 1..P of int=0;//array inicializado en 0
  begin
    loop
      select
        accept hacerReclamo(id:IN int) do
          arrayCant[id]++;
        end hacerReclamo;
      or
        when(!arrayCant.isEmpty())->accept camionLibre(idCliente:OUT ind) do
          maxReclamos=arrayCant.max();//consigue el cliente con + reclamos
          idCliente=maxReclamos;
        end CamionLibre;
        arrayCant[idCliente]=0;//reinicio pq se atendió al cliente
      end select;
    end loop;
  end administrador;

  task body persona is
    int miId;
    contestado=true;
  begin
    accept recibirId(id:IN id) do
      miId=id
    end recibirId;
    loop(!contestado)
      administrador.hacerReclamo(id);
      select
        accept recolección(); 
          contestado=true;
      or delay 900;
      end select;
    end loop;
  end persona;

begin
  for i in 1..P
    personaArray(i).recibirId(i)
  endfor;
end recolección;