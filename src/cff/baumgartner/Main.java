package cff.baumgartner;

import cff.baumgartner.dijkstraAlgorithm.DijkstraAlgorithm;
import cff.baumgartner.graph.Edge;
import cff.baumgartner.graph.Graph;
import cff.baumgartner.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Vertex> nodes;
    private static List<Edge> edges;

    public static void main(String[] args) {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();

        nodes.add(new Vertex("0", "Eisenstadt"));
        nodes.add(new Vertex("1", "Wien"));
        nodes.add(new Vertex("2", "Graz"));
        nodes.add(new Vertex("3", "St. Poelten"));
        nodes.add(new Vertex("4", "Klagenfurt"));
        nodes.add(new Vertex("5", "Linz"));
        nodes.add(new Vertex("6", "Salzburg"));
        nodes.add(new Vertex("7", "Innsbruck"));
        nodes.add(new Vertex("8", "Bregenz"));

        addLane("01", 0, 1, 100);

        addLane("12", 1, 2, 190);
        addLane("13", 1, 3, 80);
        addLane("15", 1, 5, 200);

        addLane("24", 2, 4, 160);

        addLane("35", 3, 5, 140);

        addLane("46", 4, 6, 210);
        addLane("47", 4, 7, 300);

        addLane("56", 5, 6, 150);

        addLane("67", 6, 7, 250);

        addLane("78", 7, 8, 200);
        addLane("76", 7, 6, 250);

        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        da.execute(nodes.get(8));
        List<Vertex> path = da.getPath(nodes.get(0));

        for(Vertex v : path){
            System.out.println(v);
        }

    }

    private static void addLane(String laneId, int source, int destination, int weight) {
        Edge llane = new Edge(laneId, nodes.get(source), nodes.get(destination), weight);
        Edge rlane = new Edge(laneId, nodes.get(destination), nodes.get(source), weight);
        edges.add(llane);
        edges.add(rlane);
    }
}
