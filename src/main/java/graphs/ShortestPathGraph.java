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

  /**
   * 노드와 링크정보를 가지고 최단경로 탐색 관련 그래프를 생성한다.
   * @param vertexes
   * @param edges
   */
  public ShortestPathGraph(Map<Integer, Vertex> vertexes, List<Edge> edges) {
    this.vertexes = vertexes;
    this.edges = edges;
  }

  /**
   * 링크정보를 가지고 최단경로 탐색 관련 그래프를 생성한다.
   * @param edges
   */
  public ShortestPathGraph(List<Edge> edges) {
    this.edges = edges;
    
    // 링크 정보를 가지고 노드를 생성한다.
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