package Ej8;

public class Piano implements InstrumentoMusical {

    @Override
    public void hacerSonar() {
        System.out.println("El piano está sonando");
    }

    @Override
    public void hacerSonar(Notas n, int duracion) {
        System.out.println("El piano toca la nota " + n
                + " (" + n.getCif() + ") durante "
                + duracion + " segundos");
    }

    @Override
    public String queEs() {
        return "Soy un Piano";
    }

    @Override
    public void afinar() {
        System.out.println("Afinando el piano de manera general...");
    }

    @Override
    public void afinar(FrecuenciasDeLa f) {
        System.out.println("Afinando el piano en "
                + f.getFrecuencia() + " Hz ("
                + f.getDescripcion() + ")");
    }
}
