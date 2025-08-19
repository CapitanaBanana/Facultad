package Ej_3;

public class Profesor {
    private String nombre;
    private String apellido;
    private int catedra;
    private String email;
    private String facultad;


    public Profesor(String unnombre, String unapellido, int unacatedra, String unemail, String unafacultad) {
        this.nombre = unnombre;
        this.apellido = unapellido;
        this.catedra = unacatedra;
        this.email = unemail;
        this.facultad = unafacultad;
    }


    public String tusDatos(){
        return(getNombre()+", "+ getApellido()+". comi: "+ getCatedra()+". email: "+getEmail()+ ". direccion: "+getFacultad());
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCatedra() {
        return this.catedra;
    }

    public void setCatedra(int catedra) {
        this.catedra = catedra;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacultad() {
        return this.facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }
}
