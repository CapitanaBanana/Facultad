package Ej8;

public class TestPiano {
    public static void main(String[] args) {
        Piano piano = new Piano();

        System.out.println(piano.queEs());

        piano.hacerSonar();

        piano.hacerSonar(Notas.DO, 3);
        piano.hacerSonar(Notas.SOL, 2);

        piano.afinar();

        piano.afinar(FrecuenciasDeLa.LA_440);
        piano.afinar(FrecuenciasDeLa.LA_446);
    }
}
