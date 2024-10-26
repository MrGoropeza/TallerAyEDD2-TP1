public class App {
    public static void main(String[] args) throws Exception {
        Grafo grafo = VuelosGoodFly();
        System.out.println(grafo);

        BFS bfs = new BFS(grafo, 0);
        bfs.ejecutar();
        System.out.println(bfs);

        EsAciclico esAciclico = new EsAciclico(grafo);
        esAciclico.esAciclico();

        Arbol arbol = new Arbol(1);
        var nodo1 = arbol.insertarNodo(arbol.getRaiz(), 2);
        var nodo2 = arbol.insertarNodo(arbol.getRaiz(), 3);
        var nodo3 = arbol.insertarNodo(nodo1, 4);
        var nodo4 = arbol.insertarNodo(nodo1, 5);
        arbol.insertarNodo(nodo2, 6);
        arbol.insertarNodo(nodo3, 7);
        arbol.insertarNodo(nodo4, 8);

        System.out.println(arbol);
        System.out.println("Recorrido Preorden:" + arbol.recorridoPreorden());
        System.out.println("Recorrido Postorden:" + arbol.recorridoPostorden());
    }

    private static Grafo VuelosGoodFly() {
        var vuelosString = "(d1,d2,200),(d1,d13,250),(d1,d9,290),(d2,d6,360),(d2,d3,190),(d3,d6,250),(d3,d5,190),(d3,d1,300),(d4,d3,180),(d5,d6,300),(d5,d10,400),(d6,d11,350),(d6,d12,300),(d7,d4,300),(d7,d3,250),(d7,d1,150),(d8,d7,200),(d8,d1,220),(d9,d8,180),(d9,d13,180),(d10,d4,200),(d11,d10,700),(d11,d5,200),(d12,d2,150),(d13,d12,100),(d13,d2,200)";

        var vuelos = vuelosString.split("(?<=\\)),(?=\\()");
        var grafo = new Grafo(13);

        for (String vuelo : vuelos) {
            vuelo = vuelo.replaceAll("\\)", "");
            vuelo = vuelo.replaceAll("\\(", "");
            var vueloSplit = vuelo.split(",");
            var origen = vueloSplit[0];
            var destino = vueloSplit[1];
            var peso = Integer.parseInt(vueloSplit[2]);

            var origenIndex = Integer.parseInt(origen.substring(1)) - 1;
            var destinoIndex = Integer.parseInt(destino.substring(1)) - 1;

            grafo.agregarArco(origenIndex, destinoIndex, peso);
        }

        return grafo;
    }

}
