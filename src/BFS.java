import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS {
    private Grafo grafo;
    private int origen;
    // Map para almacenar la distancia mínima desde el nodo inicial a cada nodo
    private Map<Integer, Integer> distancias = new HashMap<>();
    // Lista para almacenar el recorrido del algoritmo
    private List<Integer> recorrido = new ArrayList<>();
    // Map para almacenar el predecesor del nodo actual en el recorrido
    private Map<Integer, Integer> predecesores = new HashMap<>();

    public BFS(Grafo grafo, int origen) {
        this.grafo = grafo;
        this.origen = origen;
    }

    public void ejecutar() {
        var matriz = grafo.getMatrizAdyacencia();

        if (matriz == null || matriz.length == 0) {
            System.out.println("Matriz de adyacencia vacía");
            return;
        }

        // Cola para manejar los nodos que visitaremos
        Queue<Integer> cola = new LinkedList<>();

        // Iniciamos la cola con el nodo inicial y establecemos su distancia a 0
        cola.add(origen);
        distancias.put(origen, 0); // El nodo inicial tiene distancia 0

        // Mientras la cola no esté vacía
        while (!cola.isEmpty()) {
            var nodoActual = cola.poll(); // Extraemos el primer nodo de la cola
            recorrido.add(nodoActual); // Añadimos el nodo a la lista de recorrido

            // Iteramos sobre todos los vecinos del nodo actual
            for (int i = 0; i < matriz[nodoActual].length; i++) {
                // Si el nodo actual no está conectado al vecino, lo ignoramos
                if (matriz[nodoActual][i] == 0)
                    continue;

                // Si el vecino ya fue visitado, lo ignoramos
                if (distancias.containsKey(i))
                    continue;

                // Actualizamos la distancia del vecino
                distancias.put(i, distancias.get(nodoActual) + 1);
                // Añadimos el predecesor del vecino al nodo actual
                predecesores.put(i, nodoActual);
                // Añadimos el vecino a la cola para seguir explorando
                cola.add(i);
            }
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Distancias:\n");

        for (Map.Entry<Integer, Integer> entry : distancias.entrySet()) {
            sb.append("Al Destino " + (entry.getKey() + 1) + ": " + entry.getValue() + ", camino: "
                    + mostrarCamino(entry.getKey()) + "\n");
        }

        sb.append("Recorrido del algoritmo: ");
        for (Integer nodo : recorrido) {
            sb.append((nodo + 1) + " ");
        }

        return sb.toString();
    }

    public String mostrarCamino(int destino) {
        if (!distancias.containsKey(destino)) {
            return "[]";
        }

        List<Integer> camino = new ArrayList<>();

        for (Integer nodo = destino; nodo != null; nodo = predecesores.get(nodo)) {
            camino.add(nodo + 1);
        }

        Collections.reverse(camino);

        return camino.toString().replaceAll(",", " ->");
    }
}
