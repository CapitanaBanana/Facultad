public class Main {
    public static void main(String[] args) {
        try {
            Contenedor contenedor = new Contenedor();
            contenedor.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}