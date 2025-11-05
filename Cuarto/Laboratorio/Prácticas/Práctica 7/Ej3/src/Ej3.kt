import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main(){
    val corredores=10;
    var ganador=-1;
    val anyLock = Any()
    var fin=false;
    val exec = Executors.newFixedThreadPool(3);
    for (i in 1..corredores){
    exec.execute {
        var j=1;
        while (j<=100 && !fin){
            println("El thread "+ i +" recorrió "+ j +" metros")
            if(j==100){
                synchronized(anyLock){ //si no hago esto no tengo exclusión mutua en fin y ganador
                if (!fin){
                fin=true
                ganador=i;
            }}}
            j++;
        }
    }

    }

    exec.shutdown() //terminarlo
    exec.awaitTermination(5, TimeUnit.SECONDS)//darle tiempo a que terminen todos
    println("el ganador es "+ ganador)
}