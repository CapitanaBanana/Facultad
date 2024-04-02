package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;

public class Empresa {
    private ArrayList<Empleado> empleados;
    public Empresa(){
        empleados=new ArrayList<Empleado>();
    }

    public String obtenerSalarios(){
        String s="";
        for (Empleado e: empleados){
            s= s + e.getNombre() + " Sueldo: " + e.getSueldo() + "\n";
        }
        return s;
    }
    public void agregarEmpleado(Empleado e){
        empleados.add(e);
    }
    
}
