/**
 * 최단경로를 찾기 위한 유향 그래프
 */
package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShortestPathGraph {
  private final Map<Integer, Vertex> vertexes;
  private final List<Edge> edges;
  private LinkedList<Integer> adj[]; // 인접노드 리스트
  private Integer predecessors[];

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

  private void setAdj() {
    this.adj = new LinkedList[vertexes.size()];
    this.predecessors = new Integer[vertexes.size()];
    Map<Integer, Vertex> vert = new HashMap<Integer, Vertex>();
    
    int i = 0;
    for (Edge edge: edges) {
      Vertex source = edge.getSource();
      Vertex destination = edge.getDestination();
      int from = source.getIndex();
      int to = destination.getIndex();
      
      if (!vert.containsValue(source)) {
        vert.put(from, source);
        adj[from] = new LinkedList<Integer>();
      }
      if (!vert.containsValue(destination)) {
        vert.put(to, destination);
        adj[to] = new LinkedList<Integer>();
      }
      adj[from].add(to);
    }
    
    for(int j = 0; j < this.predecessors.length; j++) {
      this.predecessors[j] = null;
      for(int k=0; k<adj[j].size(); k++) ch.get(kk);
    }
  }
  
  public void print() {
    setAdj();
    Map<String, GraphPrint> tn = new HashMap<>();
    for (int i = 0; i < this.vertexes.size(); i++) {
      if (adj[i] != null && adj[i].size() != 0) {
        String key = vertexes.get(i).getId();
        
        GraphPrint parent = null;
        if (!tn.containsKey(key)) {
          parent = new GraphPrint(key, true);
          tn.put(key, parent);
        } else {
          parent = tn.get(key);
        }
        
        for (int j=0; j<adj[i].size(); j++) {
          String childKey = vertexes.get(adj[i].get(j)).getId();
          
          GraphPrint child = null;
          if (!tn.containsKey(childKey)) {
            child = new GraphPrint(childKey, true);
            tn.put(childKey, child);
          } else {
            child = tn.get(childKey);
          }          
          parent.addChild(child);
        }
      }
    }
    tn.get(vertexes.get(0).getId()).print();
    
    //for (int val : this.predecessors) System.out.println(val);

    System.out.println();
  }
}