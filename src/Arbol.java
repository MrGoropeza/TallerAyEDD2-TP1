import java.util.ArrayList;
import java.util.List;

public class Arbol {
    public class NodoArbol {
        private int valor;
        private List<NodoArbol> nodos = new ArrayList<>();

        public NodoArbol(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }

        public List<NodoArbol> getNodos() {
            return nodos;
        }

        public NodoArbol insertarNodo(NodoArbol nodo) {
            nodos.add(nodo);
            return nodo;
        }
    }

    private NodoArbol raiz;

    public Arbol(int valorRaiz) {
        raiz = new NodoArbol(valorRaiz);
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public NodoArbol insertarNodo(NodoArbol padre, int valor) {
        NodoArbol nuevoNodo = new NodoArbol(valor);
        padre.insertarNodo(nuevoNodo);
        return nuevoNodo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ToString(raiz, sb, 0);
        return sb.toString();
    }

    // Recorrido en preorden (raíz, luego hijos)
    public List<Integer> recorridoPreorden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoPreorden(raiz, resultado);
        return resultado;
    }

    // Recorrido en postorden (hijos, luego raíz)
    public List<Integer> recorridoPostorden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoPostorden(raiz, resultado);
        return resultado;
    }

    private void ToString(NodoArbol nodo, StringBuilder sb, int nivel) {
        if (nodo == null)
            return;

        sb.append("  ".repeat(nivel)).append(nodo.getValor()).append("\n");

        for (NodoArbol hijo : nodo.getNodos()) {
            ToString(hijo, sb, nivel + 1);
        }
    }

    private void recorridoPreorden(NodoArbol nodo, List<Integer> resultado) {
        if (nodo == null)
            return;

        resultado.add(nodo.getValor()); // Visita el nodo actual
        for (NodoArbol hijo : nodo.getNodos()) {
            recorridoPreorden(hijo, resultado);
        }
    }

    private void recorridoPostorden(NodoArbol nodo, List<Integer> resultado) {
        if (nodo == null)
            return;

        for (NodoArbol hijo : nodo.getNodos()) {
            recorridoPostorden(hijo, resultado);
        }
        resultado.add(nodo.getValor()); // Visita el nodo actual
    }
}
