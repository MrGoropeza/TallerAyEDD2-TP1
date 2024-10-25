import java.util.Arrays;

public class Grafo {
    private int vertices;
    private int[][] matrizAdyacencia;

    // Inicializar grafo con su matriz de adyacencia de tamaño (vertices x vertices)
    public Grafo(int vertices) {
        this.vertices = vertices;
        matrizAdyacencia = new int[vertices][vertices];
        for (int[] fila : matrizAdyacencia) {
            Arrays.fill(fila, 0);
        }
    }

    public int getVertices() {
        return vertices;
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
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
            matrizAdyacencia[origen][destino] = peso;
            matrizAdyacencia[destino][origen] = peso;
        } else {
            matrizAdyacencia[origen][destino] = peso;
        }
    }
}
