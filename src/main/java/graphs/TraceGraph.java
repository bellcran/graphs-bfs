/**
 * 탐색 관련 그래프 정의
 * 유향그래프, 무향그래프 관련 클래스(Undirect, Direct Graph)
 */
package graphs;

import java.io.*;
import java.util.*;

import graphs.core.Edge;
import graphs.core.Vertex;
import graphs.util.GraphPrint;

public class TraceGraph {
  private int V = 0; // 노드 수
  private int E = 0; // 연결(링크) 수
  private LinkedList<Integer> adj[]; // 인접노드 리스트
  private boolean terminalYn[]; // 터미널노드 여부
  private final Map<Integer, Vertex> vertexes; // 노드 저장소
  private final List<Edge> edges; // 링크 저장소
  private Integer predecessors[]; // 부모노드 저장소
  private final int max; // 노드인덱스 최대값, 루프문 반복횟수
  
  /**
   * 총 노드수를 가지고 탐색에 사용할 그래프를 생성한다.
   * 노드 인덱스값과 배열의 인덱스값은 같다.
   * @param v : 전체 노드 수
   */
  public TraceGraph(int v) {
    V = v;
    adj = new LinkedList[v];
    terminalYn = new boolean[v];
    
    this.vertexes = new HashMap<Integer, Vertex>();
    
    for (int i = 0; i < v; ++i) {
      vertexes.put(i, new Vertex("Node_"+i, i));
      adj[i] = new LinkedList<Integer>();
      terminalYn[i] = false;
    }
    
    this.edges = new LinkedList<Edge>();
    this.max = v;
  }

  /**
   * 링크를 가지고 탐색에 사용할 그래프를 생성한다.
   * @param edges
   */
  public TraceGraph(List<Edge> edges) {
    // 링크 정보를 가지고 노드를 생성한다.
    this.edges = edges;
    this.vertexes = new HashMap<Integer, Vertex>();

    this.max = getMaxVertex(edges);
    this.terminalYn = new boolean[max+1];
    this.adj = new LinkedList[max+1];
    
    for (Edge edge: edges) {
      Vertex source = edge.getSource();
      Vertex destination = edge.getDestination();
      int from = source.getIndex();
      int to = destination.getIndex();
      
      terminalYn[from] = false;
      terminalYn[to] = false;
      
      if (!vertexes.containsValue(source)) {
        vertexes.put(from, source);
        adj[from] = new LinkedList<Integer>();
      }
      if (!vertexes.containsValue(destination)) {
        vertexes.put(to, destination);
        adj[to] = new LinkedList<Integer>();
      }
      adj[from].add(to);
    }
    this.V = vertexes.size();
    this.E = edges.size();
    
    this.predecessors = new Integer[adj.length];
    for(int j = 0; j < adj.length; j++) {
      this.predecessors[j] = -1;
    }
    
    for(int j = 0; j < adj.length; j++) {
      if (adj[j] != null) {
        for(int k=0; k<adj[j].size(); k++) {
          int ch = adj[j].get(k);
          this.predecessors[ch] = j;
        }
      }
    }
  }
  
  private int getMaxVertex(List<Edge> eds) {
    int max = -1;
    for (Edge ed: eds) {
      Vertex source = ed.getSource();
      Vertex destination = ed.getDestination();
      int from = source.getIndex();
      int to = destination.getIndex();
      
      if (from > max) max = from;
      if (to > max) max = to;
    }
    
    return max;
  }
  
  /**
   * 유향 그래프 : 노드간에 방향과 연결(링크)을 설정하는 함수
   * @param from 시작점
   * @param to 끝점
   */
  public void addDirectedEdge(int from, int to) {
    if (!adj[from].contains(to)) {
      E++;
      adj[from].add(to);
      this.edges.add(new Edge("Edge_"+from, from, to));
    }
  }

