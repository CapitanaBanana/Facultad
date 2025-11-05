class AnalisisMeteorologico {

    @Analizador
    fun promedio(lista: List<Double>): Double {
        return lista.average()
    }

    @Analizador
    fun temperaturaMaxima(lista: List<Double>): Double {
        return lista.maxOrNull() ?: Double.NaN
    }

    @Analizador
    fun alertaCalor(lista: List<Double>): String {
        return if (lista.average() > 30) "altas"
        else "normal"
    }
}
