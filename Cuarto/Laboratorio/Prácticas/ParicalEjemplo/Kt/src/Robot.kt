import java.util.concurrent.Executors

class Robot(val id:Int, val nombre:String, var bateria:Int) {

}
class MisionRobot(private val robot: Robot) : Runnable {
    override fun run() {
        try {
            println(">>> Robot ${robot.nombre} inicia misión con ${robot.bateria}% batería")

            while (robot.bateria > 0) {
                Thread.sleep(500)
                robot.bateria -= 10
                println("Robot ${robot.nombre} avanzando... batería: ${robot.bateria}%")

                if (robot.bateria <= 0) {
                    throw RuntimeException("Batería agotada")
                }
            }

            println("✅ Robot ${robot.nombre} completó la misión")

        } catch (e: Exception) {
            println("❌ Robot ${robot.nombre} falló: ${e.message}")
        }
    }

}
fun main(){
    val r1 = Robot(1, "Alpha", 50)
    val r2 = Robot(2, "Beta", 40)
    val r3 = Robot(3, "Gamma", 30)
    val r4 = Robot(4, "Delta", 60)
    val r5 = Robot(5, "Sigma", 80)

    val robots = listOf(r1, r2, r3, r4, r5)

    println("=== Pool de 3 robots ===")

    val executor = Executors.newFixedThreadPool(3)

    robots.forEach { executor.submit(MisionRobot(it)) }

    executor.shutdown()
}