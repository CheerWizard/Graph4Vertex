import models.Edge;
import models.Graph;
import models.Vertex;
import utils.GraphUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Launcher {

    public static void main(String [] args) {
        final List<Vertex> testVertices = new ArrayList<>();
        final List<Edge> testEdges = new ArrayList<>();
        //create test vertices
        final Vertex bob = new Vertex( "Bob");
        final Vertex alice = new Vertex("Alice");
        final Vertex mark = new Vertex("Mark");
        final Vertex rob = new Vertex("Rob");
        final Vertex maria = new Vertex("Maria");
        //add test vertices
        testVertices.add(bob);
        testVertices.add(alice);
        testVertices.add(mark);
        testVertices.add(rob);
        testVertices.add(maria);
        //add test edges
        testEdges.add(new Edge(bob , alice));
        testEdges.add(new Edge(bob , rob));
        testEdges.add(new Edge(alice , mark));
        testEdges.add(new Edge(rob , mark));
        testEdges.add(new Edge(alice , maria));
        testEdges.add(new Edge(rob , maria));
        //ask graph builder to build and ask graph utility to search
        final Graph graph = Graph.Builder.getInstance()
                .setVertexes(testVertices.toArray(new Vertex[]{}))
                .setEdges(testEdges.toArray(new Edge[]{}))
                .build();
        //test deep search
        testDeepSearch(graph , bob);
        //test search in width
        testWidthSearch(graph , bob);
    }

    private static void testDeepSearch(Graph graph , Vertex rootVertex) {
        final Set<Vertex> vertexSet = GraphUtility.searchInDeep(graph, rootVertex);
        vertexSet.forEach(vertex -> System.out.println(vertex.hashCode()));
    }

    private static void testWidthSearch(Graph graph , Vertex rootVertex) {
        final Set<Vertex> vertexSet = GraphUtility.searchInWidth(graph, rootVertex);
        vertexSet.forEach(vertex -> System.out.println(vertex.hashCode()));
    }
}
