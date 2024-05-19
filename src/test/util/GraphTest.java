package pr2;

import main.*;

public class GraphTest {

    private Graph<Integer> graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddVertex() {
        assertTrue(graph.addVertex(1), "El vértice 1 debería ser añadido.");
        assertFalse(graph.addVertex(1), "El vértice 1 no debería ser añadido nuevamente.");
        assertTrue(graph.containsVertex(1), "El grafo debería contener el vértice 1.");
    }

    @Test
    public void testAddEdge() {
        graph.addEdge(1, 2);
        assertTrue(graph.containsVertex(1), "El grafo debería contener el vértice 1.");
        assertTrue(graph.containsVertex(2), "El grafo debería contener el vértice 2.");
        assertTrue(graph.obtainAdjacents(1).contains(2), "El vértice 2 debería ser adyacente a 1.");
        assertTrue(graph.obtainAdjacents(2).contains(1), "El vértice 1 debería ser adyacente a 2.");
    }

    @Test
    public void testObtainAdjacents() throws Exception {
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        Set<Integer> adjacents = graph.obtainAdjacents(1);
        assertEquals(2, adjacents.size(), "El vértice 1 debería tener 2 adyacentes.");
        assertTrue(adjacents.contains(2), "El vértice 2 debería ser adyacente a 1.");
        assertTrue(adjacents.contains(3), "El vértice 3 debería ser adyacente a 1.");
    }

    @Test
    public void testContainsVertex() {
        assertFalse(graph.containsVertex(1), "El grafo no debería contener el vértice 1.");
        graph.addVertex(1);
        assertTrue(graph.containsVertex(1), "El grafo debería contener el vértice 1.");
    }

    @Test
    public void testToString() {
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        String graphString = graph.toString();
        assertTrue(graphString.contains("1: 2 3"), "La representación en cadena del grafo debería contener '1: 2 3'.");
        assertTrue(graphString.contains("2: 1"), "La representación en cadena del grafo debería contener '2: 1'.");
        assertTrue(graphString.contains("3: 1"), "La representación en cadena del grafo debería contener '3: 1'.");
    }

    @Test
    public void testShortestPath() {
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 4);

        List<Integer> expectedPath = Arrays.asList(1, 5, 4);
        List<Integer> actualPath = graph.shortestPath(1, 4);
        assertEquals(expectedPath, actualPath, "El camino más corto debería ser [1, 5, 4].");

        expectedPath = Arrays.asList(2, 3);
        actualPath = graph.shortestPath(2, 3);
        assertEquals(expectedPath, actualPath, "El camino más corto debería ser [2, 3].");

        assertNull(graph.shortestPath(1, 6), "Debería devolver null si no existe camino.");
    }
}
