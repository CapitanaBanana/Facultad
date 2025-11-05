import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class Corredor(private val id:Int): Callable<Pair<Int, String>>{ //podes hacer que retorne lo que quieras, yo quería que me de el id
    override fun call(): Pair<Int, String> {
        var metros=0
        var steps=0;
        while (metros<100){
            steps= Random.nextInt(1,20);
            if(steps==1){
                throw RuntimeException("Corredor $id sufrió una lesión")

            }
            else if(steps ==2){
                return Pair(id, "ABANDONA ")
            }
            else metros+=steps;
            Thread.sleep(50);
        }
        return Pair(id, "TERMINO ")
    }
    fun getId(): Int{
        return id;
    }

}
// excecute es para runnable, no devuelve nada.
fun main(){
    val corredores=10;
    var ganador=-1;
    val anyLock = Any()
    var fin=false;
    var lista=mutableListOf<Corredor>();
    for(i in 1..10){
        lista.add(Corredor(i));
    }

    val exec = Executors.newFixedThreadPool(3);
    val futuros= lista.map { exec.submit(it) }
    while(!fin){
    for (futuro in futuros){
        try {
            val (id, res)=futuro.get()//BLOQUEA a main
            if(res.contains("TERMINO") && !fin){
                ganador=id
                fin =true;
            }
        }catch (e: Exception){
            println("Error: "+e.message);
        }

    }
}
    exec.shutdown() //terminarlo
    exec.awaitTermination(5, TimeUnit.SECONDS)//darle tiempo a que terminen todos
    println("el ganador es "+ ganador)
}