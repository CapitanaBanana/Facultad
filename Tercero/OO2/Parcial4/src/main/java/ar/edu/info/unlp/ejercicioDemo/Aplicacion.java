package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;

/**
 * De esta forma crearemos las clases del ejercicio
 *
 */
public class Aplicacion {

    private ArrayList<Tarea> tareas;

    public Aplicacion() {
        this.tareas = new ArrayList<Tarea>();
    }

    public void addTarea(Tarea tarea) {
        this.tareas.add(tarea);
    }

    public void CalcularAvance() {
        this.tareas.forEach(tarea -> System.out.println(tarea.CalcularAvance()));
    }

}
