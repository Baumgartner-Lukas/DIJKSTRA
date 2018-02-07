package cff.baumgartner.dijkstraAlgorithm;

import cff.baumgartner.graph.Edge;
import cff.baumgartner.graph.Graph;
import cff.baumgartner.graph.Vertex;

import java.util.*;

public class DijkstraAlgorithm {
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unsettledNodes;
    private Map<Vertex, Integer> distance;
    private Map<Vertex, Vertex> predecessor;

    public DijkstraAlgorithm(Graph graph) {
        this.nodes = new ArrayList<Vertex>(graph.getVertecies());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unsettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessor = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unsettledNodes.add(source);
        while (!unsettledNodes.isEmpty()) {
            Vertex node = getMinimum(unsettledNodes);
            settledNodes.add(node);
            unsettledNodes.remove(node);
            findMinDistance(node);
        }
    }

    private void findMinDistance(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex v : adjacentNodes) {
            if (getShortestDistance(v) > getShortestDistance(node) + getDistance(node, v)) {
                distance.put(v, getShortestDistance(node) + getDistance(node, v));
                predecessor.put(v, node);
                //predecessor.put(node, v);
                unsettledNodes.add(v);
            }
        }
    }

    private int getDistance(Vertex node, Vertex v) {
        for(Edge e : edges){
            if(e.getSource().equals(node) && e.getDestination().equals(v)){
                return e.getWeight();
            }
        }
        throw new RuntimeException("Error");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge e : edges) {
            if (e.getSource().equals(node) && !isSettled(e.getDestination())) {
                neighbors.add(e.getDestination());
            }
        }
        return neighbors;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private Vertex getMinimum(Set<Vertex> unsettledNodes) {
        Vertex minimum = null;
        for (Vertex v : unsettledNodes) {
            if (minimum == null) {
                minimum = v;
            } else {
                if (getShortestDistance(v) < getShortestDistance(minimum)) {
                    minimum = v;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Vertex v) {
        Integer dist = distance.get(v);
        if (dist == null) {
            return Integer.MAX_VALUE;
        } else {
            return dist;
        }
    }

    public List<Vertex> getPath(Vertex destination){
        List<Vertex> path = new LinkedList<Vertex>();
        Vertex step = destination;
        if(predecessor.get(step) == null){
            return null;
        }
        path.add(step);
        while(predecessor.get(step)!= null){
            step = predecessor.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}
