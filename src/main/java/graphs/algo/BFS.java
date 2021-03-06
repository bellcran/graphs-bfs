/**
 *  유향그래프 너비우선탐색 구현 (Direct Graph, BFS)
 */
package graphs.algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graphs.TraceGraph;
import graphs.core.Edge;

public class BFS {

  private Color[] colors; // 방문여부 저장
  private Integer[] predecessors; // 자신의 부모노드
  private Integer[] distances; // 해당 노드까지의 거리

  private TraceGraph graph;
  private TraceGraph trace; // 트레킹 결과값 저장

  boolean debug = false;

  public enum Color {//미탐색 노드는 하얀색, 탐색 노드는 검은색으로 표기한다. 
    WHITE, GRAY, BLACK;
  }

  /**
   * 그래프를 입력받아 탐색여부, 거리, 선행노드 관련 배열을 초기화한다.
   * 모든 노드를 방문하지 않은 것으로 표기한다.(디폴트 WHITE)
   * 배열의 인덱스와 노드의 인덱스는 같다.   * 
   * @param g
   */
  public BFS(TraceGraph g) {
    graph = g;
    colors = new Color[g.getV()]; // 탐색여부
    predecessors = new Integer[g.getV()]; // 선행노드
    distances = new Integer[g.getV()]; // 거리
  }
  
  /**
   * 너비우선순위 탐색을 수행한다.
   * @param source : 시작(출발)노드
   */
  public void execute(int source) {
    // 시작노드가 전체 노드의 범위를 벗어나면 예외를 발생시킨다. 
    // 노드 인덱스는 0부터 시작한다.
    if (source < 0 || source >= graph.getV()) {
      throw new IndexOutOfBoundsException();
    }

    this.trace = new TraceGraph(graph.getV());

    for (int u = 0; u < graph.getV(); u++) {
      if (u != source) {
        colors[u] = Color.WHITE;
        distances[u] = Integer.MAX_VALUE;
        predecessors[u] = null;
      }
    }

    // BFS를 위한 큐를 만든다.
    Queue<Integer> q = new LinkedList<Integer>();
    // 현재 노드를 탐색(BLACK)으로 표기하고 큐에 넣는다.
    colors[source] = Color.BLACK;
    distances[source] = 0;
    predecessors[source] = null;
    q.add(source);

    while (!q.isEmpty()) {
      // 큐에서 노드를 빼낸다.
      Integer u = q.poll();
      // 빼낸 노드가 터미널노드인지 판단한다.
      if(!graph.getTerminalYn(u)) {
        
        // 지정노드(유향그래프)의 인접 노드를 모두 가져온다. 
        for (Integer v : graph.getAdj(u)) {
          // 방문하지 않은 인접노드(WHITE)인 경우 방문(BLACK)으로 표기하고 큐에 넣는다.
          if (colors[v] == Color.WHITE) {
            colors[v] = Color.BLACK;
            distances[v] = distances[u] + 1;
            predecessors[v] = u;
            q.add(v);
            
            // 결과(리턴)값에 유향그래프로 저장한다. 
            this.trace.addDirectedEdge(u, v);
          }
        }

        colors[u] = Color.BLACK;
        if (debug) {
          System.out.println(this);
        }
        
      }
    }
  }
  
  /**
   * tracing 결과를 그래프 형태로 리턴한다.
   * @return
   */
  public TraceGraph getGraph() {
    return this.trace;
  }
  
  /**
   * tracing 결과를 링크(시작노드-끝노드)의 배열값으로 리턴한다.  
   * @return
   */
  public List<Edge> getPath() {
    /*LinkedList<Edge> path = new LinkedList<Edge>();
    LinkedList<Integer> adjs[] = this.trace.getAdj();
    
    for (int i = 0 ; i < adjs.length; i++) {
      if (adjs[i].size() != 0) {
        
        for (int j : adjs[i])  path.add(new Edge("Edge_"+i, i, j));
      }
    }
    
    return path;
    */
    return this.trace.getEdges();
  }

  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(Arrays.toString(colors));
    sb.append("\n");
    sb.append(Arrays.toString(distances));
    sb.append("\n");
    sb.append(Arrays.toString(predecessors));
    sb.append("\n");
    return new String(sb);
  }

  /**
   * 각 노드의 탐색유무 값
   * @return
   */
  public Color[] getColors() {
    return colors;
  }
  
  /**
   * 각 노드의 선행노드 인덱스
   * @return
   */
  public Integer[] getPredecessors() {
    return predecessors;
  }
  
  /**
   * 시작노드부터 각 노드까지의 거리값 (노드 간 거리 디폴트 : 1)
   * @return
   */
  public Integer[] getDistances() {
    return distances;
  }
}