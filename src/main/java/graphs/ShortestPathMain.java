/**
 * 최단거리 탐색 테스트
 */
package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathMain {

  public static void main(String args[]) {
    
    // 링크정보를 생성한다.
    List<Edge> edges = new ArrayList<Edge>();
    
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
    
    // 최단거리 탐색을 위한 그래프를 생성한다.
    ShortestPathGraph graph = new ShortestPathGraph(edges);
    // 그래프에 최단거리 분석 로직을 적용한다.
    Dijkstra dijkstra = new Dijkstra(graph);
    // 시작노드를 설정해서 거리를 구한다.
    dijkstra.execute(3);
    // 지정노드까지 최단경로를 구하여 출력 (노드정보)
    LinkedList<Vertex> path = dijkstra.getPath(10);
    
    graph.print();
    
    System.out.println("==> 지정노드까지 최단경로 출력 (노드정보)");
    for (Vertex vertex : path) {
      System.out.print(vertex + " -> ");
      //System.out.println(dijkstra.getShortestDistance(vertex));
    }

  }

}
