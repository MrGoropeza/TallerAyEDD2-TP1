import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class EsAciclico {
    private Grafo grafo;

    public EsAciclico(Grafo grafo) {
        this.grafo = grafo;
    }

    public boolean esAciclico() {
        // Inicializa el conteo de grados de entrada para cada nodo
        Map<Integer, Integer> grados = new HashMap<>();
        for (int nodo : grafo.getListadoAdyacencia().keySet()) {
            grados.put(nodo, 0);
        }
        for (int nodo : grafo.getListadoAdyacencia().keySet()) {
            for (int vecino : grafo.getListadoAdyacencia().get(nodo)) {
                grados.put(vecino, grados.getOrDefault(vecino, 0) + 1);
            }
        }

        // Cola para almacenar nodos con grado de entrada 0
        Queue<Integer> cola = new LinkedList<>();
        for (int nodo : grados.keySet()) {
            if (grados.get(nodo) > 0)
                continue;

            cola.add(nodo);

        }

        // Lista para almacenar el orden topológico
        List<Integer> ordenTopologico = new ArrayList<>();

        // Procesa cada nodo con grado de entrada 0
        while (!cola.isEmpty()) {
            int nodoActual = cola.poll();
            ordenTopologico.add(nodoActual);

            for (int vecino : grafo.getListadoAdyacencia().getOrDefault(nodoActual, new ArrayList<>())) {
                grados.put(vecino, grados.get(vecino) - 1);

                if (grados.get(vecino) > 0)
                    continue;

                cola.add(vecino);

            }
        }

        // Verifica si el orden topológico incluye todos los nodos del grafo
        if (ordenTopologico.size() == grafo.getListadoAdyacencia().size()) {
            System.out.println("El grafo es acíclico. Orden topológico: " + ordenTopologico);
            return true;
        } else {
            System.out.println("El grafo contiene ciclos y no es acíclico.");
            return false;
        }
    }
}