/**
 *  유향그래프, 무향그래프 관련 클래스(Undirect, Direct Graph)
 * 
 */
package graphs;

import java.io.*;
import java.util.*;

class Graph {
  private int V; // 노드 수
  private int E = 0; // 연결(링크) 수
  private LinkedList<Integer> adj[]; // 인접성 리스트
  private boolean terminalYn[]; // 터미널노드 여부

  /**
   * 생성자 : 파라메터는 노드수를 의미한다
   */
  Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    terminalYn = new boolean[v];
    for (int i = 0; i < v; ++i) {
      adj[i] = new LinkedList();
      terminalYn[i] = false;
    }
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
   * 터미널 노드를 셋팅한다.
   * @param v
   */
  public void setTerminalYn(int v) {
    this.terminalYn[v] = true;
  }
  public void setTerminalYn(int v[]) {
    for (int vval = 0; vval < v.length; vval++) 
      this.terminalYn[v[vval]] = true;
  }
  
  /**
   * 유향그래프에서 인접노드가 없거나, terminalYn 이 true 인 경우 터미널(말단)노드
   * @param v
   * @return
   */
  public boolean getTerminalYn(int v) {
    Iterator itrE = iterateEdges(v);
    return !itrE.hasNext() || this.terminalYn[v];
  }  
    
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Graph with V = " + V + " , E = " + E + "\n");
    for (int i = 0; i < this.getV(); i++) {
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
      return (vertex < Graph.this.V);
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