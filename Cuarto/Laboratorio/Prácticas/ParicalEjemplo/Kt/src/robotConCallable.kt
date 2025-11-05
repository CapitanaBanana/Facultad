import java.util.concurrent.Callable
import java.util.concurrent.Executors

//callable retorna resultado!!
class MisionRobotCallable(private val robot: Robot) : Callable<String> {

    override fun call(): String {
        while (robot.bateria > 0) {
            Thread.sleep(500)
            robot.bateria -= 10
            println("Robot ${robot.nombre} batería: ${robot.bateria}%")
        }

        return if (robot.bateria > 0) "✅ Éxito" else "❌ Batería agotada"
    }
}
fun main() {
    val robots = listOf(
        Robot(1, "Alpha", 50),
        Robot(2, "Beta", 40),
        Robot(3, "Gamma", 30)
    )

    val executor = Executors.newFixedThreadPool(3)
    val futures = robots.map { executor.submit(MisionRobotCallable(it)) }

    futures.forEach { println("Resultado misión: ${it.get()}") }

    executor.shutdown()
}
