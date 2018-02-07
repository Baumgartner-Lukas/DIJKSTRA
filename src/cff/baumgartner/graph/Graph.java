package cff.baumgartner.graph;

import java.util.List;

public class Graph {
    private final List<Vertex> vertecies;
    private final List<Edge>edges;

    public Graph(List<Vertex> vertecies, List<Edge>edges){
        this.vertecies = vertecies;
        this.edges = edges;
    }

    public List<Vertex> getVertecies() {
        return vertecies;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
