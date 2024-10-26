import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {
    private Grafo grafo;
    private int origen;
    private Map<Integer, Integer> distancias;
    private Map<Integer, Integer> predecesores;

    public Dijkstra(Grafo grafo, int origen) {
        this.grafo = grafo;
        this.origen = origen;
        this.distancias = new HashMap<>();
        this.predecesores = new HashMap<>();
    }

    public void ejecutar() {
        int n = grafo.getMatrizAdyacencia().length;
        boolean[] visitados = new boolean[n];
        Arrays.fill(visitados, false);

        for (int i = 0; i < n; i++) {
            distancias.put(i, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);

        for (int i = 0; i < n - 1; i++) {
            int u = seleccionarMinimoDistancia(visitados);
            visitados[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitados[v] && grafo.getMatrizAdyacencia()[u][v] != 0 && distancias.get(u) != Integer.MAX_VALUE) {
                    int nuevaDistancia = distancias.get(u) + grafo.getMatrizAdyacencia()[u][v];
                    if (nuevaDistancia < distancias.get(v)) {
                        distancias.put(v, nuevaDistancia);
                        predecesores.put(v, u); // Guardamos el predecesor
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origen D").append(origen + 1).append(":\n");

        for (int destino : distancias.keySet()) {
            if (destino == origen)
                continue; // No mostramos el origen como destino
            int distancia = distancias.get(destino);
            List<Integer> camino = obtenerCamino(destino);

            sb.append("Destino ").append(destino + 1).append(": ");
            if (distancia == Integer.MAX_VALUE) {
                sb.append("inaccesible\n");
            } else {
                sb.append(distancia).append(", camino: ");
                // sb.append(camino.stream().map(Object::toString).collect(Collectors.joining("
                // -> ")));
                for (int i = 0; i < camino.size(); i++) {
                    sb.append(camino.get(i) + 1);
                    if (i < camino.size() - 1) {
                        sb.append(" -> ");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // Método para obtener el camino más corto desde el origen hasta el destino
    public List<Integer> obtenerCamino(int destino) {
        List<Integer> camino = new ArrayList<>();
        Integer actual = destino;

        while (actual != null && actual != origen) {
            camino.add(actual);
            actual = predecesores.get(actual); // Seguimos el predecesor hasta el origen
        }
        if (actual == null) {
            return Collections.emptyList(); // No hay camino
        }
        camino.add(origen);
        Collections.reverse(camino);
        return camino;
    }

    private int seleccionarMinimoDistancia(boolean[] visitados) {
        int minDistancia = Integer.MAX_VALUE;
        int minIndice = -1;

        for (int i : distancias.keySet()) {
            if (!visitados[i] && distancias.get(i) <= minDistancia) {
                minDistancia = distancias.get(i);
                minIndice = i;
            }
        }
        return minIndice;
    }

}
