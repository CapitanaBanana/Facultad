//PRÁCTICA 4PMA

/* 1. Suponga que existe un antivirus distribuido que se compone de R procesos robots Examinadores y 1 proceso Analizador. Los procesos Examinadores están buscando continuamente posibles sitios web infectados; cada vez que encuentran uno avisan la dirección y luego continúan buscando. El proceso Analizador se encarga de hacer todas las pruebas necesarias con cada uno de los sitios encontrados por los robots para determinar si están o no infectados.  
a) Analice el problema y defina qué procesos, recursos y comunicaciones serán necesarios/convenientes para resolverlo.  
b) Implemente una solución con PMS sin tener en cuenta el orden de los pedidos. */
Process examinador[id:0..r]{
  webInfectada w;
  while (true){
    w=BuscarPaginaInfectada;
    Buffer!pagina(w, id)
  }
}

Process analizador{
  webInfectada w;
  while(true){
    buffer!pedido();
    buffer?pagina(w);
    //analizar w
  }
}

Process buffer{
  webInfectada w;
  Cola cola;
  do examinador[*]?pagina(w)->cola.push(w);
  [] not empty(cola); analizador?pedido()->analizador!pagina(cola.pop())
}

/* c) Modifique el inciso (b) para que el Analizador resuelva los pedidos en el orden 
en que se hicieron. 
Ya se hace ordenado
*/

Process examinador[id:0..r]{
  webInfectada w;
  while (true){
    w=BuscarPaginaInfectada;
    Buffer!pagina(w)
  }
}

Process analizador{
  webInfectada w;
  while(true){
    buffer!pedido();
    buffer?pagina(w);
    //analizar w
  }
}

Process buffer{
  webInfectada w;
  Cola cola;
  do examinador?pagina(w)->cola.push(w);
  [] not empty(cola); analizador?pedido()->analizador!pagina(cola.pop())
}

/* 2. En un laboratorio de genética veterinaria hay 3 empleados. El primero de ellos continuamente prepara las muestras de ADN; cada vez que termina, se la envía al segundo empleado y vuelve a su trabajo. El segundo empleado toma cada muestra de ADN preparada, arma el set de análisis que se deben realizar con ella y espera el resultado para archivarlo. Por último, el tercer empleado se encarga de realizar el análisis y devolverle el  resultado al segundo empleado.  */
2-
Process preparador{
  muestra m;
  while true{
    //preparar muestra;
    buffer1!preparada(m);
  }
}
process buffer1{
  cola c;
  muestra m;
  do preparador?preparada(m)-> cola.push(m);
  []!cola.isEmpty(); armador?siguiente() -> armador!muestra(m);
  od
}

Process armador{
  muestra m;
  while true{
    buffer1!siguiente(true);
    buffer1?muestra(m);
    //armado set
    analizador!muestra(m);
    analizador?resultado(resultado)
    //archivar
  }
}

Process analizador{
  while true{
    armador?muestra(m);
    //hace cositas(resultado)
    armador!resultado(resultado)
  }
}
//solucion 1 en la que el armador no hace nada entre que manda una cosa y recibe el resultado, no tiene sentido pero siento que es lo que dice la consigna
Process preparador{
  muestra m;
  while true{
    //preparar muestra;
    buffer1!preparada(m);
  }
}
process buffer1{
  cola c;
  muestra m;
  do preparador?preparada(m)-> cola.push(m);
  []!cola.isEmpty(); armador?siguiente() -> armador!muestra(m);
  od
}

Process armador{
  muestra m;
  while true{
    do
    buffer1!siguiente(true);
    buffer1?muestra(m);
    //armado set
    //manda al analizador
    buffer2!muestra(m, id);
    ------------------------------
    //recibe resultado
    buffer3!siguiente(true)
    buffer3?resultado(resultado,id)
    //archivar
    od
  }
}
process buffer2{
  cola c;
  muestra m;
  idMuestra id;
  do armador?muestra(m,id)-> cola.push(m,id);
  []!cola.isEmpty(); analizador?siguiente() -> analizador!muestra(m,id);
  od
}
process buffer3{
  cola c;
  muestra m;
  idMuestra id;
  do analizador?resultado(resultado,id)-> cola.push(resultado,id);
  []!cola.isEmpty(); armador?siguiente() -> armador!resultado(resultado,id);
  od
}


Process analizador{
  while true{
    buffer2!siguiente(true);
    buffer2?muestra(m,id);
    //hace cositas(resultado)
    buffer3!resultado(resultado,id)
  }
}
//me cabe, no se puede hacer esto. Necesitaría ver cómo implementar el do

