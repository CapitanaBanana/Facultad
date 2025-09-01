import java.util.Collection;
import java.util.HashSet;

public class HashSetAgregados<E> extends HashSet<E> {
  private int cantidadAgregados = 0;

  public HashSetAgregados() {
  }

  public HashSetAgregados(int initCap, float loadFactor) {
    super(initCap, loadFactor);
  }

  @Override
  public boolean add(E e) {
    cantidadAgregados++;
    return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    cantidadAgregados += c.size();
    return super.addAll(c);
  }

  public int getCantidadAgregados() {
    return cantidadAgregados;
  }
}