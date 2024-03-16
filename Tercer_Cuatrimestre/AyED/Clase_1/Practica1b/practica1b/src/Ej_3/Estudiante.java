package Ej_3;

public class Estudiante {
    private String nombre;
    private String apellido;
    private int comision;
    private String email;
    private String direccion;


    public Estudiante(String unnombre, String unapellido, int unacomision, String unemail, String unadireccion) {
        this.nombre = unnombre;
        this.apellido = unapellido;
        this.comision = unacomision;
        this.email = unemail;
        this.direccion = unadireccion;
    }

    public String tusDatos(){
        return(getNombre()+", "+ getApellido()+". comi: "+ getComision()+". email: "+getEmail()+ ". direccion: "+getDireccion());
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

    public int getComision() {
        return this.comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
