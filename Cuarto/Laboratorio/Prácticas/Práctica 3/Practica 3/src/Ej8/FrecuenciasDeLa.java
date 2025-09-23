package Ej8;

public enum FrecuenciasDeLa {
    LA_440(440, "Organización Internacional de Estandarización ISO 16."),
    LA_444(444, "Afinación de cámara"),
    LA_446(446, "Renacimiento"),
    LA_480(480, "Órganos alemanes que tocaba Bach");

    private final int frecuencia;
    private final String descripcion;

    FrecuenciasDeLa(int frecuencia, String descripcion) {
        this.frecuencia = frecuencia;
        this.descripcion = descripcion;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public String getDescripcion() {
        return descripcion;
    }
}