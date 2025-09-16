import java.util.Set;
import java.util.Collection;
public class HashSetAgregadosFix<E> extends SetWrapper<E> {

  private int cantidadAgregados = 0;

  public HashSetAgregadosFix(Set<E> set){
      super(set);
  }
  @Override
  public boolean add(E e) {
       cantidadAgregados++;
       return super.add(e);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    cantidadAgregados+= c.size();
    return super.addAll(c);
  }
    public int getCantidadAgregados() {
        return cantidadAgregados;
    }

}
