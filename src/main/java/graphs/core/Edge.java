/**
 * 링크 클래스 정의
 */
package graphs.core;

public class Edge {
  private final String id;
  private final Vertex source; // from 의미
  private final Vertex destination; // to 의미
  private final int weight; // 거리, 가중값

  /**
   * 시작노드와 끝노드를 가지고 링크를 생성한다.
   * 
   * @param id
   * @param source 시작노드
   * @param destination 끝노드
   * @param weight 거리
   */
  public Edge(String id, Vertex source, Vertex destination, int weight) {
    this.id = id;
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }

  /**
   * 노드의 인덱스값으로 링크를 생성한다.
   * 
   * @param id
   * @param source 시작노드의 인덱스값
   * @param destination 끝노드의 인덱스값
   * @param weight 거리
   */
  public Edge(String id, int source, int destination, int weight) {
    this.id = id;
    this.source = new Vertex("Node_" + source, source);
    this.destination = new Vertex("Node_" + destination, destination);
    this.weight = weight;
  }

  /**
   * 거리값이 존재하지 않는 링크를 생성한다.
   * 
   * @param id
   * @param source 시작노드의 인덱스값
   * @param destination 끝노드의 인덱스값
   */
  public Edge(String id, int source, int destination) {
    this.id = id;
    this.source = new Vertex("Node_" + source, source);
    this.destination = new Vertex("Node_" + destination, destination);
    this.weight = 1;
  }
  
  public String getId() {
    return id;
  }

  public Vertex getDestination() {
    return destination;
  }

  public Vertex getSource() {
    return source;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return source + "-" + destination;
  }

}