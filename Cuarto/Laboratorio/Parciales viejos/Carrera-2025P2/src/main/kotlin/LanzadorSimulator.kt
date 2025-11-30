import java.util.concurrent.Executors


    fun main(){
        val pilotos= listOf("Max Verstappen", "Sergio Pérez", "Franco Colapinto", "Fernando Alonso", "Charles Leclerc")
        val circuito=Circuito()
        val cantidadAutos=5;
        val capacidadPista=3;

        val simulacion= Simulacion.prepararSimulacion(circuito,cantidadAutos,pilotos)

        val executor = Executors.newFixedThreadPool(capacidadPista)

        simulacion.autos.map { executor.submit(it) }
        executor.shutdown()


    }
