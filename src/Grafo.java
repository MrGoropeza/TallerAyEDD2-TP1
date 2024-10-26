import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private int vertices;
    private int[][] matrizAdyacencia;
    private Map<Integer, List<Integer>> listadoAdyacencia;

    // Inicializar grafo con su matriz de adyacencia de tamaño (vertices x vertices)
    public Grafo(int vertices) {
        this.vertices = vertices;
        matrizAdyacencia = new int[vertices][vertices];
        for (int[] fila : matrizAdyacencia) {
            Arrays.fill(fila, 0);
        }

        listadoAdyacencia = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            listadoAdyacencia.put(i, new ArrayList<>());
        }
    }

    public int getVertices() {
        return vertices;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public Map<Integer, List<Integer>> getListadoAdyacencia() {
        return listadoAdyacencia;
    }

    // Agregar una arco a la matriz de adyacencia
    public void agregarArco(int origen, int destino, int peso) {
        agregarArco(origen, destino, peso, false);
    }

    // Agregar una arco a la matriz de adyacencia de formato no dirigido
    public void agregarArcoNoDirigido(int origen, int destino, int peso) {
        agregarArco(origen, destino, peso, true);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("Matriz de adyacencia:\n");

        for (int[] fila : matrizAdyacencia) {
            sb.append(Arrays.toString(fila));
            sb.append("\n");
        }

        return sb.toString();
    }

    private void agregarArco(int origen, int destino, int peso, boolean noDirigido) {
        if (noDirigido) {
            // Agregar arco no dirigido a la matriz
            matrizAdyacencia[origen][destino] = peso;
            matrizAdyacencia[destino][origen] = peso;

            // Agregar arco dirigido a la lista de adyacencia
            listadoAdyacencia.get(origen).add(destino);
            listadoAdyacencia.get(destino).add(origen);
        } else {
            // Agregar arco dirigido a la matriz
            matrizAdyacencia[origen][destino] = peso;

            // Agregar arco dirigido a la lista de adyacencia
            listadoAdyacencia.get(origen).add(destino);
        }
    }
}
