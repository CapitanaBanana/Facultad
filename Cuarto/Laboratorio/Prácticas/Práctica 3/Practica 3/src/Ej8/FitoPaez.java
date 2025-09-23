package Ej8;

public enum FitoPaez {
    INSTANCE;
    private final Piano piano;
    FitoPaez(){
        this.piano=new Piano();
    }
    public Piano getPiano(){
        return piano;
    }
    public void tocarCancion(Notas[] notas, int[] tiempos) {
        if (notas.length != tiempos.length) {
            throw new IllegalArgumentException("La cantidad de notas y tiempos debe ser la misma");
        }

        System.out.println("Inicia cancion: ");

        for (int i = 0; i < notas.length; i++) {
            piano.hacerSonar(notas[i], tiempos[i]);
        }
    }

}
