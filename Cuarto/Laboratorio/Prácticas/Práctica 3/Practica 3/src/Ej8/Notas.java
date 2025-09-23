package Ej8;

public enum Notas {
    DO("C"),
    RE("D"),
    MI("E"),
    FA("F"),
    SOL("G"),
    LA("A"),
    SI("B");
    private String cif;
    Notas(String cif){
        this.cif=cif;
    }
    public String getCif(){
        return cif;
    }


}
