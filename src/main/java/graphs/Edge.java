/**
 * 링크 클래스 정의
 */
package graphs;

public class Edge {
  private final String id;
  private final Vertex source; // from 의미
  private final Vertex destination; // to 의미
  private final int weight; // 거리, 가중값

  /**
   * 링크 초기화
   * 
   * @param id
   * @param source
   * @param destination
   * @param weight
   */
  public Edge(String id, Vertex source, Vertex destination, int weight) {
    this.id = id;
    this.source = source;
    this.destination = destination;
    this.weight = weight;
  }

  public Edge(String id, int source, int destination, int weight) {
    this.id = id;
    this.source = new Vertex("Node_" + source, source);
    this.destination = new Vertex("Node_" + destination, destination);
    this.weight = weight;
  }

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
    return source + " " + destination;
  }

}