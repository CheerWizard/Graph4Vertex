package models;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private Map<Vertex , List<Vertex>> graphMap;

    private Graph(Map<Vertex, List<Vertex>> graphMap) {
        this.graphMap = graphMap;
    }

    private Graph() {
        graphMap = new HashMap<>();
    }

    public Map<Vertex, List<Vertex>> getGraphMap() {
        return graphMap;
    }

    public List<Vertex> getVertexList(Vertex vertex) {
        return graphMap.get(vertex);
    }

    public void setGraphMap(Map<Vertex, List<Vertex>> graphMap) {
        this.graphMap = graphMap;
    }

    public void addVertex(Vertex vertex) {
        graphMap.putIfAbsent(vertex , new ArrayList<>());
    }

    public void removeVertex(Vertex vertex) {
        graphMap.values()
                .stream()
                .map(e -> e.remove(vertex))
                .collect(Collectors.toList());
        graphMap.remove(vertex);
    }

    public void addEdge(Edge edge) {
        graphMap.get(edge.getVertex1()).add(edge.getVertex2());
        graphMap.get(edge.getVertex2()).add(edge.getVertex1());
    }

    public void removeEdge(Edge edge) {
        graphMap.get(edge.getVertex1()).remove(edge.getVertex2());
        graphMap.get(edge.getVertex2()).remove(edge.getVertex1());
    }

    public static class Builder {

        private Graph graph;
        private static Graph.Builder builder;

        private Builder() {
            graph = new Graph();
        }

        @NotNull
        @Contract(" -> new")
        public static Builder getInstance() {
            if (builder == null) builder = new Builder();
            return builder;
        }
        
        public Builder setVertexes(Vertex... vertexes) {
            Arrays.stream(vertexes).forEach((vertex) -> graph.addVertex(vertex));
            return builder;
        }

        public Builder setEdges(Edge... edges) {
            Arrays.stream(edges).forEach((edge -> graph.addEdge(edge)));
            return builder;
        }

        public Graph build() {
            return graph;
        }
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null) {
            if (obj.getClass() == this.getClass())
                equals = this.hashCode() == obj.hashCode();
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
