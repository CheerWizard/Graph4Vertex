package utils;

import models.Graph;
import models.Vertex;

import java.util.*;

public final class GraphUtility {

    private static Set<Vertex> visitedVertexes = new LinkedHashSet<>();
    //search graph in deep
    public static synchronized Set<Vertex> searchInDeep(Graph graph, Vertex rootVertex) {
        //prepare set result
        if (!visitedVertexes.isEmpty()) visitedVertexes.clear();
        //create stack for each next step
        final Stack<Vertex> stack = new Stack<>();
        //first step we push element to stack for forward step
        stack.push(rootVertex);
        //do algorithm , while we will not return to the start point
        while (!stack.isEmpty()) {
            //get the previous vertex that is already marked
            final Vertex vertex = stack.pop();
            //if it's not already added to result set , then do it
            //add to result set
            if (!visitedVertexes.contains(vertex)) {
                visitedVertexes.add(vertex);
                //iterate through the list of appropriate 'key' vertex
                graph.getVertexList(vertex).forEach(stack::push);
            }
        }
        return visitedVertexes;
    }
    //search graph in width
    public static synchronized Set<Vertex> searchInWidth(Graph graph, Vertex rootVertex) {
        //prepare our set result
        if (!visitedVertexes.isEmpty()) visitedVertexes.clear();
        //crete queue for each next step
        final Queue<Vertex> queue = new LinkedList<>();
        //first step we add to queue and also to result set our first root vertex
        queue.add(rootVertex);
        visitedVertexes.add(rootVertex);
        //do algorithm , while we will not return to the start point
        while (!queue.isEmpty()) {
            //get the parent vertex
            final Vertex vertex = queue.poll();
            //iterate through list of appropriate 'key' vertex
            for (Vertex v : graph.getVertexList(vertex)) {
                //if some vertex is not already added to the result set , then do it
                //add it to result set and to queue too.
                if (!visitedVertexes.contains(v)) {
                    visitedVertexes.add(v);
                    queue.add(v);
                }
            }
        }
        return visitedVertexes;
    }
}
