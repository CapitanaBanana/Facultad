import java.util.*;

public class Diccionario {
    private Map<String, Set<String>> map = new HashMap<>();

    public Map<String, Set<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, Set<String>> map) {
        this.map = map;
    }

    public void tokenizar(String palabraOriginal){
        String palabra= '#'+palabraOriginal+'$';
        for (int i = 0; i < palabra.length() - 1; i++ ) {
            char char1 = palabra.charAt(i);
            char char2 = palabra.charAt(i + 1);
            String key = "".concat(String.valueOf(char1)).concat(String.valueOf(char2));
            if(map.containsKey(key)){
                Set<String> l=map.get(key);
                l.add(palabra);
                map.put(key, l);
            }
            else{
                Set<String> l= new TreeSet<>();
                l.add(palabraOriginal);
                map.put(key,l);
            }

        }
    }

}
