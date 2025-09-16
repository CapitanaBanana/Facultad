import java.util.Collection;
import java.util.Set;

public class HashSetEliminados<E> extends SetWrapper<E>{
    private int cantidadEliminados=0;
    public HashSetEliminados(Set<E> set){
        super(set);
    }
    @Override
    public boolean remove(Object o) {
        cantidadEliminados++;
        return super.remove(o);
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        cantidadEliminados+= c.size();
        return super.removeAll(c);
    }

    public int getCantidadEliminados() {
        return cantidadEliminados;
    }
}
