import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Auto implements Runnable {
    private String piloto;
    private int avance=0;
    private int total;

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public void setDetallesCarrera(int vueltas,int longitudVuelta){
        this.total=vueltas*longitudVuelta;
    }

    public void run() {
        //TODO:
        //Espera un determinado tiempo y avanza una
        //distancia de 10
        //al finalizar imprime el tiempo en segundos
        //que tarda en completar el circuito
        LocalDateTime inicio= LocalDateTime.now();
        while(this.avance<total){
            avance+=10;
            try{
                int random=ThreadLocalRandom.current().nextInt(1,30);
                if(random<5){
                    throw new MotorDescompuesto();
                }
                else if(random <10){
                    random=20000;
                }
                   TimeUnit.MILLISECONDS.sleep(random);

            } catch (MotorDescompuesto e){
                System.out.println("el piloto"+ this.piloto+ " abandono.");
            }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        }
        LocalDateTime fin= LocalDateTime.now();
        long duration = ChronoUnit.SECONDS.between(inicio,fin);
        System.out.println("el piloto"+ this.piloto+ " tardo: "+ String.valueOf(duration));
    }

    @Override
    public String toString() {
        return "Auto{" +
                "piloto='" + piloto + '\'' +
                ", avance=" + avance +
                ", total=" + total +
                '}';
    }
}