import java.util.*;

public class Motor {

    /**
     * no tengo idea de que esta pasando acá, se lo pedí a chatgpt por las dudas nomás
     * Busca palabras similares a la palabra indicada,
     * según la distancia de Levenshtein < 3.
     */
    public Collection<String> buscarSimilares(Map<String, Set<String>> diccionario, String palabra) {
        // 1️⃣ Obtener los 2-gramas de la palabra
        List<String> gramas = obtenerGramas(palabra);

        // 2️⃣ Recolectar candidatas del diccionario
        Set<String> candidatas = new HashSet<>();
        for (String grama : gramas) {
            Set<String> palabras = diccionario.get(grama);
            if (palabras != null) {
                candidatas.addAll(palabras);
            }
        }

        // 3️⃣ Filtrar candidatas según la distancia de Levenshtein
        Set<String> resultado = new TreeSet<>();
        for (String candidata : candidatas) {
            int distancia = distanciaLevenshtein(palabra, candidata);
            if (distancia < 3) {
                resultado.add(candidata);
            }
        }

        return resultado;
    }

    /**
     * Genera los Q-gramas (Q=2) de una palabra
     */
    private List<String> obtenerGramas(String palabra) {
        List<String> gramas = new ArrayList<>();
        String extendida = "#" + palabra + "$";
        for (int i = 0; i < extendida.length() - 1; i++) {
            gramas.add(extendida.substring(i, i + 2));
        }
        return gramas;
    }

    /**
     * Calcula la distancia de Levenshtein entre dos cadenas
     */
    private int distanciaLevenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        // Inicializar primera fila y columna
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        // Llenar la matriz
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int costo = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + costo
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}