public class Alumno implements Comparable<Alumno>{
    private String nombre;
    private String apellido;
    private int legajo;
    private int dni;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Alumno(int legajo, String apellido, String nombre, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", legajo=" + legajo +
                ", dni=" + dni +
                '}';
    }

//    @Override
//    public int compareTo(Alumno o) {
//
//        return Integer.compare(this.legajo, o.legajo);
////        if(this.legajo> o.legajo){
////            return 1;
////        }
////        else if(this.legajo< o.legajo){
////            return -1;
////        }
////        else return 0;
//
//    }

    public int compareTo(Alumno o){
        int resApellidos= this.apellido.compareTo(o.apellido);
        if(resApellidos==0){
            return this.nombre.compareTo(o.nombre);
        }
        return resApellidos;
    }
}
