/**
 * 최단경로를 찾기 위한 유향 그래프
 */
package graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathGraph {
  private final Map<Integer, Vertex> vertexes;
  private final List<Edge> edges;

  public ShortestPathGraph(Map<Integer, Vertex> vertexes, List<Edge> edges) {
    this.vertexes = vertexes;
    this.edges = edges;
  }

  public ShortestPathGraph(List<Edge> edges) {
    this.edges = edges;
    
    // create all nodes ready to be updated with the edges
    this.vertexes = new HashMap<Integer, Vertex>();

    for (Edge edge: edges) {
      if (!vertexes.containsValue(edge.getSource())) vertexes.put(edge.getSource().getIndex(),edge.getSource());
      if (!vertexes.containsValue(edge.getDestination())) vertexes.put(edge.getDestination().getIndex(),edge.getDestination());
    }
  }

  public Map<Integer, Vertex> getVertexes() {
    return vertexes;
  }

  public List<Edge> getEdges() {
    return edges;
  }

}