import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Sensor(
    private val id: Int,
    private val registro: Registro<Double>
) : Runnable {

    override fun run() {
        while (true) {
            try {
                val lectura = Random.nextDouble(1.0, 20.0)
                registro.almacenarLectura(lectura)
                println("🌡️ Sensor $id registró: $lectura °C")

                TimeUnit.SECONDS.sleep(2)
            } catch (e: Exception) {
                println("⚠️ Sensor $id interrumpido: ${e.message}")
                break
            }
        }
    }
}
