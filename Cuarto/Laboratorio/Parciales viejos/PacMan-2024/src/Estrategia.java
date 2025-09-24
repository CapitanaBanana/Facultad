public interface Estrategia {
    public void changeDirection(Ghost ghost, boolean mustChangeDirection);
}

public class Estratega{
    private Estratega(){}
    public static final Estrategia ZOMBIE  = new Zombie();
    public static final Estrategia OPUESTA = new Opuesta();
    public static final Estrategia RANDOM  = new Random();



    private static class Zombie implements Estrategia{

        @Override
        public void changeDirection(Ghost ghost, boolean mustChangeDirection) {
            ghost.move();
        }
    }
    private static class Opuesta implements Estrategia{
        @Override
        public void changeDirection(Ghost ghost, boolean mustChangeDirection) {
            if(mustChangeDirection){
                //moverse en la direccion opuesta
            }

        }

    }
    private static class Random implements Estrategia{
        @Override
        public void changeDirection(Ghost ghost, boolean mustChangeDirection) {
            if(mustChangeDirection){
                ghost.move();
            }

        }

    }
}