/* 3. En un examen final hay N alumnos y P profesores. Cada alumno resuelve su examen, lo entrega y espera a que alguno de los profesores lo corrija y le indique la nota. Los profesores corrigen los exámenes respetando el orden en que los alumnos van entregando.  
a) Considerando que P=1. 
*/
3a-
process alumno[id:0..N]{
  int nota
  //rendir
  profesor!entregar(parcial, id)
  profesor[*]?nota(nota)
}

process profesor{
  while(true){
    alumno?entregar(parcial, id)
    //corregir(nota)
    alumno[id]!nota(nota)
  }
}
/* b) Considerando que P>1.  */
3b-
process alumno[id:0..N]{
  int nota
  //rendir
  coordinador!entregar(parcial, id)
  profesor[*]?nota(nota)
}

process coordinador{
  cola (pacial, id);
  do alumno[*]?entregar(parcial, id)-> cola.push(parcial,id)
  [] if(cola.lenght>0);profesor[*]?pedir(id) -> profesor[id]!darExamen(cola.pop());
  od
}

process profesor[id:0..P]{
  while(true){
    coordinador!pedir(id)
    coordinador?darExamen(parcial,id)
    //corregir(nota)
    alumno[id]!nota(nota)
  }
}


/* c) Ídem b) pero considerando que los alumnos no comienzan a realizar su examen hasta que todos hayan llegado al aula. Nota: maximizar la concurrencia; no generar demora innecesaria; todos los procesos deben terminar su ejecución  */
process alumno[id:0..N]{
  int nota
  coordinador!llegada(id)
  coordinador?arrancar()
  //rendir

  coordinador!entregar(parcial, id)
  profesor[*]?nota(nota)
}

process coordinador{
  cola (pacial, id);
  cant=0;
  do (cant<n); alumno[*]?llegada(id) od
  //llegaron todos
  for(int i=0; i<n;i++){
    alumno[i]!arrancar(true)
  }
  do alumno[*]?entregar(parcial, id)-> cola.push(parcial,id)
  [] if(cola.lenght>0);profesor[*]?pedir(id) -> profesor[id]!darExamen(cola.pop());
  od
}

process profesor[id:0..P]{
  while(true){
    coordinador!pedir(id)
    coordinador?darExamen(parcial,id)
    //corregir(nota)
    alumno[id]!nota(nota)
  }
}

/* 4. En una exposición aeronáutica hay un simulador de vuelo (que debe ser usado con exclusión mutua) y un empleado encargado de administrar su uso. Hay P personas que esperan a que el empleado lo deje acceder al simulador, lo usa por un rato y se retira. 
a) Implemente una solución donde el empleado sólo se ocupa de garantizar la exclusión mutua. */ 
4a-
process persona[0..n]{
  empleado!llegada(id)
  empelado?pasar()
  //usar simulador
  empelado?salir()
}

process empleado{
  cola c;
  libre=true;
  while true{
    do persona[*]?llegada(id)-> {
      if(libre){
        libre=false
        persona[id].pasar();
      }
      else{
        c.push(id);
      }
    }
    [] persona[*]?salir(id)->{
      if(c.isEmpty){
        libre=true
      }
      else{
        persona[c.pop()]!pasar();
      }
    }
    od
  }
}

/* b) Modifique la solución anterior para que el empleado considere el orden de llegada para dar acceso al simulador. 
Nota: cada persona usa sólo una vez el simulador.    */
4b-
process persona[0..n]{
  persona!llegada(id)
  empelado?pasar()
  //usar simulador
  empelado?salir()
}

process empleado{
  cola c;
  libre=true;
  while true{
    buffer!siguiente(id);
    buffer?proximoCliente(id);
    cliente[id]!pasar();
    cliente[id]?salir();
    }
    
  }

process buffer{
  cola c;
  do persona[*]?llegada(id)->c.push(id);
  [] !c.isEmpty(); empelado?siguiente-> empleado!proximoCliente(id);
}

/* 5. En un estadio de fútbol hay una máquina expendedora de gaseosas que debe ser usada por  E Espectadores de acuerdo con el orden de llegada. Cuando el espectador accede a la máquina en su turno usa la máquina y luego se retira para dejar al siguiente. 
Nota: cada Espectador una sólo una vez la máquina. */

process espectador[0..n]{
  administrador!llegada(id) 
  //usar
  administrador!termino(id)
}
process administrador{
  cola c;
  bool libre=true;
    do espectador[*]?llegada(id) ->{
      if(libre==true){
        libre==false;
        espectador[id]!usar()
      }
      else{
        cola.push(id);
      }
    }
    [] espectador[*]?termino(id)->{
      if(cola.lenght>0){
        espectador[cola.push]!usar()
      }
      else{
        libre=true;
      }
    }
}
//yo siento que estop no asegura el orden pq mientras se está haciendo lo de abajo pueden llegar muchas personas y se van a recibir en cualquier orden pero idk