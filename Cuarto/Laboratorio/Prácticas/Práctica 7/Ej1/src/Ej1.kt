import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlinx.coroutines.*

//fun main(args: Array<String>) {
//    for (i in 1..10){
//        println("son las:" + LocalDateTime.now())
//        TimeUnit.SECONDS.sleep(1)
//    }
//}

fun main(args: Array<String>) {
    val t= thread {
        for (i in 1..10){
            println("son las:" + LocalDateTime.now())
            TimeUnit.SECONDS.sleep(1)
        }
    }
}

fun main() = runBlocking {
    repeat(10) {
        println("Hora: ${LocalDateTime.now()}")
        delay(1000)
    }
}

