package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;

public class Mixta extends Topografia {
    private ArrayList<Topografia> lista;

    public Mixta(ArrayList<Topografia> lista) {
        this.lista = lista;
    }

    public double proporcionDeAgua() {
        return lista.stream().mapToDouble(parte -> parte.proporcionDeAgua()).sum() / 4;
    }

    @Override
    public boolean igual(Tierra tierra) {
        return false;
    }

    @Override
    public boolean igual(Agua agua) {
        return false;
    }

    @Override
    public boolean igual(Topografia otra) {
        if (otra instanceof Mixta) {
            return igual((Mixta) otra);
        }
        return false;
    }

    public boolean igual(Mixta mixta) {
        if (this.lista.size() != mixta.lista.size()) {
            return false;
        }

        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).igual(mixta.lista.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Topografia elemento(int i) {
        return lista.get(i);
    }
}
