namespace Clase_4;
class Hora{
    private int _horas;
    private int _minutos;
    private double _segundos;

    public Hora(int horas, int minutos, double segundos){
        _horas=horas;
        _minutos=minutos;
        _segundos=segundos;
    }
    public void Imprimir(){
        Console.WriteLine($"{_horas} horas, {_minutos} minutos, {_segundos} segundos");
    }
    public Hora(double h){
        _horas=(int)h;
        double min= (h-_horas)*60;
        _minutos=(int)min;
        double sec= (min-_minutos)*60;
        _segundos= Math.Round(sec,3);
        if (Math.Round(_segundos)==60){
            _segundos=0;
            _minutos++;
        }
    }
}