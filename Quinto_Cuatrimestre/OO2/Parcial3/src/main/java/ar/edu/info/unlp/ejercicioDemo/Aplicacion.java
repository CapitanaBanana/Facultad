package ar.edu.info.unlp.ejercicioDemo;

public class Aplicacion {
    private HomeWeatherStationAdapter weatherStationAdapter;
    private Reporte reporte;

    public Aplicacion() {
        this.weatherStationAdapter = new HomeWeatherStationAdapter();
        this.reporte = new reporteConcreto();
    }

    public String generarReporte1() {
        DecoradorPresion decoradorPresion = new DecoradorPresion(reporte, weatherStationAdapter.presion);
        DecoradorRadiacion decoradorRadiacion = new DecoradorRadiacion(decoradorPresion,
                weatherStationAdapter.radiacion);
        DecoradorTemperatura decoradorTemperatura = new DecoradorTemperatura(decoradorRadiacion,
                weatherStationAdapter.temperatura);
        return decoradorTemperatura.generarReporte();
    }
}
