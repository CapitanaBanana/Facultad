
Int total=0;
Int arreglo[0..m]
Int n=?
Process contador[id:0..x]
{
	For int i=0..m-1
	{
		If (arreglo[i]==n){
			<total++>
		}
	}
}
int cant = 0; int pri_ocupada = 0; int pri_vacia = 0; int buffer[N];


process productor[id:0..P] {
    while (true){
        // producir elemento
        <await (cant < N); cant++;
        buffer[pri_vacia] = elemento;
        pri_vacia = (pri_vacia + 1) mod N;>
    }
}

process consumidor[id:0..C]{ 
    while (true) { 
        <await (cant > 0); cant-- 
        elemento = buffer[pri];
        pri_ocupada = (pri_ocupada + 1) mod N;>
        //consume elemento
    }
}

Cola c[5]
process proceso[id:0..4]{ 
    while (true) { 
	<await Cola.lenght!=0; recurso= pop cola>
	//usar recurso
	<Push recurso>
       }
}

bool ocupada=false;
process persona[id:0..4]{ 
	<await (ocupada=false); ocupada=true;>
  imprimir(documento) 
  <ocupada=false>
}

//mucho más simple
process persona[id:0..4]{ 
  <imprimir(documento)>
}


cola cola[5]
siguiente=-1
process persona[id:0..4]{ 
	<if (siguiente==-1) siguiente=id 
    else push cola(id)>
  <await(siguiente==id)>
    imprimir(documento)
  <if (cola.lenght==0) siguiente=-1; 
    else siguiente=pop cola>
}

colaOrdenada cola[n] //cola en la que se encola según el id. 
siguiente=-1
process persona[id:0..4]{ 
	<if (siguiente==-1) siguiente=id 
    else push cola(id)>
  <await(siguiente==id)>
    imprimir(documento)
  <if (cola.lenght==0) siguiente=-1; 
    else siguiente=pop cola>
}

cola cola[n]
int siguiente=-1
boolean ocupada=false

process persona[id:0..n]{ 
  <push cola(id)>
  <await(siguiente==id), ocupada=true>
    imprimir(documento)
    ocupada=false
  
}

process coordinador[]{ 
   while (true) {
    <await (ocupada==false && cola.lenght > 0), siguiente = pop cola>
   }
}

int arribo[1:n]=([n]0), ejecutar[1:n]=([n]0)
process coordinador[]{ 
  while (true) {
  for [i=1..n]{
    while (arribo[i]==0) skip{//CONSULTAR, no sería un if???? sino es re estúpido porque se queda esperando a que sea 1, sin avanzar al siguiente número del for
      ejecutar[i]=1
      while (ejecutar[i]==1) skip{
        arribo[i]=0
      }
    }
  }
  }
}

process proceso[id:0..n]{ 
  while (true) { 
    arribo[i]=1
    while (ejecutar[i]==0) skip{
      Sección crítica
      ejecutar[i]=0
    }
  }
}