  /**
   * 무향 그래프 : 노드간에 방향없는 연결(링크)을 설정하는 함수
   * @param from 시작점
   * @param to 끝점
   */
  public void addUndirectedEdge(int from, int to) {
    addDirectedEdge(from, to);
    addDirectedEdge(to, from);
  }

  /**
   * 노드 수
   * @return
   */
  public int getV() {
    return V;
  }

  /**
   * 링크 수
   * @return
   */
  public int getE() {
    return E;
  }
  
  /**
   * 전체 그래프를 얻는다.
   * @return
   */
  public LinkedList<Integer>[] getAdj() {
    return adj;
  }

  /**
   * 지정한 노드와 인접한, 연결(링크)이 설정된 노드 목록을 얻는다. 
   * @param v
   * @return
   */
  public LinkedList<Integer> getAdj(int v) {
    return adj[v];
  }

  /**
   * 모든 링크 정보를 가져온다.
   * @return
   */
  public List<Edge> getEdges() {
    return edges;
  }

  /**
   * 모든 노드 정보를 가져온다.
   * @return
   */
  public Map<Integer, Vertex> getVertexes() {
    return this.vertexes;
  }
  
  /**
   * 인덱스값을 사용하여 하나의 노드를 터미널 노드로 셋팅한다.
   * @param v
   */
  public void setTerminalYn(int v) {
    this.terminalYn[v] = true;
  }
  
  /**
   * 인덱스값 배열을 사용하여 여러 개의 노드를 터미널 노드로 셋팅한다.
   * @param v
   */
  public void setTerminalYn(int v[]) {
    for (int vval = 0; vval < v.length; vval++) 
      this.terminalYn[v[vval]] = true;
  }
  
  /**
   * 하나의 노드를 터미널 노드로 셋팅한다.
   * @param v
   */
  public void setTerminalYn(Vertex v) {
    this.terminalYn[v.getIndex()] = true;
  }

  /**
   * 여러 개의 노드를 터미널 노드로 셋팅한다.
   * @param v
   */
  public void setTerminalYn(Vertex v[]) {
    for (int vval = 0; vval < v.length; vval++) 
      this.terminalYn[v[vval].getIndex()] = true;
  }
  
  /**
   * 인덱스값에 해당하는 노드를 가져온다.
   * @param i
   * @return
   */
  public Vertex getVertex(int i) {
    return this.vertexes.get(i);
  }
  
  /**
   * 유향그래프에서 인접노드가 없거나, terminalYn 이 true 인 경우 터미널(말단)노드이다.
   * @param v
   * @return
   */
  public boolean getTerminalYn(int v) {
    Iterator itrE = iterateEdges(v);
    return !itrE.hasNext() || this.terminalYn[v];
  }  

  public void print() {
    Map<String, GraphPrint> tn = new HashMap<>();
    for (int i = 0; i < this.max; i++) {
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
    
    int ii = 0;
    for (int val : this.predecessors) {
      if (val == -1 && vertexes.get(ii) != null) tn.get(vertexes.get(ii).getId()).print();
      ii++;
    }
    
    System.out.println();
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Graph with V = " + V + " , E = " + E + "\n");
    for (int i = 0; i < this.max; i++) {
      sb.append(i + ": ");
      sb.append(adj[i]);
      sb.append("\n");
    }

    return new String(sb);
  }

  private class VertexIterator implements Iterator<Integer> {
    private int vertex = 0;

    @Override
    public boolean hasNext() {
      return (vertex < TraceGraph.this.V);
    }

    @Override
    public Integer next() {
      int current = vertex;
      vertex++;
      return current;
    }

    @Override
    public void remove() {
      // TODO Auto-generated method stub

    }
  }

  /**
   * 전체 노드의 반복자(iterator)를 얻는다. 
   * @return
   */
  public Iterator<Integer> iterateVertices() {
    return new VertexIterator();
  }

  /**
   * 지정된 노드의 인접노드의 반복자(iterator)를 얻는다. 
   * @return
   */
  public Iterator<Integer> iterateEdges(int v) {
    return this.adj[v].iterator();
  }

}



