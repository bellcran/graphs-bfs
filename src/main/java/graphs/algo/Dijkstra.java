/**
 * 다익스트라 알고리즘
 */
package graphs.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graphs.ShortestPathGraph;
import graphs.core.Edge;
import graphs.core.Vertex;

public class Dijkstra {

  private final Map<Integer, Vertex> nodes;
  private final List<Edge> edges;
  private Set<Vertex> settledNodes; // 방문노드 집합
  private Set<Vertex> unSettledNodes; // 비방문노드 집합
  private Map<Vertex, Vertex> predecessors; // 부모노드 저장소
  private Map<Vertex, Integer> distance; // 해당노드까지 거리값

  /**
   * 다익스트라 알고리즘에 사용되는 노드, 링크 초기화
   * 
   * @param graph
   */
  public Dijkstra(ShortestPathGraph graph) {
    // 노드, 링크의 복사본을 생성하여 계산에 사용한다.
    this.nodes = new HashMap<Integer, Vertex>(graph.getVertexes());
    this.edges = new ArrayList<Edge>(graph.getEdges());
  }

  /**
   * 시작노드에서 하위노드까지의 거리계산
   * @param source : 시작노드의 인덱스값
   */
  public void execute(int source) {
    execute(nodes.get(source));
  }

  /**
   * 시작노드에서 하위노드까지의 거리계산
   * @param source : 시작노드(vertex)
   */
  public void execute(Vertex source) {
    settledNodes = new HashSet<Vertex>();
    unSettledNodes = new HashSet<Vertex>();
    distance = new HashMap<Vertex, Integer>();
    predecessors = new HashMap<Vertex, Vertex>();
    distance.put(source, 0);
    unSettledNodes.add(source);
    while (unSettledNodes.size() > 0) {
      Vertex node = getMinimum(unSettledNodes);
      settledNodes.add(node);
      unSettledNodes.remove(node);
      findMinimalDistances(node);
    }
  }

  /*
   * 해당노드에서 가장 가까운 인접노드 찾기, 거리계산  
   * @param node
   */
  private void findMinimalDistances(Vertex node) {
    List<Vertex> adjacentNodes = getNeighbors(node);
    for (Vertex target : adjacentNodes) {
      if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node) + getDistance(node, target));
        predecessors.put(target, node);
        unSettledNodes.add(target);
      }
    }

  }

  /*
   * 링크로부터 저장된 거리값 가져오기
   */
  private int getDistance(Vertex node, Vertex target) {
    for (Edge edge : edges) {
      if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
        return edge.getWeight();
      }
    }
    throw new RuntimeException("Should not happen");
  }

  /*
   * 해당노드의 모든 인접노드 가져오기
   */
  private List<Vertex> getNeighbors(Vertex node) {
    List<Vertex> neighbors = new ArrayList<Vertex>();
    for (Edge edge : edges) {
      if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
        neighbors.add(edge.getDestination());
      }
    }
    return neighbors;
  }

  /*
   * 가장 가까운 거리에 있는 노드 가져오기
   */
  private Vertex getMinimum(Set<Vertex> vertexes) {
    Vertex minimum = null;
    for (Vertex vertex : vertexes) {
      if (minimum == null) {
        minimum = vertex;
      } else {
        if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
          minimum = vertex;
        }
      }
    }
    return minimum;
  }

  private boolean isSettled(Vertex vertex) {
    return settledNodes.contains(vertex);
  }

  public int getShortestDistance(Vertex destination) {
    Integer d = distance.get(destination);
    if (d == null) {
      return Integer.MAX_VALUE;
    } else {
      return d;
    }
  }

  /**
   * 소스에서 선택한 대상까지의 경로를 반환하고, 경로가 없으면 NULL을 반환한다.
   * @param target : 끝노드(vertex)
   * @return
   */
  public LinkedList<Vertex> getPath(Vertex target) {
    LinkedList<Vertex> path = new LinkedList<Vertex>();
    Vertex step = target;
    // 링크가 존재하는지 체크한다.
    if (predecessors.get(step) == null) {
      return null;
    }
    path.add(step);
    while (predecessors.get(step) != null) {
      step = predecessors.get(step);
      path.add(step);
    }
    // 올바른 순서로 바꾼다.
    Collections.reverse(path);
    return path;
  }
  
  /**
   * 소스에서 선택한 대상까지의 경로를 반환하고, 경로가 없으면 NULL을 반환한다.
   * @param target : 끝노드의 인덱스값
   * @return
   */
  public LinkedList<Vertex> getPath(int target) {
    return getPath(nodes.get(target));
  }
}