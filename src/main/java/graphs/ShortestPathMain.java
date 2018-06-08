package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ShortestPathMain {

  public static void main(String args[]) {
    List<Vertex> nodes;
    List<Edge> edges;
    
    //nodes = new ArrayList<Vertex>();
    //for (int i = 0; i < 11; i++) {
    //  Vertex location = new Vertex("Node_" + i, i);
    //  nodes.add(location);
    //}
    
    edges = new ArrayList<Edge>();
    
    edges.add(new Edge("Edge_0", 0, 1, 85));
    edges.add(new Edge("Edge_1", 0, 2, 217));
    edges.add(new Edge("Edge_2", 0, 4, 173));
    edges.add(new Edge("Edge_3", 2, 6, 186));
    edges.add(new Edge("Edge_4", 2, 7, 103));
    edges.add(new Edge("Edge_5", 3, 7, 183));
    edges.add(new Edge("Edge_6", 5, 8, 250)); 
    edges.add(new Edge("Edge_7", 8, 9, 84));
    edges.add(new Edge("Edge_8", 7, 9, 167));
    edges.add(new Edge("Edge_9", 4, 9, 502));
    edges.add(new Edge("Edge_10",9, 10, 40));
    edges.add(new Edge("Edge_11",1, 10, 600));
    
    // Lets check from location Loc_1 to Loc_10
    //ShortestPathGraph graph = new ShortestPathGraph(nodes, edges);
    ShortestPathGraph graph = new ShortestPathGraph(edges);
    Dijkstra dijkstra = new Dijkstra(graph);
    dijkstra.execute(3);
    LinkedList<Vertex> path = dijkstra.getPath(10);

    //assertNotNull(path);
    //assertTrue(path.size() > 0);

    for (Vertex vertex : path) {
      System.out.println(vertex + " ");
      //System.out.println(dijkstra.getShortestDistance(vertex));
    }

  }

}
