fun main() {
    val registro = Registro<Double>()

    // Creamos 3 sensores
    val sensores = List(3) { i -> Sensor(i + 1, registro) }

    // Iniciar cada sensor en un hilo
    sensores.forEach { sensor ->
        Thread(sensor).start()
    }

    // Dejar que corran 10 segundos
    Thread.sleep(10_000)

    println("\n📊 Total de lecturas: ${registro.getLecturas().size}")
}
//import java.util.concurrent.Executors
//
//fun main() {
//    val registro = Registro<Double>()
//    val pool = Executors.newFixedThreadPool(3)
//
//    repeat(3) { i ->
//        pool.submit(Sensor(i + 1, registro))
//    }
//
//    Thread.sleep(10_000)
//    pool.shutdownNow()
//
//    println("\n📊 Total de lecturas: ${registro.getLecturas().size}")
//}
