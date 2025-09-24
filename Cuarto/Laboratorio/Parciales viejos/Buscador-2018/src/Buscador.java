import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Buscador {
  private List<Pelicula> peliculas = new ArrayList<Pelicula>();

  public List<Pelicula> seleccionar(Genero g, Edad e, RangosValoracion valoracion, RangosReproducciones reproduccion,
      Comparator<Pelicula> comp) {
    List<Pelicula> pelis = filtrar(g, e, valoracion, reproduccion);
    pelis.sort(comp);
    return pelis;
  }

  private List<Pelicula> filtrar(Genero g, Edad e, RangosValoracion valoracion, RangosReproducciones reproduccion) {
    List<Pelicula> pelis = new ArrayList<>();
    for (Pelicula pelicula : peliculas) {
      if (reproduccion.contiene(pelicula.getVistas()) && valoracion.contiene(pelicula.getPuntaje())
          && pelicula.getGenero().contains(g) && e == pelicula.getEdad()) {
        pelis.add(pelicula);
      }
    }
    return pelis;
  }

  static class OrdenarPorValoracion implements Comparator<Pelicula> {
    public static final OrdenarPorValoracion INSTANCE = new OrdenarPorValoracion();

    @Override
    public int compare(Pelicula o1, Pelicula o2) {
      return Double.compare(o1.getPuntaje(), o2.getPuntaje());
    }

  }

  static class OrdenarPorReproducciones implements Comparator<Pelicula> {
    public static final OrdenarPorReproducciones INSTANCE = new OrdenarPorReproducciones();

    @Override
    public int compare(Pelicula o1, Pelicula o2) {
      return Double.compare(o1.getVistas(), o2.getVistas());
    }
  }

  static class OrdenarPorEdad implements Comparator<Pelicula> {
    public static final OrdenarPorEdad INSTANCE = new OrdenarPorEdad();

    @Override
    public int compare(Pelicula o1, Pelicula o2) {
      return Integer.compare(o1.getEdad().minEdad(), o2.getEdad().minEdad());
    }

  }

}
